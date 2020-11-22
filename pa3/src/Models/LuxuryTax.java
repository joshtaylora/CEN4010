package Models;

public class LuxuryTax extends Tile {
    private final int tax = 75;

    public LuxuryTax(String name, String type, int position){
        super.name = name;
        super.type = type;
        super.position = position;
    }

    public int getTax(){
        return tax;
    }
}
