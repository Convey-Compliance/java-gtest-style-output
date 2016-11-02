package GTestStyleOutput;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.LinkedList;

public class GTestStyleJUnitTestCase {
    private static long _startTime;

    public static int TotalTestCount = 0;
    public static long ClassStartTime = 0;
    public static int ClassTestCount = 0;
    public static int PassedCount = 0;
    public static int FailedCount = 0;
    public static int SkippedCount = 0;
    public final static LinkedList<Description> FailedTests = new LinkedList<>();
    public final static LinkedList<Description> SkippedTests = new LinkedList<>();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Rule
        public TestName testName = new TestName();

        @Override
        protected void starting(Description description) {
            _startTime = System.currentTimeMillis();
            System.err.println("[ RUN      ] " + description.getMethodName());
            ClassTestCount++;
            TotalTestCount++;
        }

        @Override
        protected void succeeded(Description description) {
            System.err.println("[       OK ] " + description.getMethodName() + " (" + (System.currentTimeMillis() - _startTime) + " ms)");
            PassedCount++;
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.err.println(e.getMessage());
            System.err.println("[  FAILED  ] " + description.getMethodName() + " (" + (System.currentTimeMillis() - _startTime) + " ms)");
            FailedCount++;
            FailedTests.add(description);
        }

        @Override
        protected void skipped(org.junit.AssumptionViolatedException e, Description description) {
            System.err.println(e.getMessage());
            System.err.println("[  SKIPPED ] " + description.getMethodName());
            SkippedCount++;
            SkippedTests.add(description);
        }
    };
}
