package calc;

import org.testng.Assert;
import org.testng.annotations.Test;
import basicfunc.Base;
import utils.RetryAnalyzer;

/**
 * RetryTestDemo - Demonstrates retry mechanism for failed tests
 * Test 1: Always passes
 * Test 2: Always fails
 * Test 3: Fails first time, passes on retry
 */
public class RetryTestDemo extends Base {

    // Counter to track retry attempts for Test 3
    private static int test3AttemptCount = 0;

    /**
     * Test 1: This test will always pass
     */
    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void test1_AlwaysPass() {
        System.out.println("Executing Test 1 - This test will pass");
        extentTest.info("Test 1 - Always Pass");

        long result = calculator.sum(5, 3);
        Assert.assertEquals(result, 8L, "Addition of 5 + 3 should be 8");

        extentTest.pass("Test 1 passed successfully!");
        System.out.println("✓ Test 1 PASSED");
    }

    /**
     * Test 2: This test will always fail (even after retries)
     */
    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void test2_AlwaysFail() {
        System.out.println("Executing Test 2 - This test will fail");
        extentTest.info("Test 2 - Always Fail");

        long result = calculator.sum(2, 2);
        // Intentionally wrong assertion - will always fail
        Assert.assertEquals(result, 5L, "This assertion will fail - 2 + 2 is not 5");

        extentTest.fail("Test 2 failed as expected!");
        System.out.println("✗ Test 2 FAILED");
    }

    /**
     * Test 3: This test will fail on first attempt but pass on retry
     * Simulates a flaky test that succeeds after retry
     */
    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void test3_PassOnRetry() {
        test3AttemptCount++;

        System.out.println("Executing Test 3 - Attempt #" + test3AttemptCount);
        extentTest.info("Test 3 - Pass on Retry (Attempt #" + test3AttemptCount + ")");

        // Simulate a flaky condition - fail on first attempt, pass on retry
        if (test3AttemptCount == 1) {
            System.out.println("⚠ Test 3 - First attempt, simulating failure...");
            extentTest.warning("First attempt - simulating temporary failure");

            // This will fail the test on first attempt
            Assert.fail("Simulated failure on first attempt - will retry");

        } else {
            System.out.println("✓ Test 3 - Retry attempt successful!");
            extentTest.info("Retry attempt #" + (test3AttemptCount - 1));

            // On retry, test will pass
            long result = calculator.mult(4, 5);
            Assert.assertEquals(result, 20L, "Multiplication of 4 * 5 should be 20");

            extentTest.pass("Test 3 passed on retry!");
            System.out.println("✓ Test 3 PASSED on retry");
        }
    }

    /**
     * Additional Example: Test with custom retry logic based on exception type
     */
    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void test4_DivisionRetry() {
        System.out.println("Executing Test 4 - Division test");
        extentTest.info("Test 4 - Division Test");

        double result = calculator.div(10, 2);
        Assert.assertEquals(result, 5.0, 0.01, "Division of 10 / 2 should be 5.0");

        extentTest.pass("Test 4 passed!");
        System.out.println("✓ Test 4 PASSED");
    }
}
