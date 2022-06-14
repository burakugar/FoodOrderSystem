package util;

import java.lang.System;

import static org.testng.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class TestingFood {

	@Test
	void TS_FOOD_001() {
		Food obj = new Food();
		Food param = new Food();
		int expectedResult;
		
		//Prices are equal so expected result is 0
		obj.setPrice(1.95);
		param.setPrice(1.95);
		expectedResult=0;
		
		assertEquals(expectedResult,obj.TestingCompareTo(param)); 
		
		System.out.println("-----TS_FOOD_001_001-----");
		if( expectedResult == obj.TestingCompareTo(param)) {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST PASSED!");
		}
		else {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST FAILED!");
		}
		
		//Obj price is higher than parameter price so expected result is 1
		obj.setPrice(3.97);
		param.setPrice(1.95);
		expectedResult=1;
				
		assertEquals(expectedResult,obj.TestingCompareTo(param));
		
		System.out.println("-----TS_FOOD_001_002-----");
		if( expectedResult == obj.TestingCompareTo(param)) {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST PASSED!");
		}
		else {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST FAILED!");
		}
		
		//Obj price is lower than parameter price so expected result is -1
		obj.setPrice(3.97);
		param.setPrice(5.99);
		expectedResult=-1;
						
		assertEquals(expectedResult,obj.TestingCompareTo(param));
		
		System.out.println("-----TS_FOOD_001_003-----");
		if( expectedResult == obj.TestingCompareTo(param)) {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST PASSED!");
		}
		else {
			System.out.println("Expected Result -> " + expectedResult + " Test Result -> " + obj.TestingCompareTo(param));
			System.out.println("TEST FAILED!");
		}
		
	}

}
