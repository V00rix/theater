package theater.repositories.simpleCrud;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.CRUDTests;
import theater.repositories.PerformanceRepository;
import theater.utility.Dummy;
import theater.utility.JpaTestBase;

public class PerformanceTest extends JpaTestBase implements CRUDTests {
    @Autowired
    PerformanceRepository performanceRepository;

    @Test
    public void create() {
        var performance = Dummy.performance();
        performance.description = "Description";

        performanceRepository.save(performance);
    }

    @Override
    @Test
    public void read() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void update() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void delete() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void createAndRead() {

        throw new NotImplementedException();
    }

    // TODO: 13-Jun-18 More tests
}
