package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.repositories.TheaterRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TheaterControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/theater/";

    @Autowired
    TheaterRepository theaterRepository;

    @Override
    @Test
    public void getTest() throws Exception {
        // Create theater
        theaterRepository.deleteAll();
        var theater = Dummy.theater();
        theaterRepository.save(theater);

        // Perform request
        this.mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(theater)));
    }

    @Override
    @Test
    public void postTest() throws Exception {
        // Create theater
        theaterRepository.deleteAll();
        var theater = Dummy.theater();

        // Perform request
        this.mockMvc.perform(post(url + "new")
                .content(mapper.writeValueAsString(theater))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = theaterRepository.findAll().get(0);

        assert created != null;
        assert created.equals(theater);
    }

    @Test
    public void updateTest() throws Exception {

        // Create theater
        theaterRepository.deleteAll();
        var theater = Dummy.theater();

        // Save
        theaterRepository.save(theater);

        // Change
        var newTheater = Dummy.theater();
        newTheater.name = "Second Name";
        newTheater.address = "1 Box 2 Left 10 Above the Skies";

        // Perform request
        this.mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(newTheater))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = theaterRepository.findAll().get(0);

        assert created != null;
        assert !created.equals(theater);
        assert created.equals(newTheater);
    }
}