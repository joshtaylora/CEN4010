package Models;

public class Token{
  String name = "";

  public int getLocation(Tile tile){
    return tile.getPosition();
  }
}