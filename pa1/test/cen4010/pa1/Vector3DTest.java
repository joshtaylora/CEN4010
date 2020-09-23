package cen4010.pa1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cen4010.pa1.Vector3D;

class Vector3DTest {

	@Test
	void testDot() {
		Vector3D v1 = new Vector3D(5.00, 3.60, 4.50);
		Vector3D v2 = new Vector3D(5.00, 3.60, 4.50);
		double testD = 5.00 * 5.00 + 3.60 * 3.60 + 4.50 *4.50;
		double result = v1.dot(v2);
		System.out.println(result);
		assertEquals(result, testD);
	}
	
	@Test
	void testMagnitude() {
		Vector3D v1 = new Vector3D(5.00, 3.60, 4.50);
		double magnitude = v1.magnitude();
		double testM = Math.sqrt(5.00 * 5.00 + 3.60 * 3.60 + 4.50 * 4.50);
		assertEquals(magnitude, testM);
	}
	
	//checking the unit scale as returning (a copy of) the same vector,
	//and scaling by some value to make test multiplying each coordinate. 
	@Test
	void testScale() {
		Vector3D orig = new Vector3D(1, 2, 3);
		
		Vector3D scaledCorrect = new Vector3D(2, 4, 6);
		Vector3D outrightWrong = new Vector3D(15, 5, 0);
		Vector3D xWrong = new Vector3D(0, 4, 6);
		Vector3D yWrong = new Vector3D(2, 0, 6);
		Vector3D zWrong = new Vector3D(2, 4, 0);
		
		Vector3D testee = orig.scale(2);
		
		//basic equality test
		assertTrue(testee.equals(scaledCorrect));
		
		//self-equality
		assertFalse(testee.equals(orig));
		
		//equality to a different object (all wrong)
		assertFalse(testee.equals(outrightWrong));
		
		//first coordinate wrong
		assertFalse(testee.equals(xWrong));
		
		//second coordinate wrong
		assertFalse(testee.equals(yWrong));
		
		//third coordinate wrong
		assertFalse(testee.equals(zWrong));
	}
	
	//simple tests
	@Test
	void testAdd() {
		Vector3D origOne = new Vector3D(1, 2, 3);
		Vector3D origTwo = new Vector3D(1, 2, 3);
		
		Vector3D addCorrect = new Vector3D(2, 4, 6);
		Vector3D outrightWrong = new Vector3D(15, 5, 0);
		Vector3D xWrong = new Vector3D(0, 4, 6);
		Vector3D yWrong = new Vector3D(2, 0, 6);
		Vector3D zWrong = new Vector3D(2, 4, 0);
		
		Vector3D testee = origOne.add(origTwo);
		
		//basic equality test
		assertTrue(testee.equals(addCorrect));
		
		//self-equality
		assertFalse(testee.equals(origOne));
		assertFalse(testee.equals(origTwo));
		
		
		//equality to a different object (all wrong)
		assertFalse(testee.equals(outrightWrong));
		
		//first coordinate wrong
		assertFalse(testee.equals(xWrong));
		
		//second coordinate wrong
		assertFalse(testee.equals(yWrong));
		
		//third coordinate wrong
		assertFalse(testee.equals(zWrong));
	}
}
