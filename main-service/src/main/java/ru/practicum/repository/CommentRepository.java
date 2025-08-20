package ru.practicum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.model.Comment;
import ru.practicum.model.CommentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"author"})
    @Query("""
            SELECT c FROM Comment c
            WHERE c.event.id = :eventId AND c.status = ru.practicum.model.CommentStatus.CONFIRMED
            ORDER BY c.createdOn DESC
            """)
    List<Comment> findPublishedByEvent(@Param("eventId") Long eventId, Pageable pageable);

    Optional<Comment> findByIdAndAuthorId(Long id, Long authorId);

    @EntityGraph(attributePaths = {"author", "event"})
    @Query("""
            SELECT c FROM Comment c
            WHERE (:status IS NULL OR c.status = :status)
            AND (:eventId IS NULL OR c.event.id = :eventId)
            AND (:authorId IS NULL OR c.author.id = :authorId)
            AND (:start IS NULL OR c.createdOn >= :start)
            AND (:end IS NULL OR c.createdOn <= :end)
            ORDER BY c.createdOn DESC
            """)
    List<Comment> adminSearch(@Param("status") CommentStatus status,
                              @Param("eventId") Long eventId,
                              @Param("authorId") Long authorId,
                              @Param("start")LocalDateTime start,
                              @Param("end")LocalDateTime end,
                              Pageable pageable);


    @EntityGraph(attributePaths = {"author"})
    @Query("""
    SELECT c FROM Comment c
    WHERE c.event.id = :eventId AND c.status = :status
    ORDER BY c.createdOn DESC
    """)
    List<Comment> findByEventIdAndStatus(@Param("eventId") Long eventId,
                                         @Param("status") CommentStatus status);

    @EntityGraph(attributePaths = {"author", "event"})
    List<Comment> findAllByAuthorId(Long authorId, Pageable pageable);

    List<Comment> findByAuthorIdAndEventId(Long authorId, Long eventId);

    List<Comment> findByStatus(CommentStatus status);

    List<Comment> findByAuthorIdAndStatus(Long authorId, CommentStatus status);
}