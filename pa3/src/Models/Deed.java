package Models;

public class Deed extends Tile{

    //final deed variables
    private final int saleValue;
    private final int propertySet;
    private final int baseRent;
    private final int rentWithColorSet;
    private final int rentWithOneHouse;
    private final int rentWithTwoHouses;
    private final int rentWithThreeHouses;
    private final int rentWithFourHouses;
    private final int rentWithHotel;
    private final int mortgageValue;
    private final int houseCost;
    private final int hotelCost;


    //ownership variables
    private Player owner = null;
    private int houses = 0;

    //constructor
    public Deed(int saleValue, int propertySet, int baseRent,
                int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses,
                int rentWithHotel, int mortgageValue,
                int houseCost, int hotelCost,
                String name, int position){

        this.saleValue = saleValue;
        this.propertySet = propertySet;
        this.baseRent = baseRent;
        this.rentWithColorSet = baseRent*2;
        this.rentWithOneHouse = rentWithOneHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
        this.mortgageValue = mortgageValue;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;

        //parent class variables
        super.name = name;
        super.type = "Deed";
        super.position = position;
    }

    //****************GETTERS************************************
    //returns the value of a property's mortgage
    public int getMortgageValue(){
        return mortgageValue;
    }

    /*returns the color set of the property
     *legend:
     * 0: brown
     * 1: light blue
     * 2: pink
     * 3: orange
     * 4: red
     * 5: yellow
     * 6: green
     * 7: dark blue
     * 8: RR
     * 9: Utility
     */
    public int getPropertySet(){
        return propertySet;
    }

    //returns the sale price of a property
    public int getPrice(){
        return saleValue;
    }

    //returns the house cost of a property
    public int getHouseCost(){
        return houseCost;
    }

    //returns the hotel cost of a property
    public int getHotelCost(){
        return hotelCost;
    }

    //****************SETTERS************************************
    //sets owner of property
    public void setOwner(Player possessor){
        owner = possessor;
    }

    //adds a house or hotel to a property
    //1 indicates full propertySet
    //6 indicates hotel
    public void setHouses(){
        if(houses < 7){
            houses++;
        }
    }

    //****************METHODS************************************
    //returns the rent amount, depending on the number of houses/hotels present on a property
    public int calcRent(){
        switch(houses){
            case 1:
                return rentWithColorSet;
            case 2:
                return rentWithOneHouse;
            case 3:
                return rentWithTwoHouses;
            case 4:
                return rentWithThreeHouses;
            case 5:
                return rentWithFourHouses;
            case 6:
                return rentWithHotel;
            default:
                return baseRent;

        }
    }

    //returns owner of space if there is an owner, or returns null if there is no owner
    public Player isOwned(){
        if (owner != null){
            return owner;
        }
        else
            return null;
    }
}
