
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

	@Test
	void testEquals(){
		boolean equivalent;
		Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
		Vector3D2 vector1 = new Vector3D(2.0, 4.0, 6.0);
		Vector3D2 vector2 = new Vector3D(1.0, 2.0, 9.0);
		equivalent = vector.equals(vector1);
		assertFalse(equivalent);
		equivalent = vector.equals(vector);
		assertTrue(equivalent);
		equivalent = vector.equals(vector2);
		assertFalse(equivalent);

	}

	@Test
	void testToString(){
		Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
		String expected = "X coordinate: 1.0 \n Y coordinate: 2.0 \n Z coordinate: 3.0 \n";
		assertEquals(expected, vector.toString());
	}

	@Test
	void testSubtract() {
		Vector3D sub = new Vector3D(6, 5, 4);
		Vector3D otherSub = new Vector3D(3, 2, 7);
		Vector3D resultSub = sub.subtract(otherSub);
		Vector3D correctAnswer = new Vector3D(3, 3, -3);
		Vector3D falseAnswer = new Vector3D(1, 2, 3);
		//Base check for correctness
		assertTrue(resultSub.equals(resultSub, correctAnswer));
		//Base check for incorrect result
		assertFalse(resultSub.equals(resultSub, falseAnswer));
		//Checking to make sure the function is immutable
		assertFalse(sub.equals(sub, resultSub));
	}
	
	@Test
	void testNegate() {
		Vector3D neg = new Vector3D(4, 5, 6);
		Vector3D resultNeg = neg.negate();
		Vector3D correctAnswer = new Vector3D(-4, -5, -6);
		Vector3D falseAnswer = new Vector3D(1, 2, 3);
		//Base check for correctness
		assertTrue(resultNeg.equals(resultNeg, correctAnswer));
		//Base check for incorrect result
		assertFalse(resultNeg.equals(resultNeg, falseAnswer));
		//Checking to make sure the function is immutable
		assertFalse(neg.equals(neg, resultNeg));
	}
}
