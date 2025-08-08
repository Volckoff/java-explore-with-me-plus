package ru.practicum.mapper;

import org.mapstruct.*;
import ru.practicum.dto.event.*;
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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    void patchFromUser(UpdateEventUserRequest src, @MappingTarget Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    void patchFromAdmin(UpdateEventAdminRequest src, @MappingTarget Event event);

    default LocalDateTime mapNow() {
        return LocalDateTime.now();
    }
}
