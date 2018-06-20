package theater.suites.repositories.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;
import theater.suites.repositories.EntityTestBase;
import theater.utility.Dummy;

import java.util.List;

public class PerformanceTest extends EntityTestBase<Performance> {
    @Autowired
    PerformanceRepository repository;

    @Override
    protected JpaRepository<Performance, Long> getRepository() {
        return repository;
    }

    @Override
    protected Performance construct() {
        return Dummy.performance();
    }

    @Override
    protected List<Performance> constructMultiple() {
        return Dummy.performances();
    }
}
