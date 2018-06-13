package theater.environment;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import theater.common.JpaTestBase;
import theater.repositories.*;

/**
 * Test simple CRUD operations on all entities
 */
public class RepositoriesInjectionTest extends JpaTestBase {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Test
    public void simpleRunTest() {}
}
