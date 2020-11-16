//ADD X AND Y COORDINATES
public class Token{
  string name = "";
  int xCoor, yCoor;

  public int getLocation(Tile tile){
    return tile.getPosition();
  }

  public void setX(int num){
    xCoor = num;
  }

  public int getX(){
    return xCoor;
  }

  public void setY(int num){
    yCoor = num;
  }

  public int getY(){
    return yCoor;
  }
}