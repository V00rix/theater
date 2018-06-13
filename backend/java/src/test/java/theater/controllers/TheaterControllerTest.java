package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.repositories.TheaterRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TheaterControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/theater/";

    @Autowired
    TheaterRepository theaterRepository;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get(url + "greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mock")));
    }


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
    public void postTest() {

    }
}