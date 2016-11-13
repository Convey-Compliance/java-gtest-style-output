package GTestStyleOutput;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class GTestStyleJUnitSuite extends Suite{
    public static final long SuiteStartTime = System.currentTimeMillis();

    public GTestStyleJUnitSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    private void PrintRunPreface() {
        System.err.println("[==========] Running JUnit tests");
        System.err.println("[----------] Classes loaded:");
        for(Runner runner : getChildren()) {
            System.err.println("             " + runner.getDescription().getClassName());
        }
    }

    private static void PrintSuitePreface(Runner runner){
        System.err.println("[----------] Running " + runner.testCount() + " tests from TestCase " + runner.getDescription().getClassName());
        GTestStyleJUnitTestCase.ClassTestCount = 0;
        GTestStyleJUnitTestCase.ClassStartTime = System.currentTimeMillis();
    }

    private static void PrintSuitePostface(Runner runner) {
        System.err.println("[----------] " + GTestStyleJUnitTestCase.ClassTestCount + " test from " + runner.getDescription().getClassName() + " (" + (System.currentTimeMillis() - GTestStyleJUnitTestCase.ClassStartTime) + " ms total)");
        System.err.println();
    }

    private static void PrintRunPostface() {
        System.err.println("[----------] Global JUnit test environment tear-down");
        System.err.println("[==========] " + GTestStyleJUnitTestCase.TotalTestCount + " test cases run. (" + (System.currentTimeMillis() - SuiteStartTime) + " ms total)");

        if(GTestStyleJUnitTestCase.PassedCount > 0)
            System.err.println("[  PASSED  ] " + GTestStyleJUnitTestCase.PassedCount + " tests.");
        if(GTestStyleJUnitTestCase.SkippedCount > 0) {
            System.err.println("[  SKIPPED ] " + GTestStyleJUnitTestCase.SkippedCount + " tests.");
            for(Description description : GTestStyleJUnitTestCase.SkippedTests) {
                System.err.println("[  SKIPPED ] " + description.getClassName());
            }
        }
        if(GTestStyleJUnitTestCase.FailedCount > 0) {
            System.err.println("[  FAILED  ] " + GTestStyleJUnitTestCase.FailedCount + " tests.");
            for(Description description : GTestStyleJUnitTestCase.FailedTests) {
                System.err.println("[  FAILED  ] " + description.getClassName());
            }
        }
    }

    @Override
    protected void runChild(Runner runner, RunNotifier notifier) {
        if(getChildren().get(0) == runner)
            PrintRunPreface();

        PrintSuitePreface(runner);
        runner.run(notifier);
        PrintSuitePostface(runner);

        if(getChildren().get(getChildren().size() - 1) == runner)
            PrintRunPostface();
    }
}

