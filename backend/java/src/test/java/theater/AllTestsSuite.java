package theater;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.suites.ControllersSuite;
import theater.suites.EnvironmentSuite;
import theater.suites.EntitiesSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EnvironmentSuite.class,
        EntitiesSuite.class,
        ControllersSuite.class
})
public class AllTestsSuite {
}
