package theater.suites.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import theater.TestBase;
import theater.TheaterApplication;
import theater.rest.controllers.HallController;
import theater.rest.controllers.PerformanceController;
import theater.rest.controllers.TheaterController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheaterApplication.class)
@ComponentScan("theater.rest")
public class ControllersSmokeTest extends TestBase {

    @Autowired
    private PerformanceController performanceController;

    @Autowired
    private TheaterController theaterController;

    @Autowired
    private HallController hallController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(performanceController).isNotNull();
        assertThat(theaterController).isNotNull();
        assertThat(hallController).isNotNull();
    }
}