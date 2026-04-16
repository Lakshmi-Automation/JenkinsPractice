package calc;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import basicfunc.Base;

public class Parameterization extends Base {
	
	// Columns = {value1, value2, expected_result}
    @DataProvider(name = "additionDataProvider")
    public Object[][] dataProviderAddMethod() {
        return new Object[][] { 
        	{ 2, 3, 5 }, 
        	{ -2, 6, 4 }, 
        	{ 7, 3, 10 }, 
        	{ -4, -1, -5 } 
        	};
    }
    
 // Columns = {value1, value2, expected_result}
    @DataProvider(name = "subtractionDataProvider")
    public Object[][] dataProviderSubMethod() {
        return new Object[][] { 
        	{ 2, 3, -1 }, 
        	{ -2, 6, -8 }, 
        	{ 7, 3, 4 }, 
        	{ -4, -1, -3 }
        	};
    }

    // Parameterized test using DataProvider
    @Test(dataProvider = "additionDataProvider")
    public void testAdditionWithDataProvider(int value1, int value2, int expected) {
        assertEquals(calculator.sum(value1, value2), expected);
        extentTest.info("Addition using dataprovider");
    }
    
    @Test(dataProvider = "subtractionDataProvider")
    public void testSubtractionWithDataProvider(int value1, int value2, int expected) {
        assertEquals(calculator.sub(value1, value2), expected);
    }
    
}
