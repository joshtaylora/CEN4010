import java.lang.*;

public class Vector3D{
  private double xPt;
  private double yPt;
  private double zPt;

    public Vector3D( double x, double y, double z){
      xPt = x;
      yPt = y;
      zPt = z;
    }

    public String toString(){
      return "X coordinate: " + this.xPt + "\n" + "Y coordinate: " + this.yPt + "\n" + "Z coordinate: " + this.zPt + "\n";
    }

    public boolean equals(Vector3D vector1, Vector3D vector2){
      if(abs(vector1.getx) == abs(vector2.getx) && abs(vector1.gety) == abs(vector2.gety) && abs(vector1.getz) == abs(vector2.getz)){
        System.out.print("The two vectors are equal\n");
        return true;
      }
      else{
        System.out.print("The two vectors are not equal\n");
        return false;
      }
    }
}