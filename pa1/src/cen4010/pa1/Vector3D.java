package cen4010.pa1;

public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	//default constructor(unused)
	public Vector3D() {}
	
	//constructor to set vector values
	public Vector3D(double givex, double givey, double givez) {
		x = givex;
		y = givey;
		z = givez;
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
