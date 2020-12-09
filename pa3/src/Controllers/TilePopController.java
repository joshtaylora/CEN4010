package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TilePopController {

// =====================================================================================================================
// ============================================ TilePopup FXML fields ==================================================
    @FXML Label tileName;
    @FXML Label tileMessage;
    @FXML Button button1;
    @FXML Button button2;

    private int[] ret = {0, 0};
    private int cost;

    //TODO: return array correctly
    /**
     *
     * @param name name of the tile
     * @param type type of the tile
     * @param ownershipStatus who owns the property
     * @param cost the money involved
     * @return returns an integer array, where [0] indicates adding(1) or subtracting(0) from player account,
     * and [1] indicates the value to be added or subtracted
     */
    public void tileSetup(String name, String type, int ownershipStatus, int cost) {
        button2.setVisible(true);
        this.cost = cost;
        tileName.setText(name);
        switch(type){
            case "Deed":
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    tileMessage.setText("Congrats, you own this! Want to mortgage?");
                    button1.setText("Yes, mortgage!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay Rent.");
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
            case "Go":
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
                tileMessage.setText("WEE WOO WE EWOO WEE WOO");
                button1.setText("Continue");
                button2.setVisible(false);
                ret[0] = 0;
                ret[1] = 0;
                break;
            case "RailRoad":
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    tileMessage.setText("Congrats, you own this! Want to mortgage?");
                    button1.setText("Yes, mortgage!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay Rent.");
                    button1.setText("Pay Rent");
                    button2.setVisible(false);
                    ret[0] = 0;
                    ret[1] = cost;
                }
                break;
            case "Utility":
                if(ownershipStatus == 0){
                    tileMessage.setText("No one owns this. Want to buy it?");
                    button1.setText("Yes, buy it!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else if(ownershipStatus == 1){
                    tileMessage.setText("Congrats, you own this! Want to mortgage?");
                    button1.setText("Yes, mortgage!");
                    button2.setText("No thanks");
                    ret[0] = 0;
                    ret[1] = 0;
                }
                else{
                    tileMessage.setText("Aw, someone else owns this! Pay Rent.");
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

    public void onButtonPress(){

    }

    public int[] getMoneyInfo(){
        return ret;
    }
}
