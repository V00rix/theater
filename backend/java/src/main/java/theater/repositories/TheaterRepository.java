package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}