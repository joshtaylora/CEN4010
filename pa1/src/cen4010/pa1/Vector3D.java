import java.lang.*;

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
      return "X coordinate: " + this.xPt + "\n" + "Y coordinate: " + this.yPt + "\n" + "Z coordinate: " + this.zPt + "\n";
    }

    public boolean equals(Vector3D vector){
      if(this.x == vector.x && this.y == vector.y && this.z == vector.z){
        return true;
      }
      else{
        return false;
      }
    }
}