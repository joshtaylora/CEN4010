package Controllers;

import Models.Deed;
import Models.Player;
import Models.PropertySet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

public class TradeController {
    @FXML Label playerALabel;
    @FXML Label playerBLabel;

    @FXML
    SplitMenuButton playerAMenu;

    @FXML
    MenuItem playerAProperty1MenuItem;
    @FXML
    ChoiceBox<String> playerAProperty1ChoiceBox;

    @FXML
    MenuItem playerAProperty2MenuItem;
    @FXML
    ChoiceBox<String> playerAProperty2ChoiceBox;

    @FXML
    MenuItem playerAProperty3MenuItem;
    @FXML
    ChoiceBox<String> playerAProperty3ChoiceBox;

    @FXML
    SplitMenuButton playerBMenu;

    @FXML
    MenuItem playerBProperty1MenuItem;
    @FXML
    ChoiceBox<String> playerBProperty1ChoiceBox;

    @FXML
    MenuItem playerBProperty2MenuItem;
    @FXML
    ChoiceBox<String> playerBProperty2ChoiceBox;

    @FXML
    MenuItem playerBProperty3MenuItem;
    @FXML
    ChoiceBox<String> playerBProperty3ChoiceBox;
// =====================================================================================================================
    @FXML
    Label tradePopupLabel;
    @FXML
    ChoiceBox<String> tradePlayerChoiceBox;
    @FXML
    Button initiateTradeButton;
// =====================================================================================================================
// ============================================ Global Variables =======================================================

    MainController mainController;
    Player initiatingPlayer;
    Player receivingPlayer;
// =====================================================================================================================

    public void initTradeController(MenuController menuController) {
        this.mainController = menuController.mainController;
//        playerAProperty1ChoiceBox.getItems().add();
    }

    /**
     * Method to perform a trade between Player A and Player B
     * @param playerA player that initiated the trade
     * @param playerB player that is the recipient of the initial trade interaction from playerA
     */
    @FXML
    void performTrade(Player playerA, Player playerB) throws IOException {
        Parent parentNode = FXMLLoader.load(getClass().getResource("TradePopup.fxml"));
    }

    void populateTradeView(Player initiatingPlayer, Player receivingPlayer) {
        this.initiatingPlayer = initiatingPlayer;
        this.receivingPlayer = receivingPlayer;
        playerALabel.setText(this.initiatingPlayer.getName());

        playerBLabel.setText(this.receivingPlayer.getName());

        // Add all of the properties that the initiating player owns to the choice boxes for the initiating player
        initMenuForInitiatingPlayer();
        // Add all of the properties that the receiving player owns to the choice boxes for the receiving player
        initMenuForReceivingPlayer();
    }

    private void initMenuForInitiatingPlayer() {
        getDeedToDisplay(this.initiatingPlayer, playerAProperty1ChoiceBox);
        getDeedToDisplay(this.initiatingPlayer, playerAProperty2ChoiceBox);
        getDeedToDisplay(this.initiatingPlayer, playerAProperty3ChoiceBox);
    }

    private void initMenuForReceivingPlayer() {
        getDeedToDisplay(this.receivingPlayer, playerBProperty1ChoiceBox);
        getDeedToDisplay(this.receivingPlayer, playerBProperty2ChoiceBox);
        getDeedToDisplay(this.receivingPlayer, playerBProperty3ChoiceBox);
    }

    private void getDeedToDisplay(Player initiatingPlayer, ChoiceBox<String> playerChoiceBox) {
        for (PropertySet propertySet : initiatingPlayer.getPlayerPropertySetArray()) {
            for (Deed displayDeed : propertySet.getPropertiesInSet()) {
                if (displayDeed != null) {
                    String deedName = displayDeed.getName();
                    String displayStr = deedName.concat(", " + displayDeed.getPropertySet());
                    playerChoiceBox.getItems().add(displayStr);
                }
            }
        }
    }

