package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import basicfunc.Base;

public class CalculatorTestPositive extends Base {
    private Calculator calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();  // Initialize calculator before each test
    }
    // Positive test cases
    @Test(groups="fail")
    public void testAdd() { 
    	assertEquals(4, calculator.sum(2, 2)); 
    	extentTest.info("Addition failed");
    	}

    @Test(groups="pass")
    public void testSubtract() { 
    	assertEquals(2, calculator.sub(4, 2)); }
    
    @Test(groups="pass")
    public void testMultiply() { 
    	assertEquals(4, calculator.mult(2, 2)); 
    	}

  /*  @Test(groups="pass")
    public void testDivide() { 
    	assertEquals(2.0, calculator.div(4, 2), 0.00); 
    	}*/

    @Test(groups="pass")
    public void testPower() {
    	assertEquals(8, calculator.power(2, 3));
    	}
    
    
    
}

