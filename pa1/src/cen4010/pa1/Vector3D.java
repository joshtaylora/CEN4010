package cen4010.pa1;
import java.lang.*;

public class Vector3D {
	
	private double xCoord;
	private double yCoord;
	private double zCoord;

	public Vector3D(double x, double y, double z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}
	
	public double dot(Vector3D v){
		double dotP = this.xCoord * v.xCoord 
				+ this.yCoord * v.yCoord 
				+ this.zCoord * v.zCoord;
		return dotP;
	}
	
	public double magnitude() {
		double mag = Math.sqrt(this.xCoord * this.xCoord 
				+ this.yCoord * this.yCoord 
				+ this.zCoord * this.zCoord);
		return mag;
	}
	
}
