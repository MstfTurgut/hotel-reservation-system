package com.mstftrgt.hotelreservationsystem.cqrs;

// command/CommandBusImpl.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class CommandBusImpl implements CommandBus {

    private final Map<Class<? extends Command>, CommandHandler> handlers;

    public CommandBusImpl(ApplicationContext context) {
        this.handlers = context.getBeansOfType(CommandHandler.class).values().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                handler -> {
                                    var genericInterface = ((java.lang.reflect.ParameterizedType) handler.getClass()
                                            .getGenericInterfaces()[0]);
                                    return (Class<? extends Command>) genericInterface.getActualTypeArguments()[0];
                                },
                                handler -> handler
                        )
                );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C extends Command> void dispatch(C command) {
        CommandHandler<C> handler = handlers.get(command.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for command: " + command.getClass());
        }
        log.info("Dispatching command: {}", command);
        handler.handle(command);
    }
}