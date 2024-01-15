package com.magoya.challenge.infrastructure.out.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magoya.challenge.application.port.out.events.EventPublishPort;
import com.magoya.challenge.application.port.out.events.EventType;
import com.magoya.challenge.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class EventBusAdapter <T> implements EventPublishPort<T> {

    private final RabbitTemplate template;
    static ObjectMapper mapper = new ObjectMapper();

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingKey;


    /**
     * Generic publisher using rabbitMQ
     * @param data will be serialized content of message
     * @param type type of event to publish, needed to process on listener
     * @throws RuntimeException When queue publish or serialization process fails
     */
    @Override
    public void publish(T data, EventType type) {
        try {
            EventModel model = new EventModel(
                    UUID.randomUUID().toString(),
                    type,
                    data,
                    System.currentTimeMillis()
            );
            template.convertAndSend(exchange, routingKey, model);
            log.info("Event registered: ....{} {}", type, mapper.writeValueAsString(data));
        } catch (JsonProcessingException | AmqpException e) {
            throw new RuntimeException(e); // Make better exception management
        }
    }
}
