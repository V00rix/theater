package theater.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.entities.Session;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.HallRepository;
import theater.repositories.PerformanceRepository;
import theater.repositories.SessionRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SessionControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/session/";

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    PerformanceRepository performanceRepository;

    private static Hall hall;
    private static Performance[] performances;
    private static Timestamp[] dates;

    @SuppressWarnings("SameParameterValue")
    private static Timestamp newDate(int y, int m, int d, int h, int mm, int ss) {
        var cal = Calendar.getInstance();
        cal.set(y, m, d, h, mm, ss);
        return new Timestamp(cal.getTime().getTime());
    }

    @Before
    public void setUp() throws Exception {
        hallRepository.deleteAll();
        performanceRepository.deleteAll();
        hall = Dummy.hall();
        hallRepository.save(hall);
        performances = new Performance[]{
                new Performance("author 1TWO", "title 1"),
                new Performance("author 1", "title THREE")};
        for (var p : performances) {
            performanceRepository.save(p);
        }
        dates = new Timestamp[]{
                newDate(2010, 10, 3, 16, 0, 0),
                newDate(2016, 9, 3, 16, 0, 0),
                newDate(2016, 9, 3, 19, 30, 0),
                newDate(2016, 9, 3, 20, 30, 0),
        };
    }

    @Override
    @Test
    public void getTest() throws Exception {
        sessionRepository.deleteAll();
        var sessions = new Session[]{
                new Session(hall, performances[0], dates[0]),
                new Session(hall, performances[0], dates[1]),
                new Session(hall, performances[0], dates[3]),
                new Session(hall, performances[1], dates[2]),
                new Session(hall, performances[1], dates[3]),
        };

        for (var session : sessions) {
            sessionRepository.save(session);
        }


        var res = mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        for (Session p : sessions) {
            res.andExpect(jsonPath("$."+ p.getId() + ".hall", is((p.hall.getId().intValue()))));
            res.andExpect(jsonPath("$."+ p.getId() + ".performance", is((p.performance.getId().intValue()))));
            // todo other expectations
        }
    }

    @Test
    public void getSpecificSessionTest() throws Exception {
        throw new NotImplementedException();
    }

    @Override
    @Test
    public void postTest() throws Exception {
        RESTTest.defaultPostTest(sessionRepository, () -> {
            return Dummy.session(hall, performances[0]);
        }, mockMvc, url + "new");
    }

    @Test
    public void updatePerformanceTest() throws Exception {
//
//        // Create entity
//        sessionRepository.deleteAll();
//        var entityFirst = Dummy.performance();
//
//        // Save
//        sessionRepository.save(entityFirst);
//
//        // Change
//        var entitySecond = new Performance("new author", "new title");
//
//        // Perform request
//        mockMvc.perform(post(url + entityFirst.id)
//                .content(mapper.writeValueAsString(entitySecond))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        // Check
//        var created = sessionRepository.findAll().get(0);
//
//        assert created != null;
//        assert !created.equalz(entityFirst);
//        assert created.equalz(entitySecond);
//    }
//
//    @Test
//    public void deletePerformanceTest() throws Exception {
//        // Create entity
//        sessionRepository.deleteAll();
//        var entityFirst = Dummy.performance();
//        sessionRepository.save(entityFirst);
//
//        // Perform request
//        mockMvc.perform(delete(url + entityFirst.id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//
//        var performances = sessionRepository.findAll();
//
//        assert performances.isEmpty();

    }
}