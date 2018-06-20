package theater.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.suites.repositories.entities.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientTest.class,
        HallTest.class,
        OrderTest.class,
        PerformanceTest.class,
        SessionTest.class,
        TheaterTest.class
})
public class EntitiesSuite {
}
