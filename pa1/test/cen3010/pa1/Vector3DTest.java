import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Vector3DTest {

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
