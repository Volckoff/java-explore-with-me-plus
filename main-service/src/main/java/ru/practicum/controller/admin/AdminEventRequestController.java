package ru.practicum.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.dto.event.EventRequestStatusUpdateRequestDto;
import ru.practicum.dto.event.EventRequestStatusUpdateResultDto;
import ru.practicum.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events/{eventId}/requests")
@RequiredArgsConstructor
@Validated
public class AdminEventRequestController {

    private final RequestService requestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getEventRequests(@PathVariable @Valid Long userId,
                                                          @PathVariable @Valid Long eventId) {
        return requestService.getEventRequests(userId, eventId);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EventRequestStatusUpdateResultDto updateRequestsStatus(@PathVariable @Valid Long userId,
                                                               @PathVariable @Valid Long eventId,
                                                               @RequestBody EventRequestStatusUpdateRequestDto
                                                                              updateRequest) {
        return requestService.changeRequestStatus(userId, eventId, updateRequest);
    }
}