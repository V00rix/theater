package theater.suites.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Order;
import theater.repositories.*;
import theater.suites.rest.ControllerTestBase;
import theater.utility.Dummy;

import java.util.List;

public class OrderControllerTest extends ControllerTestBase<Order> {
    private static final String url = "/api/order";

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    protected Order construct() {
        var hall = eraseAndCreate(hallRepository, Dummy::hall);
        var performance = eraseAndCreate(performanceRepository, Dummy::performance);
        var session = eraseAndCreate(sessionRepository, () -> Dummy.session(hall, performance));
        var client = eraseAndCreate(clientRepository, Dummy::client);
        return Dummy.order(session, client);
    }

    @Override
    protected List<Order> constructMultiple() {
        var halls = eraseAndCreateMultiple(hallRepository, Dummy::halls);
        var performances = eraseAndCreateMultiple(performanceRepository, Dummy::performances);
        var sessions = eraseAndCreateMultiple(sessionRepository, () -> Dummy.sessions(halls, performances));
        var clients = eraseAndCreateMultiple(clientRepository, Dummy::clients);
        return Dummy.orders(sessions, clients);
    }
}