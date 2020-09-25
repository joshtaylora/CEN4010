import java.lang.*;
package cen4010.pa1;

public class Vector3D{
  private double x;
  private double y;
  private double z;

    public Vector3D( double x, double y, double z){
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public String toString(){
      return "X coordinate: " + this.x + "\n" + "Y coordinate: " + this.y + "\n" + "Z coordinate: " + this.z + "\n";
    }

    public boolean equals(Vector3D vector){
      if(this.x == vector.x && this.y == vector.y && this.z == vector.z){
        return true;
      }
      else{
        return false;
      }
    }

	
	public double dot(Vector3D v){
		double dotP = this.x * v.x 
				+ this.y * v.y 
				+ this.z * v.z;
		return dotP;
	}
	
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
}

