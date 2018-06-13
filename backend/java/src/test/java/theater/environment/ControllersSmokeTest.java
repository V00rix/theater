package theater.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import theater.TheaterApplication;
import theater.controllers.HallController;
import theater.controllers.PerformanceController;
import theater.controllers.StatusController;
import theater.controllers.TheaterController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheaterApplication.class)
@ComponentScan("theater.controllers")
public class ControllersSmokeTest {

    @Autowired
    private StatusController statusController;

    @Autowired
    private PerformanceController performanceController;

    @Autowired
    private TheaterController theaterController;

    @Autowired
    private HallController hallController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(statusController).isNotNull();
        assertThat(performanceController).isNotNull();
        assertThat(theaterController).isNotNull();
        assertThat(hallController).isNotNull();
    }
}