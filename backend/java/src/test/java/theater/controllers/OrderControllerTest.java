package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.domain.entities.*;
import theater.repositories.*;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/order/";

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

    private Order order;
    //endregion

    private void createOrder() {
        performanceRepository.deleteAll();
        performance = Dummy.performance();
        performanceRepository.save(performance);

        hallRepository.deleteAll();
        hall = Dummy.hall();
        hallRepository.save(hall);

        sessionRepository.deleteAll();
        session = Dummy.session(hall, performance);
        sessionRepository.save(session);

        clientRepository.deleteAll();
        client = Dummy.client();
        clientRepository.save(client);

        orderRepository.deleteAll();
        order = Dummy.order(session, client);
        orderRepository.save(order);
    }

    @Override
    @Test
    public void getTest() throws Exception {
        createOrder();

        this.mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Override
    @Test
    public void postTest() throws Exception {
        //        RESTTest.defaultPostTest(theaterRepository, Dummy::theater, mockMvc, url + "new");
    }

    @Test
    public void updateTest() throws Exception {
        //        RESTTest.defaultUpdateTest(theaterRepository, Dummy::theater, () -> {
        //            var newTheater = Dummy.theater();
        //            newTheater.name = "Second Name";
        //            newTheater.address = "1 Box 2 Left 10 Above the Skies";
        //            return newTheater;
        //        }, mockMvc, url);
    }
}