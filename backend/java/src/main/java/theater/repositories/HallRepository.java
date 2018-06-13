package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}