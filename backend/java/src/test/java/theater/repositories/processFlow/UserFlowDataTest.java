package theater.repositories.processFlow;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Repeat;
import theater.domain.entities.Client;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.entities.Session;
import theater.repositories.*;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserFlowDataTest extends RESTTestBase {
    private static final String url = "/api/complex/";

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    OrderRepository orderRepository;

    private Hall hall = Dummy.hall();

    private Timestamp[] dates = new Timestamp[] {
            newDate(2010, 10, 3, 16, 0, 0),
            newDate(2016, 9, 3, 16, 0, 0),
            newDate(2016, 9, 3, 19, 30, 0),
            newDate(2016, 9, 3, 20, 30, 0),
    };

    private Performance[] performances = new Performance[] {
            new Performance("Author 1", "Title 1"),
            new Performance("Author 1", "Title 2")
    };

    private Client[] clients = new Client[] {
            new Client("333111222", "Client 1"),
            new Client("example@mail.com", "Client 2"),
            new Client("777777777", "Client 3")
    };

    private Session[] sessions;

    @SuppressWarnings("SameParameterValue")
    private static Timestamp newDate(int y, int m, int d, int h, int mm, int ss) {
        var cal = Calendar.getInstance();
        cal.set(y, m, d, h, mm, ss);
        return new Timestamp(cal.getTime().getTime());
    }

    @Test
    public void testEnvironment() {
        assertThat(hallRepository).isNotNull();
        assertThat(sessionRepository).isNotNull();
        assertThat(performanceRepository).isNotNull();
        assertThat(orderRepository).isNotNull();
        assertThat(clientRepository).isNotNull();
    }

    @Before
    public void setUp() throws Exception {
        hallRepository.deleteAll();
        sessionRepository.deleteAll();
        performanceRepository.deleteAll();
        clientRepository.deleteAll();
        orderRepository.deleteAll();

        hallRepository.save(hall);

        var plen = performances.length;
        var dlen = dates.length;

        sessions = new Session[plen * dlen];

        for (int i = 0; i < plen; i++) {
            Performance p = performances[i];
            performanceRepository.save(p);
            for (int i1 = 0; i1 < dlen; i1++) {
                sessionRepository.save(sessions[i * dlen + i1] = new Session(hall, performances[i], dates[i1]));
            }
        }
        for (var c : clients) {
            clientRepository.save(c);
        }
    }

    @Test
    @Repeat(3)
    public void simpleSetUpTest() {

    }



    @Test
    public void dummyHallNothingReservedTest() throws Exception {
        mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
