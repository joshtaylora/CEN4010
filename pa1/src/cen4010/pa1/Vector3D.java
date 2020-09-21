/*Current class for Vector3D until combining.*/
public class Vector3D {
	public double x;
	public double y;
	public double z;
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//This function is intended to take in the value of another vector and subtract it from the calling vector. Returning the new Vector
	public Vector3D Vector3DSubtract(Vector3D sub) {
		Vector3D result = new Vector3D(this.x-sub.x, this.y-sub.y, this.z-sub.z);
		return result;
	}
	
	//This function negates all of the values (i.e. multiplying each value by -1). Returning the new negated vector
	public Vector3D Vector3DNegate() {
		Vector3D result = new Vector3D(this.x * -1, this.y * -1, this.z * -1);
		return result;
	}
	
	public boolean Vequals(double x1, double y1, double z1, double x2, double y2, double z2) {
		if(x1 == x2 && y1 == y2 && z1 == z2) {
			System.out.print("True");
			return true;
		}
		else {
			System.out.print("False");
			return false;
		}
	}
	
}
