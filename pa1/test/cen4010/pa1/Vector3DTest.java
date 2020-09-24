import java.lang.*;

class Vector3DTest{

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
}