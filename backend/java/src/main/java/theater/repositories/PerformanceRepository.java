package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Performance;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}