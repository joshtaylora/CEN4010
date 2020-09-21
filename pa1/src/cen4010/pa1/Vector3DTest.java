import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Vector3DTest {

	@Test
	void testSubtract() {
		Vector3D subtract = new Vector3D(6, 5, 4);
		Vector3D otherSub = new Vector3D(3, 2, 7);
		Vector3D resultSub = subtract.Vector3DSubtract(otherSub);
		Vector3D correctAnswer = new Vector3D(3, 3, -3);
		assertTrue(resultSub.Vequals(resultSub.x, resultSub.y, resultSub.z, correctAnswer.x, correctAnswer.y, correctAnswer.z));
	}
	
	@Test
	void testNegate() {
		Vector3D negate = new Vector3D(4, 5, 6);
		Vector3D resultNeg = negate.Vector3DNegate();
		Vector3D correctAnswer = new Vector3D(-4, -5, -6);
		assertTrue(resultNeg.Vequals(resultNeg.x, resultNeg.y, resultNeg.z, correctAnswer.x, correctAnswer.y, correctAnswer.z));
	}

}
