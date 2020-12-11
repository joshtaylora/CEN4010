package Controllers;

import Models.*;
import Resources.ImageContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TilePopController {

// =====================================================================================================================
// ============================================ TilePopup FXML fields ==================================================
    @FXML Label tileName;
    @FXML TextArea tileMessage;
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

    @FXML Label purchasePriceLabel;
    @FXML Label rentLabel;
    @FXML Label rentWith1HouseLabel;
    @FXML Label rentWith2HousesLabel;
    @FXML Label rentWith3HousesLabel;
    @FXML Label rentWith4HousesLabel;
    @FXML Label rentWithHotelLabel;
    @FXML Label mortgageValueLabel;
    @FXML Label houseCostLabel;
    @FXML Label hotelCostLabel;

    @FXML Label railRoadLabel;

// =====================================================================================================================
// ============================================ Global Variables =======================================================

    MainController mainController;

    private Tile tile;
    private String type;
    private int ownershipStatus;
    private Player rollingPlayer;
    private int rollSum;
    private boolean canBuy;

    /* Moved ImageContainer declaration to be a global variable so it isn't instantiated every method call for getTileImage */
    ImageContainer imgContainer = new ImageContainer();


    public void injectMain(MainController mainController){
        this.mainController = mainController;
    }

    /**
     * passes all values needed for buttons to work, then builds the TileView
     *
     * @param ownershipStatus who owns the property(0 is no one, 1 is current player, 2 is anyone else)
     * @param tile the Tile the player is on
     * @param rollingPlayer the currentPlayer
     * @param rollSum the sum of the dice roll (needed for utility rent)
     */
    public void initTile(Tile tile, int ownershipStatus, Player rollingPlayer, int rollSum){
        this.tile = tile;
        this.type = tile.getType();
        this.ownershipStatus = ownershipStatus;
        this.rollingPlayer = rollingPlayer;
        this.rollSum = rollSum;
        this.canBuy = (rollingPlayer.getAccBalance() > 0);

        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(false);
        housePane.setVisible(false);
        houseHotelMessage.setVisible(false);
        tileSetup();
    }

// =====================================================================================================================
// ============================================ TileView Configurations ================================================

    private void tileSetup() {
        tileName.setText(tile.getName());
        setTileImage();
        switch(type){
            case "Deed":
                tileSetupDeed();
                break;
            case "LuxuryTax":
                tileSetupLuxuryTax();
                break;
            case "IncomeTax":
                tileSetupIncomeTax();
                break;
            case "GO":
                tileSetupGO();
                break;
            case "Jail":
                tileSetupJail();
                break;
            case "GoToJail":
                tileSetupGoToJail();
                break;
            case "RailRoad":
                tileSetupRailRoad();
                break;
            case "Utility":
                tileSetupUtility();
                break;
            case "none":
                tileSetupNone();
                break;
            default:
                break;
        }
    }

    private void tileSetupDeed() {
        Deed obj = (Deed) tile;
        housePane.setVisible(true);
        upgradeCircles(obj.getHouses());
        setDeedImageLabels(obj);
        //if player does not have money
        if(!canBuy){
            if(ownershipStatus == 1 && !(obj.getMortgaged())){
                tileMessage.setText("Property already owned, want to mortgage for $" + obj.getMortgageValue() + "?");
                button1.setText("Yes, mortgage!");
                button2.setText("No thanks");
                return;
            }
            tileMessage.setText("You have no money!");
            button2.setText("Continue");
            button1.setVisible(false);
            return;
        }

        //if player does have money
        if(ownershipStatus == 0){
            tileMessage.setText("No one owns this. Want to buy it for $" + obj.getPrice() + "?");
            button1.setText("Yes, buy it!");
            button2.setText("No thanks");
        }
        else if(ownershipStatus == 1){
            //check property mortgage status
            if(obj.getMortgaged()){
                tileMessage.setText("Congrats, you own this! Want to unmortgage for $" + obj.getMortgageValue() + "?");
                button1.setText("Yes, unmortgage!");
            }
            else{
                tileMessage.setText("Congrats, you own this! Want to mortgage for $" + obj.getMortgageValue() + "?");
                button1.setText("Yes, mortgage!");
            }
            button2.setText("No thanks");
            //check if property can be upgraded
            if(obj.getUpgradeCost() < 0){
                button3.setText("Upgrade Property");
                houseHotelMessage.setText("or do you want to upgrade for $" + obj.getUpgradeCost() + "?");
                button3.setVisible(true);
                houseHotelMessage.setVisible(true);
            }
        }
        else{
            tileMessage.setText("Aw, someone else owns this! Pay rent for $" + obj.calcRent() + ".");
            button1.setText("Pay Rent");
            button2.setVisible(false);
        }

    }


    private void setDeedImageLabels(Deed deed) {
        String purchasePriceStr = "PURCHASE PRICE $";
        String rentStr = "Rent $";
        String house1Str = "With 1 House   $";
        String house2Str = "With 2 Houses  $";
        String house3Str = "With 3 Houses  $";
        String house4Str = "With 4 Houses  $";
        String hotelStr =  "With HOTEL     $";
        String mortgageValueStr = "Mortgage Value $";
        String houseCost = "Houses cost $";
        String hotelCost = "Hotel cost $";
        purchasePriceLabel.setText(purchasePriceStr.concat(Integer.toString(deed.getPrice())));
        rentLabel.setText(rentStr.concat(Integer.toString(deed.getBaseRent())));
        rentWith1HouseLabel.setText(house1Str.concat(Integer.toString(deed.getRentWithOneHouse())));
        rentWith2HousesLabel.setText(house2Str.concat(Integer.toString(deed.getRentWithTwoHouses())));
        rentWith3HousesLabel.setText(house3Str.concat(Integer.toString(deed.getRentWithThreeHouses())));
        rentWith4HousesLabel.setText(house4Str.concat(Integer.toString(deed.getRentWithFourHouses())));
        rentWithHotelLabel.setText(hotelStr.concat(Integer.toString(deed.getRentWithHotel())));
        mortgageValueLabel.setText(mortgageValueStr.concat(Integer.toString(deed.getMortgageValue())));
        houseCostLabel.setText(houseCost.concat(Integer.toString(deed.getHouseCost())));
        hotelCostLabel.setText(hotelCost.concat(Integer.toString(deed.getHotelCost())));
        showDeedLabels();
    }

    /**
     * method used to hide labels that are displayed for Deed objects
     */
    public void hideDeedLabels() {
        railRoadLabel.setVisible(false);
        purchasePriceLabel.setVisible(false);
        rentLabel.setVisible(false);
        rentWith1HouseLabel.setVisible(false);
        rentWith2HousesLabel.setVisible(false);
        rentWith3HousesLabel.setVisible(false);
        rentWith4HousesLabel.setVisible(false);
        rentWithHotelLabel.setVisible(false);
        mortgageValueLabel.setVisible(false);
        houseCostLabel.setVisible(false);
        hotelCostLabel.setVisible(false);
    }

    /**
     * method used to show previously hidden labels that are displayed for Deed objects
     */
    public void showDeedLabels() {
        // hide railroad label if visible
        railRoadLabel.setVisible(false);
        tileName.setVisible(true);
        purchasePriceLabel.setVisible(true);
        rentLabel.setVisible(true);
        rentWith1HouseLabel.setVisible(true);
        rentWith2HousesLabel.setVisible(true);
        rentWith3HousesLabel.setVisible(true);
        rentWith4HousesLabel.setVisible(true);
        rentWithHotelLabel.setVisible(true);
        mortgageValueLabel.setVisible(true);
        houseCostLabel.setVisible(true);
        hotelCostLabel.setVisible(true);
    }

    private void tileSetupLuxuryTax() {
        hideDeedLabels();
        //if player has no money
        if(!canBuy){
            tileMessage.setText("You have no money!");
            button2.setText("Continue");
            button1.setVisible(false);
            return;
        }
        //if player does have money
        tileMessage.setText("Time to pay your taxes! That'll be $75");
        button1.setText("Pay Tax");
        button2.setVisible(false);
    }

    private void tileSetupIncomeTax() {
        hideDeedLabels();
        //if player has no money
        if(!canBuy){
            tileMessage.setText("You have no money!");
            button2.setText("Continue");
            button1.setVisible(false);
            return;
        }
        //if player does have money
        tileMessage.setText("Time to pay your taxes! That'll be $200");
        button1.setText("Pay Tax");
        button2.setVisible(false);
    }

    private void tileSetupGO() {
        tileName.setVisible(false);
        hideDeedLabels();
        tileMessage.setText("You passed GO! Here's you $200 payday");
        button1.setText("Continue");
        button2.setVisible(false);
    }

    private void tileSetupJail() {
        tileName.setVisible(false);
        hideDeedLabels();
        tileMessage.setText("Don't forget that prison is legal slavery!");
        button1.setText("Continue");
        button2.setVisible(false);
    }

    private void tileSetupGoToJail() {
        tileName.setVisible(false);
        hideDeedLabels();
        tileMessage.setText("WEE WOO WEE WOO WEE WOO");
        button1.setText("Continue");
        button2.setVisible(false);
    }

    private void tileSetupRailRoad() {
        tileName.setVisible(false);
        hideDeedLabels();
        RailRoad obj2 = (RailRoad) tile;

        railRoadLabel.setText(obj2.getName());
        railRoadLabel.setVisible(true);

        rentWith2HousesLabel.setText("PURCHASE PRICE $200");
        rentWith3HousesLabel.setText("Rent                    $25");
        rentWith4HousesLabel.setText("If 2 R.R.'s are owned   $50");
        rentWithHotelLabel.setText("If 3 \" \" \"          $100");
        mortgageValueLabel.setText("If 4 \" \" \"          $200");
        hotelCostLabel.setText("Mortgage Value         $100");
        rentWith2HousesLabel.setVisible(true);
        rentWith3HousesLabel.setVisible(true);
        rentWith4HousesLabel.setVisible(true);
        rentWithHotelLabel.setVisible(true);
        mortgageValueLabel.setVisible(true);
        hotelCostLabel.setVisible(true);

        //if player does not have money
        if(!canBuy){
            if(ownershipStatus == 1 && !(obj2.getMortagaged())){
                tileMessage.setText("Congrats, you own this! Want to mortgage for $" + obj2.getMortgageValue() + "?");
                button1.setText("Yes, mortgage!");
                button2.setText("No thanks");
                return;
            }
            tileMessage.setText("You have no money!");
            button2.setText("Continue");
            button1.setVisible(false);
            return;
        }

        //if player does have money
        if(ownershipStatus == 0){
            tileMessage.setText("No one owns this. Want to buy it for $" + obj2.getPrice() + "?");
            button1.setText("Yes, buy it!");
            button2.setText("No thanks");
        }
        else if(ownershipStatus == 1){
            if(obj2.getMortagaged()){
                tileMessage.setText("Congrats, you own this! Want to unmortgage for $" + obj2.getMortgageValue() + "?");
                button1.setText("Yes, unmortgage!");
            }
            else{
                tileMessage.setText("Congrats, you own this! Want to mortgage for $" + obj2.getMortgageValue() + "?");
                button1.setText("Yes, mortgage!");
            }
            button2.setText("No thanks");
        }
        else{
            tileMessage.setText("Aw, someone else owns this! Pay rent for $" + obj2.calcRent() + ".");
            button1.setText("Pay Rent");
            button2.setVisible(false);
        }
    }

    private void tileSetupUtility() {
        hideDeedLabels();
        //tileName.setVisible(true);
        

        if(!canBuy){
            tileMessage.setText("You have no money!");
            button2.setText("Continue");
            button1.setVisible(false);
            return;
        } /* Had to rename util to utility because util is a semi-keyword in Java */
        Utility utility = (Utility) tile;

        railRoadLabel.setText(utility.getName());

        if(ownershipStatus == 0){
            tileMessage.setText("No one owns this. Want to buy it for $" + utility.getPrice() + "?");
            button1.setText("Yes, buy it!");
            button2.setText("No thanks");
        }
        else if(ownershipStatus == 1){
            tileMessage.setText("Congrats, you own this! Whew");
            button1.setText("Continue");
            button2.setVisible(false);
        }
        else{
            tileMessage.setText("Aw, someone else owns this! Pay rent for $" + utility.calcRent(rollSum) + ".");
            button1.setText("Pay Rent");
            button2.setVisible(false);
        }
    }

    private void tileSetupNone() {
        if (tile.getName().equals("Water Works")) {
            railRoadLabel.setVisible(true);
            railRoadLabel.setText("Water Works");
        }
        else {
            tileName.setVisible(true);
        }

        hideDeedLabels();
        tileMessage.setText("Welcome! There's nothing to do here.");
        button1.setText("Continue");
        button2.setVisible(false);
    }

// =====================================================================================================================
// ============================================ Buttons ================================================================
    /**
     * button method handling buying, mortgaging, paying rent, paying tax, and passing GO
     */
    @FXML
    public void onButtonOne(){
        switch(type){
            case "Deed":
            case "RailRoad":
            case "Utility":
                if(ownershipStatus == 0){
                    rollingPlayer.purchaseProperty(tile);
                }
                 else if(ownershipStatus == 1){
                    rollingPlayer.mortgageProperty(tile);
                }
                else if(ownershipStatus == 2){
                    rollingPlayer.rentProperty(tile, rollSum);
                }
                break;
            case "LuxuryTax":
                rollingPlayer.payTax(tile);
                break;
            case "IncomeTax":
                rollingPlayer.payTax(tile);
                break;
            case "GO":
                rollingPlayer.passGO();
                break;
            default:
                break;
        }

        mainController.selectGameTab();
        mainController.removeTileTab();
    }

    /**
     * button method handling no thanks responses
     */
    public void onButtonTwo(){
        mainController.removeTileTab();
    }

    /**
     * button method handling the purchase of houses or hotels
     */
    public void onButtonThree(){
        rollingPlayer.upgradeProperty(tile);
        mainController.removeTileTab();
    }

// =====================================================================================================================
// ============================================ Circle updates ==================================================
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

    @FXML
    private void setTileImage(){
        Image tileImages = null;
        switch(type){
            case "Deed":
                Deed obj = (Deed) tile;
                tileImages = imgContainer.getTileImage(obj.getPropertySet());
                tileImage.setImage(tileImages);
                break;
            case "Utility":
               Utility obj2 = (Utility) tile;
               if(obj2.getName().equals("Electric Company")){
                   tileImage.setImage(imgContainer.getTileImage(13));
               }
               else{
                   tileImage.setImage(imgContainer.getTileImage(8));
               }
                break;
            case "RailRoad":
                // RailRoad railRoad = (RailRoad) tile;
                tileImage.setImage(imgContainer.getTileImage(10));
                break;
            case "Jail":
                tileImage.setImage(imgContainer.getTileImage(16));
                break;
            case "Go":
                tileImage.setImage(imgContainer.getTileImage(14));
                break;
            case "GoToJail":
                tileImage.setImage((imgContainer.getTileImage(15)));
                break;
            default:
                tileImage.setImage(imgContainer.getTileImage(9));
                break;
        }
    }

}
