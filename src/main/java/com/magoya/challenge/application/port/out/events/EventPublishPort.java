package com.magoya.challenge.application.port.out.events;

public interface EventPublishPort<T> {
    void publish (T data, EventType type);
}
