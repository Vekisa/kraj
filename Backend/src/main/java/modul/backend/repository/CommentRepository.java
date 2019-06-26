package modul.backend.repository;

import modul.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment c where c.approved = TRUE and c.registered_user_id = ?", nativeQuery = true)
    List<Comment> getForUnit(Long id);
}
