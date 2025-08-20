package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.Comment;
import ru.practicum.model.CommentStatus;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByEventIdAndStatus(Long eventId, CommentStatus status);

    List<Comment> findByAuthorId(Long authorId);

    List<Comment> findByAuthorIdAndEventId(Long authorId, Long eventId);

    List<Comment> findByStatus(CommentStatus status);

    List<Comment> findByEventId(Long eventId);

    List<Comment> findByAuthorIdAndStatus(Long authorId, CommentStatus status);
}