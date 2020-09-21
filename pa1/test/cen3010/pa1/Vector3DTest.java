package cen4010.pa1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Vector3DTest {

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
