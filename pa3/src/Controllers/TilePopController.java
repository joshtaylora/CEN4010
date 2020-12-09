package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TilePopController {

// =====================================================================================================================
// ============================================ TilePopup FXML fields ==================================================
    @FXML Label tileName;
    @FXML Label tileMessage;
    @FXML Label houseHotelMessage;
    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    @FXML GridPane housePane;
    @FXML ImageView tileImage;
    @FXML Circle circle1;
    @FXML Circle circle2;
    @FXML Circle circle3;
    @FXML Circle circle4;
    @FXML Circle circle5;
    @FXML Circle circle6;

    MainController mainController;

    private int[] ret = {0, 0};
    private int cost;
    private String type;
    private int ownershipStatus;
    private Player rollingPlayer;
    private boolean purchased;
    private Tile tile;

    public void injectMain(MainController mainController){
        this.mainController = mainController;
    }

    /**
     *
     * @param ownershipStatus who owns the property
     * @param cost the money involved
     * ret array -> [0] indicates adding(1) or subtracting(0) from player account,
     * and [1] indicates the value to be added or subtracted
     */
    public void tileSetup(Tile tile, int ownershipStatus, int cost, Player rollingPlayer) {
        button2.setVisible(true);
        button3.setVisible(false);
        housePane.setVisible(false);
        houseHotelMessage.setVisible(false);
        purchased = false;

        this.tile = tile;
        this.cost = cost;
        this.type = tile.getType();
        this.ownershipStatus = ownershipStatus;
        this.rollingPlayer = rollingPlayer;

        tileName.setText(tile.getName());

        switch(type){
            case "Deed":
                Deed obj = (Deed) tile;
                housePane.setVisible(true);
                upgradeCircles(obj.getHouses());
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it for $" + cost + "?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    //check property mortgage status
                    if(obj.getMortagaged()){
                        tileMessage.setText("Congrats, you own this! Want to unmortgage for $" + cost + "?");
                        button1.setText("Yes, unmortgage!");
                        ret[0] = 0;
                    }
                    else{
                        tileMessage.setText("Congrats, you own this! Want to mortgage for $" + cost + "?");
                        button1.setText("Yes, mortgage!");
                        ret[0] = 1;
                    }
                    button2.setText("No thanks");
                    //check if property can be upgraded
                    if(obj.getUpgradeCost() < 0){
                        button3.setText("Upgrade Property");
                        houseHotelMessage.setText("or do you want to upgrade for $" + obj.getUpgradeCost() + "?");
                        button3.setVisible(true);
                        houseHotelMessage.setVisible(true);
                    }
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay rent for $" + cost + ".");
                    button1.setText("Pay Rent");
                    button2.setVisible(false);
                    ret[0] = 0;
                    ret[1] = cost;
                }
                break;
            case "LuxuryTax":
                tileMessage.setText("Time to pay your taxes! That'll be $75");
                button1.setText("Pay Tax");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 75;
                break;
            case "IncomeTax":
                tileMessage.setText("Time to pay your taxes! That'll be $200");
                button1.setText("Pay Tax");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 200;
                break;
            case "GO":
                tileMessage.setText("You passed GO! Here's you $200 payday");
                button1.setText("Continue");
                button2.setVisible(false);
                ret[0] = 1;
                ret[1] = 200;
                break;
            case "Jail":
                tileMessage.setText("Don't forget that prison is legal slavery!");
                button1.setText("Continue");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 0;
                break;
            case "GoToJail":
                tileMessage.setText("WEE WOO WEE WOO WEE WOO");
                button1.setText("Continue");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 0;
                break;
            case "RailRoad":
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it for $" + cost + "?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    RailRoad obj2 = (RailRoad) tile;
                    if(obj2.getMortagaged()){
                        tileMessage.setText("Congrats, you own this! Want to unmortgage for $" + cost + "?");
                        button1.setText("Yes, unmortgage!");
                        ret[0] = 0;
                    }
                    else{
                        tileMessage.setText("Congrats, you own this! Want to mortgage for $" + cost + "?");
                        button1.setText("Yes, mortgage!");
                        ret[0] = 1;
                    }
                    button2.setText("No thanks");
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay rent for $" + cost + ".");
                    button1.setText("Pay Rent");
                    button2.setVisible(false);
                    ret[0] = 0;
                    ret[1] = cost;
                }
                break;
            case "Utility":
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it for $" + cost + "?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    tileMessage.setText("Congrats, you own this! Whew");
                    button1.setText("Continue");
                    button2.setVisible(false);
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay rent for $" + cost + ".");
                    button1.setText("Pay Rent");
                    button2.setVisible(false);
                    ret[0] = 0;
                    ret[1] = cost;
                }
                break;
            case "none":
                tileMessage.setText("Welcome! There's nothing to do here.");
                button1.setText("Continue");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 0;
                break;
            default:
                break;
        }
    }

    /**
     * button method handling purchasing or mortgaging of property cards
     */
    @FXML
    public void onButtonOne(){
        //don't change ret[1] if constants were set
        if(!(type.equals("LuxuryTax")) && !(type.equals("IncomeTax")) && !(type.equals("GO"))){
            ret[1] = cost;
            processProperties();
        }
        processMoneyInfo();
        // Josh - I added methods in the main controller to switch between tabs
        mainController.selectGameTab();
        mainController.removeTileTab();
    }

    /**
     * button method handling no thanks responses
     */
    public void onButtonTwo(){
        processMoneyInfo();
        mainController.removeTileTab();
    }

    /**
     * button method handling the purchase of houses or hotels
     */
    public void onButtonThree(){
        Deed obj = (Deed) tile;
        obj.setHouses();
        mainController.removeTileTab();
    }

    /**
     * updates the accounts of players during a transaction
     */
    private void processMoneyInfo(){
        //if: player is to lose money or stay the same
        if(ret[0] == 0){
            rollingPlayer.setAccBalance(rollingPlayer.getAccBalance() - ret[1]);
        }
        //else: player is to gain money
        else{
            rollingPlayer.setAccBalance(rollingPlayer.getAccBalance() + ret[1]);
        }

        //another player is owed money
        if(ownershipStatus == 2){
            switch (type) {
                case "Deed":
                    Deed obj = (Deed) tile;
                    obj.getOwner().setAccBalance(obj.getOwner().getAccBalance() + ret[1]);
                    break;
                case "RailRoad":
                    RailRoad obj2 = (RailRoad) tile;
                    obj2.getOwner();
                    obj2.getOwner().setAccBalance(obj2.getOwner().getAccBalance() + ret[1]);
                    break;
                case "Utility":
                    Utility obj3 = (Utility) tile;
                    obj3.getOwner();
                    obj3.getOwner().setAccBalance(obj3.getOwner().getAccBalance() + ret[1]);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * updates the properties owned by a player for two occurences:
     * 1. Player has purchased a property not previously owned
     * 2. Player has mortgaged or unmortgaged a property they own
     */
    private void processProperties() {
        //if: player purchased a property
        if (ownershipStatus == 0) {
            rollingPlayer.purchaseProperty(tile);
        }
        //else: player mortgaged a property
        else{
            switch (type) {
                case "Deed":
                    Deed obj = (Deed) tile;
                    obj.setMortagaged();
                    break;
                case "RailRoad":
                    RailRoad obj2 = (RailRoad) tile;
                    obj2.setMortagaged();
                    break;
                default:
                    break;
            }
        }
    }

    private void upgradeCircles(int houses){
        switch(houses){
            case 1:
                circle1.setFill(Color.BLACK);
            case 2:
                circle2.setFill(Color.BLACK);
            case 3:
                circle3.setFill(Color.BLACK);
            case 4:
                circle4.setFill(Color.BLACK);
            case 5:
                circle5.setFill(Color.BLACK);
            case 6:
                circle6.setFill(Color.BLACK);
            default:
                break;
        }
    }
}
