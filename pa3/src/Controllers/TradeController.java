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

    @FXML ChoiceBox<Integer> playerAMoneyBox;
    @FXML ChoiceBox<Integer> playerBMoneyBox;


    @FXML
    ChoiceBox<String> playerAProperty1ChoiceBox;
    @FXML
    ChoiceBox<String> playerAProperty2ChoiceBox;
    @FXML
    ChoiceBox<String> playerAProperty3ChoiceBox;

    @FXML
    ChoiceBox<String> playerBProperty1ChoiceBox;
    @FXML
    ChoiceBox<String> playerBProperty2ChoiceBox;
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
        // add options for the players to chose monetary options to add to their trade offers
        initPlayerMoneyBox(initiatingPlayer);
        initPlayerMoneyBox(receivingPlayer);

    }

    /**
     * Populate the combo box with possible values that the player can select to add to their trade offer
     * @param player the initiating player for the trade
     */
    private void initPlayerMoneyBox(Player player) {
        int playerMoney = player.getAccBalance();
        int option1 = (int)Math.floor(playerMoney * 0.20);
        int option2 = (int)Math.floor(playerMoney * 0.35);
        int option3 = (int)Math.floor(playerMoney * 0.50);
        int option4 = (int)Math.floor(playerMoney * 0.65);
        int option5 = (int)Math.floor(playerMoney * 0.80);
        int option6 = (int)Math.floor(playerMoney * 0.95);
        // Add all of the possible monetary trade offer options to the choice box list of items
        if (player.getName().equals(this.initiatingPlayer.getName())) {
            playerAMoneyBox.getItems().addAll(option1, option2, option3, option4, option5, option6);
        }
        else {
            playerBMoneyBox.getItems().addAll(option1, option2, option3, option4, option5, option6);
        }

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

    /**
     * Method to display all of the deeds that a player ones in a given choice box for the specified player
     * @param initiatingPlayer the player whose playerDeedSetArray will be queried
     * @param playerChoiceBox the choice box for the player that the deeds will be displayed in
     */
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

    private void updatePlayerChoiceBoxDisplay(ChoiceBox<String> playerChoiceBox) {
        // Store the string value for the item selected by the playerChoiceBox
        String selectedItem = playerChoiceBox.getSelectionModel().getSelectedItem();

        String choiceBoxIDStr = playerChoiceBox.getId();
        String choiceBoxOwner = choiceBoxIDStr.split("Property")[0];
        System.out.println(choiceBoxOwner);
        String selectedChoiceBoxNum = choiceBoxIDStr.split("Property")[1];
        System.out.println(selectedChoiceBoxNum);
        // Switch depending on what choice box is selected so that we can remove instances of duplicate deed names from
        // other choice boxes
        switch (choiceBoxOwner) {
            case "playerA":
                updateNotSelectedChoiceBoxes(selectedItem, selectedChoiceBoxNum, playerAProperty2ChoiceBox, playerAProperty3ChoiceBox, playerAProperty1ChoiceBox);
                break;
            case "playerB":
                updateNotSelectedChoiceBoxes(selectedItem, selectedChoiceBoxNum, playerBProperty2ChoiceBox, playerBProperty3ChoiceBox, playerBProperty1ChoiceBox);
                break;

        }
    }

    private void updateNotSelectedChoiceBoxes(String selectedItem, String selectedChoiceBoxNum, ChoiceBox<String> property2ChoiceBox, ChoiceBox<String> property3ChoiceBox, ChoiceBox<String> property1ChoiceBox) {
        switch (selectedChoiceBoxNum) {
            case "1ChoiceBox":
                // if the selected item from choice box 1 for player A is still in choice boxes 2 and 3,
                // remove it
                property2ChoiceBox.getItems().remove(selectedItem);
                property3ChoiceBox.getItems().remove(selectedItem);
                break;
            case "2ChoiceBox":
                property1ChoiceBox.getItems().remove(selectedItem);
                property3ChoiceBox.getItems().remove(selectedItem);
                break;
            case "3ChoiceBox":
                property1ChoiceBox.getItems().remove(selectedItem);
                property2ChoiceBox.getItems().remove(selectedItem);
                break;
            default:
                break;
        }
    }

    @FXML
    public void playerAProperty1ChoiceBoxSelected(Event e) {
        String newMenuText = playerAProperty1ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];

        playerAProperty1ChoiceBox.setValue(newMenuText);

//        playerAProperty1ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerAProperty1ChoiceBox);

//        playerAProperty1ChoiceBox.setDisable(false);

    }
    @FXML
    public void playerAProperty2ChoiceBoxSelected(Event e) {
        String newMenuText  = playerAProperty2ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];;

        playerAProperty2ChoiceBox.setValue(newMenuText);

//        playerAProperty2ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerAProperty2ChoiceBox);

//        playerAProperty2ChoiceBox.setDisable(false);

    }

    @FXML
    public void playerAProperty3ChoiceBoxSelected(Event e) {
        String newMenuText = playerAProperty3ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];

        playerAProperty3ChoiceBox.setValue(newMenuText);

//        playerAProperty3ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerAProperty3ChoiceBox);

//        playerAProperty3ChoiceBox.setDisable(false);

    }



    @FXML
    public void playerBProperty1ChoiceBoxSelected(Event e) {
        String newMenuText = playerBProperty1ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];

        playerBProperty1ChoiceBox.setValue(newMenuText);

//        playerBProperty1ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerBProperty1ChoiceBox);

//        playerBProperty1ChoiceBox.setDisable(false);
    }
    @FXML
    public void playerBProperty2ChoiceBoxSelected(Event e) {
        String newMenuText = playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];

        playerBProperty2ChoiceBox.setValue(newMenuText);

//        playerBProperty2ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerBProperty2ChoiceBox);

//        playerBProperty2ChoiceBox.setDisable(false);

    }

    @FXML
    public void playerBProperty3ChoiceBoxSelected(Event e) {
        String newMenuText = playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem().split(",")[0];;

        playerBProperty3ChoiceBox.setValue(newMenuText);
//        playerBProperty3ChoiceBox.setDisable(true);
        updatePlayerChoiceBoxDisplay(playerBProperty3ChoiceBox);
//        playerBProperty3ChoiceBox.setDisable(false);

    }

}
