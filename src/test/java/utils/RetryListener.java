package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * RetryListener - Automatically applies RetryAnalyzer to all test methods
 * This ensures all tests use the retry mechanism without manual annotation
 */
public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                         Constructor testConstructor, Method testMethod) {
        // Apply RetryAnalyzer to all test methods
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
