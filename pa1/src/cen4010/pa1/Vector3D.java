package cen4010.pa1;

import java.lang.*;

public class Vector3D {
	
	private double x;
	private double y;
	private double z;

	public Vector3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// computes the dot product for the Vector3D object v passed as the arguement
	// 		vector dot product = vector q (dot) vector v 
	// 		= q(x) * v(x) + q(y) * v(y) + q(z) * v(z)
	public double dot(Vector3D v){
		double dotP = this.x * v.x 
				+ this.y * v.y 
				+ this.z * v.z;
		return dotP;
	}
	
	// computes the magnitude of the Vector3D object that this instance method is invoked on
	// 		magnitude of a vector = |vector v| 
	// 		= the square root of ( v(x) squared + v(y) squared + v(z) squared )
	public double magnitude() {
		double mag = Math.sqrt(this.x * this.x 
				+ this.y * this.y 
				+ this.z * this.z);
		return mag;
	}
	
	//scales each vector coordinate by value "f"
	//returns a new object with scaled coordinates
	public Vector3D scale(double f){
		Vector3D created = new Vector3D(x*f, y*f, z*f);
		return created;
	}
	
	//adds "this" vector coordinates to input vector coordinates
	//returns a new object with added coordinates
	public Vector3D add(Vector3D v) {
		Vector3D created = new Vector3D(this.x+v.x, this.y+v.y, this.z+v.z);
		return created;
	}

}
