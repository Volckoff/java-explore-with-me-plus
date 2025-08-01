package ru.practicum.mapper;

import ru.practicum.model.EndpointHit;
import ru.practicum.EndpointHitDto;

public class StatsMapper {
    public static EndpointHit toEntity(EndpointHitDto dto) {
        return EndpointHit.builder()
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .timestamp(dto.getTimestamp())
                .build();
    }
}
