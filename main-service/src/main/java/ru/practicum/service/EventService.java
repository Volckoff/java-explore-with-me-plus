package ru.practicum.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.practicum.dto.event.*;
import ru.practicum.model.enums.EventState;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    // private
    EventFullDto createEvent(Long userId, NewEventDto dto);

    List<EventShortDto> getUserEvents(Long userId, int from, int size);

    EventFullDto getUserEvent(Long userId, Long eventId);

    EventFullDto updateUserEvent(Long userId, Long eventId, UpdateEventUserRequest dto);

    //admin
    List<EventFullDto> searchAdmin(List<Long> users,
                                   List<EventState> states,
                                   List<Long> categories,
                                   LocalDateTime rangeStart,
                                   LocalDateTime rangeEnd,
                                   int from,
                                   int size);

    EventFullDto updateAdminEvent(Long eventId, UpdateEventAdminRequest dto);

    //public
    List<EventShortDto> searchPublic(String text,
                                     List<Long> categories,
                                     Boolean paid,
                                     LocalDateTime rangeStart,
                                     LocalDateTime rangeEnd,
                                     Boolean onlyAvailable,
                                     String sort,
                                     int from,
                                     int size,
                                     HttpServletRequest request);

    EventFullDto getPublicEvent(Long eventId, HttpServletRequest request);
}
