package com.magoya.challenge.infrastructure.out.event;

import com.magoya.challenge.application.port.out.events.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EventModel {

    private String id;
    private EventType type;
    private Object data;
    private Long datetime;

}
