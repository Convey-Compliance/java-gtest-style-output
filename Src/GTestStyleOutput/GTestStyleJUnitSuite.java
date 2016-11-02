package GTestStyleOutput;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class GTestStyleJUnitSuite extends Suite{
    public GTestStyleJUnitSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    @Override
    protected void runChild(Runner runner, RunNotifier notifier) {
        runner.run(notifier);
        if(getChildren().get(getChildren().size() - 1) == runner) {
            System.err.println("[----------] " + GTestStyleJUnitTestCase.ClassTestCount + " test from " + GTestStyleJUnitTestCase.CurrentRunningClass + " (" + (System.currentTimeMillis() - GTestStyleJUnitTestCase.ClassStartTime) + " ms total)");
            System.err.println("");
            System.err.println("[----------] Global JUnit test environment tear-down");
            System.err.println("[==========] " + GTestStyleJUnitTestCase.TotalTestCount + " test cases run. (" + (System.currentTimeMillis() - GTestStyleJUnitTestCase.SuiteStartTime) + " ms total)");
            if(GTestStyleJUnitTestCase.SkippedCount > 0)
                System.err.println("[  SKIPPED ] " + GTestStyleJUnitTestCase.SkippedCount + " tests.");
            if(GTestStyleJUnitTestCase.PassCount > 0)
                System.err.println("[  PASSED  ] " + GTestStyleJUnitTestCase.PassCount + " tests.");
            if(GTestStyleJUnitTestCase.FailedCount > 0)
                System.err.println("[  FAILED  ] " + GTestStyleJUnitTestCase.FailedCount + " tests.");
        }
    }
}

