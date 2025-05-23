package com.mstftrgt.hotelreservationsystem.generic.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class QueryBusImpl implements QueryBus {

    private final Map<Class<? extends Query>, QueryHandler> handlers;

    public QueryBusImpl(ApplicationContext context) {
        this.handlers = context.getBeansOfType(QueryHandler.class).values().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                handler -> {
                                    var genericInterface = ((java.lang.reflect.ParameterizedType) handler.getClass()
                                            .getGenericInterfaces()[0]);
                                    return (Class<? extends Query>) genericInterface.getActualTypeArguments()[0];
                                },
                                handler -> handler
                        )
                );
    }

    @Override
    public <R, Q extends Query<R>> R dispatchAndReturn(Q query) {
        QueryHandler<Q, R> handler = handlers.get(query.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for query: " + query.getClass());
        }
        log.info("Handling query: {}", query);
        return handler.handle(query);
    }
}