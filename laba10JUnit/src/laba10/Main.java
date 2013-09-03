package laba10;

import java.util.Enumeration;

import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import interfaces.junit.JunitTester;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        JunitTester junitTester = new JunitTesterImpl();
        TestSuite suite = junitTester.suite();
        TestResult result = new TestResult();
        suite.run(result);

        Enumeration<TestFailure> failures = result.failures();
        Enumeration<TestFailure> errors = result.errors();

        System.out.println("Was failure: " + failures.hasMoreElements());
        System.out.println("Was error: " + errors.hasMoreElements());

        System.out
                .println("suite.countTestCases() = " + suite.countTestCases());

        System.out.println("end");
    }

}
