package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.CommentAdminDto;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.model.CommentStatus;
import ru.practicum.service.CommentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Validated
public class AdminCommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> search(@RequestParam(required = false) Long eventId,
                                   @RequestParam(required = false) CommentStatus status,
                                   @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                   @RequestParam(defaultValue = "10") @Positive int size) {
        return commentService.getCommentsByAdmin(eventId, status, from, size);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto patchComment(@PathVariable Long commentId,
                                   @Valid @RequestBody CommentAdminDto dto) {
        return commentService.patchCommentByAdmin(commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
    }
}