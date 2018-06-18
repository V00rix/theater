package theater.environment;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import theater.controllers.PerformanceController;
import theater.repositories.*;
import theater.utility.JpaTestBase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test simple CRUD operations on all entities
 */
public class RepositoriesSmokeTest extends JpaTestBase {
    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected HallRepository hallRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected PerformanceRepository performanceRepository;

    @Autowired
    protected SessionRepository sessionRepository;

    @Autowired
    protected TheaterRepository theaterRepository;

    @Autowired
    protected PerformanceController performanceController;

    @Autowired
    private TestEntityManager entityManager;

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