    @FXML
    public void playerAProperty1ChoiceBoxSelected(Event e) {
        String prop2ChoiceBoxVal = playerAProperty2ChoiceBox.getSelectionModel().getSelectedItem();
        String prop3ChoiceBoxVal = playerAProperty3ChoiceBox.getSelectionModel().getSelectedItem();
        // If the player has already selected items in the other 2 choice boxes, concatenate the output string to reflect that
        String newMenuText;
        if ((prop2ChoiceBoxVal != null) && (prop3ChoiceBoxVal != null)) {
            newMenuText =  playerAProperty1ChoiceBox.getValue().split(",")[0];
            newMenuText = newMenuText.concat(" " + prop2ChoiceBoxVal.split(",")[0]);
            newMenuText = newMenuText.concat(" " + prop3ChoiceBoxVal.split(",")[0]);
        }
        else {
            newMenuText = playerAProperty1ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];
        }
        playerAMenu.setText(newMenuText);
        // Remove the property selected in Property1ChoiceBox from the Property 2 and 3 ChoiceBoxes
        playerAProperty2ChoiceBox.getItems().remove(playerAProperty1ChoiceBox.getValue());
        playerAProperty3ChoiceBox.getItems().remove(playerAProperty1ChoiceBox.getValue());

    }
    @FXML
    public void playerAProperty2ChoiceBoxSelected(Event e) {
        playerAMenu.setText(playerAProperty2ChoiceBox.getValue().split(",")[0]);
        // Remove the property selected in Property1ChoiceBox from the Property 1 and 3 ChoiceBoxes
        playerAProperty1ChoiceBox.getItems().remove(playerAProperty1ChoiceBox.getValue());
        playerAProperty3ChoiceBox.getItems().remove(playerAProperty3ChoiceBox.getValue());

    }

    @FXML
    public void playerAProperty3ChoiceBoxSelected(Event e) {
        playerAMenu.setText(playerAProperty3ChoiceBox.getValue().split(",")[0]);
        // Remove the property selected in Property1ChoiceBox from the Property 1 and 3 ChoiceBoxes
        playerAProperty1ChoiceBox.getItems().remove(playerAProperty1ChoiceBox.getValue());
        playerAProperty3ChoiceBox.getItems().remove(playerAProperty3ChoiceBox.getValue());

    }



    @FXML
    public void playerBProperty1ChoiceBoxSelected(Event e) {
        String prop2ChoiceBoxVal = playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem();
        String prop3ChoiceBoxVal = playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem();
        // If the player has already selected items in the other 2 choice boxes, concatenate the output string to reflect that
        String newMenuText;
        if ((prop2ChoiceBoxVal != null) && (prop3ChoiceBoxVal != null)) {
            newMenuText =  playerBProperty1ChoiceBox.getValue().split(",")[0];
            newMenuText = newMenuText.concat(" " + prop2ChoiceBoxVal.split(",")[0]);
            newMenuText = newMenuText.concat(" " + prop3ChoiceBoxVal.split(",")[0]);
        }
        else {
            newMenuText = playerBProperty1ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];
        }
        playerBMenu.setText(newMenuText);
        // Remove the property selected in Property1ChoiceBox from the Property 2 and 3 ChoiceBoxes
        playerBProperty2ChoiceBox.getItems().remove(playerBProperty1ChoiceBox.getValue());
        playerBProperty3ChoiceBox.getItems().remove(playerBProperty1ChoiceBox.getValue());

    }
    @FXML
    public void playerBProperty2ChoiceBoxSelected(Event e) {
        String prop1ChoiceBoxVal = playerBProperty1ChoiceBox.getSelectionModel().getSelectedItem();
        String prop3ChoiceBoxVal = playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem();
        // If the player has already selected items in the other 2 choice boxes, concatenate the output string to reflect that
        String newMenuText;
        if ((prop1ChoiceBoxVal != null) && (prop3ChoiceBoxVal != null)) {
            newMenuText =  prop1ChoiceBoxVal.split(",")[0];
            newMenuText = newMenuText.concat(" " + playerAProperty2ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0]);
            newMenuText = newMenuText.concat(" " + prop3ChoiceBoxVal.split(",")[0]);
        }
        else {
            newMenuText = playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];
        }
        playerBMenu.setText(newMenuText);
        // Remove the property selected in Property1ChoiceBox from the Property 2 and 3 ChoiceBoxes
        playerBProperty1ChoiceBox.getItems().remove(playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem());
        playerBProperty3ChoiceBox.getItems().remove(playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void playerBProperty3ChoiceBoxSelected(Event e) {
        String prop1ChoiceBoxVal = playerBProperty1ChoiceBox.getSelectionModel().getSelectedItem();
        String prop2ChoiceBoxVal = playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem();
        // If the player has already selected items in the other 2 choice boxes, concatenate the output string to reflect that
        String newMenuText;
        if ((prop1ChoiceBoxVal != null) && (prop2ChoiceBoxVal != null)) {
            newMenuText =  prop1ChoiceBoxVal.split(",")[0];
            newMenuText = newMenuText.concat(" " + prop2ChoiceBoxVal.split(",")[0]);
            newMenuText = newMenuText.concat(" " + playerAProperty3ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0]);
        }
        else {
            newMenuText = playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];
        }
        playerBMenu.setText(newMenuText);
        // Remove the property selected in Property1ChoiceBox from the Property 2 and 3 ChoiceBoxes
        playerBProperty1ChoiceBox.getItems().remove(playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem());
        playerBProperty2ChoiceBox.getItems().remove(playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem());
    }

}
