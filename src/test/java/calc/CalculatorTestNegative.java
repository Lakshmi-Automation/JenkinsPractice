package calc;

import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.Test;

import basicfunc.Base;


public class CalculatorTestNegative extends Base {
    private Calculator calculator;
	@Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
    	int i=1/0;
    }

    @Test
    public void testAddNegative() {
        assertNotEquals(5, calculator.sum(2, 2),"Addition failed");
        extentTest.info("Addition Failed");
    }

    @Test
    public void testSubtractNegative() {
        assertNotEquals(5, calculator.sub(3, 2),"Subtract failed");
        extentTest.info("Subtraction Failed");
    }
    
    @Test
    public void testMultiplyNegative() {
        assertNotEquals(15, calculator.mult(3, 4),"Multiplication failed");
        extentTest.info("Multiplication Failed");
    }

    @Test
    public void testDivideNegative() {
        assertNotEquals(3, calculator.div(10, 3),"Division failed");
    }
    
}
