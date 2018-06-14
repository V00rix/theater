package theater.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.repositories.simpleCrud.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientTest.class,
        HallTest.class,
        OrderTest.class,
        PerformanceTest.class,
        SessionTest.class,
        TheaterTest.class
})
public class SimpleCRUDSuite {
}
