package theater.controllers;

import org.junit.Test;
import theater.utility.RESTTestBase;

import static org.assertj.core.api.Assertions.assertThat;


public class TheaterControllerTest extends RESTTestBase implements RESTTest {
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/theater/",
                String.class)).contains("Hello World");
    }

    @Override
    @Test
    public void getTest() {

    }

    @Override
    @Test
    public void postTest() {

    }
}
