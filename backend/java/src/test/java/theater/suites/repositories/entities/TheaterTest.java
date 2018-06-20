package theater.suites.repositories.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;
import theater.suites.repositories.EntityTestBase;
import theater.utility.Dummy;

import java.util.List;

public class TheaterTest extends EntityTestBase<Theater> {
    @Autowired
    TheaterRepository repository;

    @Override
    public JpaRepository<Theater, Long> getRepository() {
        return repository;
    }

    @Override
    public Theater construct() {
        return Dummy.theater();
    }

    @Override
    public List<Theater> constructMultiple() {
        return Dummy.theaters();
    }
}
