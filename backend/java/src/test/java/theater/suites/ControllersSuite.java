package theater.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.rest.controllers.ClientController;
import theater.rest.controllers.OrderController;
import theater.suites.rest.controllers.HallControllerTest;
import theater.suites.rest.controllers.PerformanceControllerTest;
import theater.suites.rest.controllers.SessionControllerTest;
import theater.suites.rest.controllers.TheaterControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TheaterControllerTest.class,
        HallControllerTest.class,
        PerformanceControllerTest.class,
        ClientController.class,
        SessionControllerTest.class,
        OrderController.class
})
public class ControllersSuite {
}
