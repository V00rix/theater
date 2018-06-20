package theater.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.suites.environment.ApplicationSmokeTest;
import theater.suites.environment.ControllersSmokeTest;
import theater.suites.environment.RepositoriesSmokeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationSmokeTest.class,
        ControllersSmokeTest.class,
        RepositoriesSmokeTest.class
})
public class EnvironmentSuite {
}
