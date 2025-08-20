package ru.practicum.service;

import ru.practicum.dto.comment.CommentAdminDto;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.model.CommentStatus;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    CommentDto updateCommentByUser(Long userId, Long commentId, UpdateCommentDto dto);

    void deleteCommentByUser(Long userId, Long commentId);

    CommentDto patchCommentByAdmin(Long commentId, CommentAdminDto dto);

    void deleteCommentByAdmin(Long commentId);

    List<CommentDto> getCommentsByAdmin(Long eventId, CommentStatus status, int from, int size);

    List<CommentDto> getCommentsByEvent(Long eventId, int from, int size);
}
