package theater;

import org.junit.Rule;
import theater.rules.IgnoreNotImplementedRule;

public abstract class TestBase {

    /**
     * Ignores NotImplementedExceptions.
     * Comment to fail on NotImplementedException.
     */
    @Rule
    public IgnoreNotImplementedRule ignoreNotImplemented = new IgnoreNotImplementedRule();
}
