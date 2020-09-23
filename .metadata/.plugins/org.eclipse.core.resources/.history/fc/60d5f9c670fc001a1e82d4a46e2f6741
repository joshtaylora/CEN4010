package cen3010.pa1;

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
	

}
