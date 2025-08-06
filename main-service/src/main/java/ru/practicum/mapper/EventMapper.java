package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.Location;
import ru.practicum.model.User;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class, LocationMapper.class})
public interface EventMapper {

    EventFullDto toFullDto(Event event);

    EventShortDto toShortDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "confirmedRequests", constant = "0")
    @Mapping(target = "state", expression = "java(ru.practicum.model.enums.EventState.PENDING)")
    @Mapping(target = "createdOn", expression = "java(mapNow())")
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "views", constant = "0L")
    @Mapping(target = "category", source = "category")

    Event toEvent(NewEventDto newEventDto, User initiator, Category category, Location location);

    default LocalDateTime mapNow() {
        return LocalDateTime.now();
    }
}
