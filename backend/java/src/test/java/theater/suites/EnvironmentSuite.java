package theater.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import theater.environment.ApplicationSmokeTest;
import theater.environment.ControllersSmokeTest;
import theater.environment.RepositoriesSmokeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationSmokeTest.class,
        ControllersSmokeTest.class,
        RepositoriesSmokeTest.class
})
public class EnvironmentSuite {
}
