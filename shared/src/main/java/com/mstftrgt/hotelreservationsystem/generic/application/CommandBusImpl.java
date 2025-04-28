package com.mstftrgt.hotelreservationsystem.generic.application;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CommandBusImpl implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandHandlers = new HashMap<>();
    private final Map<Class<? extends Command>, VoidCommandHandler<? extends Command>> voidCommandHandlers = new HashMap<>();

    public CommandBusImpl(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(CommandHandler.class).values()
                .forEach(this::registerCommandHandler);

        applicationContext.getBeansOfType(VoidCommandHandler.class).values()
                .forEach(this::registerVoidCommandHandler);
    }

    private <C extends Command, R> void registerCommandHandler(CommandHandler<C, R> handler) {
        Class<?> commandType = ResolvableType.forClass(CommandHandler.class, handler.getClass())
                .getGeneric(0).getRawClass();

        if (Command.class.isAssignableFrom(commandType)) {
            @SuppressWarnings("unchecked")
            Class<? extends Command> commandClass = (Class<? extends Command>) commandType;
            commandHandlers.put(commandClass, handler);
        }
    }

    private <C extends Command> void registerVoidCommandHandler(VoidCommandHandler<C> handler) {
        Class<?> commandType = ResolvableType.forClass(VoidCommandHandler.class, handler.getClass())
                .getGeneric(0).getRawClass();

        if (Command.class.isAssignableFrom(commandType)) {
            @SuppressWarnings("unchecked")
            Class<? extends Command> commandClass = (Class<? extends Command>) commandType;
            voidCommandHandlers.put(commandClass, handler);
        }
    }

    @Override
    public <C extends Command> void dispatch(C command) {
        @SuppressWarnings("unchecked")
        VoidCommandHandler<C> handler = (VoidCommandHandler<C>) voidCommandHandlers.get(command.getClass());

        if (handler == null) {
            throw new IllegalArgumentException("No command handler registered for " + command.getClass().getName());
        }

        log.info("Handling command {}" , command);
        handler.handle(command);
    }

    @Override
    public <C extends Command, R> R dispatchAndReturn(C command) {
        @SuppressWarnings("unchecked")
        CommandHandler<C, R> handler = (CommandHandler<C, R>) commandHandlers.get(command.getClass());

        if (handler == null) {
            throw new IllegalArgumentException("No command handler registered for " + command.getClass().getName());
        }

        log.info("Handling command {}" , command);
        return handler.handle(command);
    }
}