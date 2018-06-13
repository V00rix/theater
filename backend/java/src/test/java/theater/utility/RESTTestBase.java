package theater.utility;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import theater.TheaterApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheaterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan("theater.controllers")
public abstract class RESTTestBase {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;
}
