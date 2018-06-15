package theater.environment;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import theater.controllers.PerformanceController;
import theater.utility.JpaTestBase;
import theater.repositories.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test simple CRUD operations on all entities
 */
public class RepositoriesSmokeTest extends JpaTestBase {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

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
    @Autowired
    private PerformanceController performanceController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(clientRepository).isNotNull();
        assertThat(hallRepository).isNotNull();
        assertThat(orderRepository).isNotNull();
        assertThat(performanceRepository).isNotNull();
        assertThat(sessionRepository).isNotNull();
        assertThat(theaterRepository).isNotNull();
    }
}
