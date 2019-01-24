package managers;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ScreenshotListener implements TestRule {

    public class ScreenshotListenerStatement extends Statement {
        private Statement baseStatement;

        public ScreenshotListenerStatement(Statement b) {
            baseStatement = b;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                baseStatement.evaluate();
            } catch (Error e) {
                System.out.println("I take a Screenshot");
                throw e;
            } finally {
                after();
            }
        }

        //Put your after code in this method!
        public void after() {
            System.out.println("I am after");
        }
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new ScreenshotListenerStatement(statement);
    }
}