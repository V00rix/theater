package theater.controllers;

import org.junit.Test;
import theater.utility.RESTTestBase;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TheaterControllerTest extends RESTTestBase {

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/api/theater/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mock")));
    }
}