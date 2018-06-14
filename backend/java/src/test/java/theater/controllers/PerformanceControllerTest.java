package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PerformanceControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/performance/";

    @Autowired
    PerformanceRepository performanceRepository;

    @Override
    @Test
    public void getTest() throws Exception {
        performanceRepository.deleteAll();
        var performances = new Performance[]{
                new Performance("author 1", "title 1"),
                new Performance("author 1TWO", "title 1"),
                new Performance("author 1", "title THREE"),
                new Performance("author Four", "Fourth"),
        };

        for (var performance : performances) {
            performanceRepository.save(performance);
        }


        var res = mockMvc.perform(get(url + "names")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        for (var p : performances) {
            res.andExpect(jsonPath("$." + p.getId(), is(p.title + " - " + p.author)));
        }
    }

    @Test
    public void getSpecificPerformanceTest() throws Exception {
        performanceRepository.deleteAll();
        var performances = new Performance[]{
                new Performance("author 1", "title 1"),
                new Performance("author 1TWO", "title 1"),
                new Performance("author 1", "title THREE"),
                new Performance("author Four", "Fourth"),
        };

        for (var performance : performances) {
            performanceRepository.save(performance);

            var res = mockMvc.perform(get(url + performance.getId().toString())).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(mapper.writeValueAsString(performance)));
        }
    }

    @Override
    @Test
    public void postTest() throws Exception {
        RESTTest.defaultPostTest(performanceRepository, Dummy::performance, mockMvc, url + "new");
    }

    @Test
    public void updatePerformanceTest() throws Exception {

        // Create entity
        performanceRepository.deleteAll();
        var entityFirst = Dummy.performance();

        // Save
        performanceRepository.save(entityFirst);

        // Change
        var entitySecond = new Performance("new author", "new title");

        // Perform request
        mockMvc.perform(post(url + entityFirst.getId())
                .content(mapper.writeValueAsString(entitySecond))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = performanceRepository.findAll().get(0);

        assert created != null;
        assert !created.equals(entityFirst);
        assert created.equals(entitySecond);
    }

    @Test
    public void deletePerformanceTest() throws Exception {
        // Create entity
        performanceRepository.deleteAll();
        var entityFirst = Dummy.performance();
        performanceRepository.save(entityFirst);

        // Perform request
        mockMvc.perform(delete(url + entityFirst.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        var performances = performanceRepository.findAll();

        assert performances.isEmpty();

    }
}