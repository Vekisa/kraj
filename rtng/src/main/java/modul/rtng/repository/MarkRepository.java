package modul.rtng.repository;

import modul.rtng.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarkRepository  extends JpaRepository<Mark, Long> {
    @Query(
            value = "SELECT * FROM mark m WHERE m.object_id = ?1", nativeQuery = true)
    List<Mark> findObjectMarks(Long objectId);

    @Query(
            value = "SELECT * FROM mark m WHERE m.object_id = ?1 AND m.user_id = ?2", nativeQuery = true)
    Optional<Mark> findMarkForObjectFromUser(Long objectId, Long userId);

}
