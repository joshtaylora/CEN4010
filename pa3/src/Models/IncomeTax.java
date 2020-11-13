package Models;

public class IncomeTax extends Tile {
    private final int tax = 200;

    public IncomeTax(String name, String type, int position){
        super.name = name;
        super.type = type;
        super.position = position;
    }

    public int getTax(){
        return tax;
    }
}
