package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.entities.Client;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.entities.Session;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.*;
import theater.utility.Dummy;
import theater.utility.EntityTestBase;
import theater.utility.JpaTestBase;

public class OrderTest extends JpaTestBase implements CRUDTest {

    //region Autowired
    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ClientRepository clientRepository;
    //endregion

    //region Fields
    private Performance performance;
    private Session session;
    private Hall hall;
    private Client client;
    //endregion

    @Before
    public void setUp() throws Exception {
        performance = Dummy.find(performanceRepository).orElse(Dummy.performance());
        performanceRepository.save(performance);
        hall = Dummy.find(hallRepository).orElse(Dummy.hall());
        hallRepository.save(hall);
        session = Dummy.find(sessionRepository).orElse(Dummy.session(hall, performance));
        sessionRepository.save(session);
        client = Dummy.find(clientRepository).orElse(Dummy.client());
        clientRepository.save(client);
        orderRepository.deleteAll();
    }

    @Override
    @Test
    public void read() {
        EntityTestBase.findAllAndPrint(orderRepository);
    }

    @Override
    @Test
    public void create() {
        orderRepository.deleteAll();
        var order = Dummy.order(session, client);
        sessionRepository.save(session);
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
        orderRepository.deleteAll();
        var order = Dummy.order(session, client);
        orderRepository.save(order);
        var found = EntityTestBase.findAllAndPrint(orderRepository);
        assert order.equalz(found.get(0));
    }

}
