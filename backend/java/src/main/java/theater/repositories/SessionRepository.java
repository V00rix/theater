package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}