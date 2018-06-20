package theater.suites.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import theater.TestBase;
import theater.TheaterApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheaterApplication.class)
public class ApplicationSmokeTest extends TestBase {

    @Test
    public void contextLoads() throws Exception {
    }
}