package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer - Implements retry logic for failed test cases
 * This class will retry failed tests up to a maximum number of attempts
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() +
                             " for the " + retryCount + " time(s).");
            return true; // Retry the test
        }
        return false; // Don't retry
    }
}
