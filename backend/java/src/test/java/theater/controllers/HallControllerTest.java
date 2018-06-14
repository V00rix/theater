package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.repositories.HallRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HallControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/hall/";

    @Autowired
    HallRepository hallRepository;

    @Override
    @Test
    public void getTest() throws Exception {
        RESTTest.defaultGetTest(hallRepository, Dummy::hall, mockMvc, url);
    }

    @Override
    @Test
    public void postTest() throws Exception {
        RESTTest.defaultPostTest(hallRepository, Dummy::hall, mockMvc, url + "new");
    }

    @Test
    public void updateTest() throws Exception {
        hallRepository.deleteAll();
        var entityFirst = Dummy.hall();

        // Save
        hallRepository.save(entityFirst);

        // Change
        var entitySecond = Dummy.hall();
        entitySecond.name = "Hall TWO";

        var id = hallRepository.findAll().get(0).id;

        // Perform request
        mockMvc.perform(post(url + "/" + id)
                .content(mapper.writeValueAsString(entitySecond))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = hallRepository.findAll().get(0);

        assert created != null;
        assert !created.equals(entityFirst);
        assert created.equals(entitySecond);
    }
}