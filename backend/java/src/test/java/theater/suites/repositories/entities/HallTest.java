package theater.suites.repositories.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Hall;
import theater.repositories.HallRepository;
import theater.suites.repositories.EntityTestBase;
import theater.utility.Dummy;

import java.util.List;

public class HallTest extends EntityTestBase<Hall> {
    @Autowired
    HallRepository hallRepository;

    @Override
    protected JpaRepository<Hall, Long> getRepository() {
        return hallRepository;
    }

    @Override
    protected Hall construct() {
        return Dummy.hall();
    }

    @Override
    protected List<Hall> constructMultiple() {
        return Dummy.halls();
    }
}
