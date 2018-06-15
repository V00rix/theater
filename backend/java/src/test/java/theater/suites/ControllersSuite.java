package theater.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.controllers.HallControllerTest;
import theater.controllers.PerformanceControllerTest;
import theater.controllers.SessionControllerTest;
import theater.controllers.TheaterControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HallControllerTest.class,
        PerformanceControllerTest.class,
        SessionControllerTest.class,
        TheaterControllerTest.class,
})
public class ControllersSuite {
}
