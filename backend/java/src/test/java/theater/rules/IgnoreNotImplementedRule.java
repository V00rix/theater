package theater.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import theater.domain.exceptions.NotImplementedException;

public class IgnoreNotImplementedRule implements TestRule {
    @Override
    public Statement apply(Statement statement, Description description) {
        return new MyStatement(statement);
    }

    class MyStatement extends Statement {

        private final Statement base;

        MyStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            // Rules go here
            try {
                base.evaluate();
            } catch (NotImplementedException ignored) {
            }
        }
    }
}

