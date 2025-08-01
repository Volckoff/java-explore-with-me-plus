package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsDto;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.EndpointHit;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final StatsRepository statsRepository;

    @Transactional
    @Override
    public void save(EndpointHitDto endpointHitDto) {
        log.debug("Service: Attempting to save hit: {}", endpointHitDto);
        if (endpointHitDto == null) {
            log.warn("Service: Cannot save hit, input EndpointHitDto was null.");
            throw new IllegalArgumentException("Input EndpointHitDto cannot be null.");
        }
        EndpointHit endpointHit = StatsMapper.toEntity(endpointHitDto);
        statsRepository.save(endpointHit);
        log.info("Service: Hit saved successfully for app: {}, uri: {}", endpointHit.getApp(), endpointHit.getUri());
    }

    @Override
    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startTime = LocalDateTime.parse(start, FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse(end, FORMATTER);

        return unique ?
                statsRepository.findUniqueStats(startTime, endTime, uris)
                : statsRepository.findAllStats(startTime, endTime, uris);
    }
}
