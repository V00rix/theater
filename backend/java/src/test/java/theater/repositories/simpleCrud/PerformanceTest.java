package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.common.JpaTestBase;
import theater.domain.entities.Performance;
import theater.domain.entities.Theater;
import theater.repositories.PerformanceRepository;

public class PerformanceTest extends JpaTestBase {

    private static Theater theater;

    @Autowired
    PerformanceRepository performanceRepository;

    @Before
    public void setUp() throws Exception {
        System.out.println("Creating theater");
        theater = TheaterTest.theater(1);
    }

    @Test
    public void createPerformance() {

        var performance = new Performance("Author", "Title");
        performance.description = "Description";

        performanceRepository.save(performance);
    }
}
