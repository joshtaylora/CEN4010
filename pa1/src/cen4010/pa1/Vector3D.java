/*Current class for Vector3D until combining.*/
public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//This function is intended to take in the value of another vector and subtract it from the calling vector. Returning the new Vector
	public Vector3D subtract(Vector3D sub) {
		Vector3D result = new Vector3D(this.x-sub.x, this.y-sub.y, this.z-sub.z);
		return result;
	}
	
	//This function negates all of the values (i.e. multiplying each value by -1). Returning the new negated vector
	public Vector3D negate() {
		Vector3D result = new Vector3D(this.x * -1, this.y * -1, this.z * -1);
		return result;
	}
	
	public boolean equals(Vector3D vector1, Vector3D vector2){
	      if(vector1.x == vector2.x && vector1.y == vector2.y && vector1.z == vector2.z){
	        System.out.print("The two vectors are equal\n");
	        return true;
	      }
	      else{
	        System.out.print("The two vectors are not equal\n");
	        return false;
	      }
	 }
	
}
