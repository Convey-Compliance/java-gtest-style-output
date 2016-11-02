package GTestStyleOutput;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class GTestStyleJUnitTestCase {
    public static long ClassStartTime;
    private static long _startTime;
    public static int ClassTestCount;
    public static String CurrentRunningClass = "";
    public static int TotalTestCount = 0;
    public static long SuiteStartTime = System.currentTimeMillis();
    public static int PassCount = 0;
    public static int FailedCount = 0;
    public static int SkippedCount = 0;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        private String TestName(Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            return className + "." + methodName;
        }

        @Rule
        public TestName testName = new TestName();

        @Override
        protected void starting(Description description) {
            if(!CurrentRunningClass.equals(description.getClassName())) {
                if(CurrentRunningClass.equals(""))
                    System.err.println("[==========] Running JUnit tests");
                else {
                    System.err.println("[----------] " + ClassTestCount + " test from " + CurrentRunningClass + " (" + (System.currentTimeMillis() - ClassStartTime) + " ms total)");
                    System.err.println();
                }
                System.err.println("[----------] Running tests from class " + description.getClassName());
                CurrentRunningClass = description.getClassName();
                ClassTestCount = 0;
                ClassStartTime = System.currentTimeMillis();
            }
            _startTime = System.currentTimeMillis();
            System.err.println("[ RUN      ] " + TestName(description));
            ClassTestCount++;
            TotalTestCount++;
        }

        @Override
        protected void succeeded(Description description) {
            System.err.println("[       OK ] " + TestName(description) + " (" + (System.currentTimeMillis() - _startTime) + " ms)");
            PassCount++;
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.err.println(e.getMessage());
            System.err.println("[  FAILED  ] " + TestName(description) + " (" + (System.currentTimeMillis() - _startTime) + " ms)");
            FailedCount++;
        }

        @Override
        protected void skipped(org.junit.AssumptionViolatedException e, Description description) {
            System.err.println(e.getMessage());
            System.err.println("[  SKIPPED ] " + TestName(description));
            SkippedCount++;
        }
    };
}
