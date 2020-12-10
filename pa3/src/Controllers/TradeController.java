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
import java.util.ArrayList;

public class TradeController {
    @FXML CheckBox playerAConfirmTradeCheckBox;
    @FXML CheckBox playerBConfirmTradeCheckBox;

    @FXML ListView<String> playerBTradeOfferList;
    @FXML ListView<String> playerATradeOfferList;

    @FXML Button completeTradeButton;

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
    Player playerA;
    Player playerB;

    String playerAProperty1;
    String playerAProperty2;
    String playerAProperty3;

    String playerBProperty1;
    String playerBProperty2;
    String playerBProperty3;

    ArrayList<String> playerASelectedDeeds;
    ArrayList<String> playerADeedOptions;
    ArrayList<String> playerBSelectedDeeds;
    ArrayList<String> playerBDeedOptions;

    ArrayList<ChoiceBox<String>> choiceBoxes;

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
        this.playerA = initiatingPlayer;
        this.playerB = receivingPlayer;

        // set the label to show the initiating player's name
        playerALabel.setText(this.playerA.getName());

        // set the label to show the receiving player's name
        playerBLabel.setText(this.playerB.getName());

        // initialize the selected and option array lists for player A
        playerASelectedDeeds = new ArrayList<>();
        playerADeedOptions = new ArrayList<>();

        // initialize the selected and option array lists for player B
        playerBSelectedDeeds = new ArrayList<>();
        playerBDeedOptions = new ArrayList<>();

        // Add all of the properties
        initDeedOptionsLists(initiatingPlayer);
        initDeedOptionsLists(receivingPlayer);
        // initialize the array list of choice boxes
        choiceBoxes = new ArrayList<>();
        // add all of player A's choice boxes to the array list
        choiceBoxes.add(playerAProperty1ChoiceBox);
        choiceBoxes.add(playerAProperty2ChoiceBox);
        choiceBoxes.add(playerAProperty3ChoiceBox);
        // add all of player B's choice boxes to the array list
        choiceBoxes.add(playerBProperty1ChoiceBox);
        choiceBoxes.add(playerBProperty2ChoiceBox);
        choiceBoxes.add(playerBProperty3ChoiceBox);

        // until player A selects a property in the first choice box, hide the other choices
        playerAProperty2ChoiceBox.setVisible(false);
        playerAProperty3ChoiceBox.setVisible(false);
        // until player B selects a property in the first choice box, hide the other choices
        playerBProperty2ChoiceBox.setVisible(false);
        playerBProperty3ChoiceBox.setVisible(false);

        // add options for the players to chose monetary contributions to add to their trade offers
        initPlayerMoneyBox(initiatingPlayer);
        initPlayerMoneyBox(receivingPlayer);

        updateChoiceBox(playerA, playerAProperty1ChoiceBox);
        updateChoiceBox(playerA, playerAProperty2ChoiceBox);
        updateChoiceBox(playerA, playerAProperty3ChoiceBox);


        updateChoiceBox(playerB, playerBProperty1ChoiceBox);
        updateChoiceBox(playerB, playerBProperty2ChoiceBox);
        updateChoiceBox(playerB, playerBProperty3ChoiceBox);

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
        if (player.getName().equals(this.playerA.getName())) {
            playerAMoneyBox.getItems().addAll(option1, option2, option3, option4, option5, option6);
        }
        else {
            playerBMoneyBox.getItems().addAll(option1, option2, option3, option4, option5, option6);
        }

    }

    private void initDeedOptionsLists(Player tradePlayer) {
        String tradePlayerName = tradePlayer.getName();
        String initiatingPlayerName = playerA.getName();
        String receivingPlayerName = playerB.getName();

        for (PropertySet propertySet : tradePlayer.getPropertySetArray()) {
            for (Deed deed : propertySet.getPropertiesInSet()) {
                if (deed != null) {
                    String deedName = deed.getName();
                    String displayStr = deedName.concat(", " + deed.getPropertySetType());
                    if (tradePlayerName.equals(initiatingPlayerName)) {
                        playerADeedOptions.add(displayStr);
                    }
                    else if (tradePlayerName.equals(receivingPlayerName)) {
                        playerBDeedOptions.add(displayStr);
                    }
                }
            }
        }
    }

    public void updateChoiceBox(Player tradePlayer, ChoiceBox<String> playerChoiceBox) {
        String playerName = tradePlayer.getName();

        playerChoiceBox.getItems().remove(0, playerChoiceBox.getItems().size());
        if (playerName.equals(playerA.getName())) {
            for (String deed : playerADeedOptions) {
                playerChoiceBox.getItems().add(deed);
            }
        }
        else if (playerName.equals(playerB.getName())) {
            for (String deed : playerBDeedOptions) {
                playerChoiceBox.getItems().add(deed);
            }
        }
    }

    private void updateDeedOptionsList(String playerName, String selectedDeed, ChoiceBox<String> selectedChoiceBox) {
        if (playerName.equals(playerA.getName())) {
            // Check if any other PlayerA ChoiceBoxes currently select "selectedDeed"
            String duplicateSelectCB = checkOtherPlayerAChoiceBoxes(selectedChoiceBox, selectedDeed);
            if (duplicateSelectCB != null) {
                switch (duplicateSelectCB) {
                    case "playerAProperty1ChoiceBox":
                        playerAProperty1ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    case "playerAProperty2ChoiceBox":
                        playerAProperty2ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    case "playerAProperty3ChoiceBox":
                        playerAProperty3ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    default:
                        break;
                }
            }
            else {
                if (!playerASelectedDeeds.contains(selectedDeed)) {
                    playerASelectedDeeds.add(selectedDeed);
                }
            }
        }
        else if (playerName.equals(playerB.getName())) {
            String duplicateSelectCB = checkOtherPlayerBChoiceBoxes(selectedChoiceBox, selectedDeed);
            if (duplicateSelectCB != null) {
                switch (duplicateSelectCB) {
                    case "playerBProperty1ChoiceBox":
                        playerBProperty1ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    case "playerBProperty2ChoiceBox":
                        playerBProperty2ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    case "playerBProperty3ChoiceBox":
                        playerBProperty3ChoiceBox.getSelectionModel().clearSelection();
                        break;
                    default:
                        break;
                }
            }
            else {
                if (!playerBSelectedDeeds.contains(selectedDeed)) {
                    playerBSelectedDeeds.add(selectedDeed);
                }
            }
        }
    }

    /**
     * Method used to check if the other ChoiceBoxes for Player A currently select the same item that has just been
     *        selected by the choiceBox param
     * @param choiceBox the ChoiceBox object that has just been selected
     * @param selectedDeed the string representation of the Deed that has been selected in choiceBox
     * @return the ID attribute of the ChoiceBox that also selects the same Deed or null if no other CB does
     */
    private String checkOtherPlayerAChoiceBoxes(ChoiceBox<String> choiceBox, String selectedDeed) {
        return getDuplicateSelectionCB(choiceBox,
                selectedDeed,
                playerAProperty1ChoiceBox,
                playerAProperty2ChoiceBox,
                playerAProperty3ChoiceBox);
    }

   /**
     * Method used to check if the other ChoiceBoxes for Player A currently select the same item that has just been
     *        selected by the choiceBox param
     * @param choiceBox the ChoiceBox object that has just been selected
     * @param selectedDeed the string representation of the Deed that has been selected in choiceBox
     * @return the ID attribute of the ChoiceBox that also selects the same Deed or null if no other CB does
     */
   private String checkOtherPlayerBChoiceBoxes(ChoiceBox<String> choiceBox, String selectedDeed) {
        return getDuplicateSelectionCB(choiceBox,
                selectedDeed,
                playerBProperty1ChoiceBox,
                playerBProperty2ChoiceBox,
                playerBProperty3ChoiceBox);
   }

    private String getDuplicateSelectionCB(ChoiceBox<String> choiceBox,
                                           String selectedDeed,
                                           ChoiceBox<String> property1ChoiceBox,
                                           ChoiceBox<String> property2ChoiceBox,
                                           ChoiceBox<String> property3ChoiceBox)
    {
        String choiceBoxID = choiceBox.getId();
        String returnCondition;
        if (choiceBoxID.equals(property1ChoiceBox.getId())) {
            if (getChoiceBoxSelection(property2ChoiceBox).equals(selectedDeed)) {
                returnCondition = property2ChoiceBox.getId();
            }
            else if (getChoiceBoxSelection(property3ChoiceBox).equals(selectedDeed)) {
                returnCondition = property3ChoiceBox.getId();
            }
            else {
                returnCondition = null;
            }
        }
        else if (choiceBoxID.equals(property2ChoiceBox.getId())) {
            if(getChoiceBoxSelection(property1ChoiceBox).equals(selectedDeed)) {
                returnCondition = property1ChoiceBox.getId();
            }
            else if (getChoiceBoxSelection(property3ChoiceBox).equals(selectedDeed)) {
                returnCondition = property3ChoiceBox.getId();
            }
            else {
                returnCondition = null;
            }
        }
        else if (choiceBoxID.equals(property3ChoiceBox.getId())) {
            if(getChoiceBoxSelection(property1ChoiceBox).equals(selectedDeed)) {
                returnCondition = property1ChoiceBox.getId();
            }
            else if(getChoiceBoxSelection(property2ChoiceBox).equals(selectedDeed)) {
                returnCondition = property2ChoiceBox.getId();
            }
            else {
                returnCondition = null;
            }
        }
        else {
            returnCondition = null;
        }
        return returnCondition;
    }

    private String getChoiceBoxSelection(ChoiceBox<String> choiceBox) {
        return choiceBox.getSelectionModel().getSelectedItem();
    }


    public void updatePlayerASelectedDeeds(String selectedItem) {

        if (!playerASelectedDeeds.contains(selectedItem)) {
            playerASelectedDeeds.add(selectedItem);
        }
        else {
            playerASelectedDeeds.remove(selectedItem);
        }
    }

    @FXML
    public void playerAProperty1ChoiceBoxSelected(Event e) {
        String selectedItem = playerAProperty1ChoiceBox.getSelectionModel().getSelectedItem();

        updateDeedOptionsList(playerA.getName(), selectedItem, playerAProperty1ChoiceBox);
        updateChoiceBox(playerA, playerAProperty1ChoiceBox);

        // display the selected item in the choice box
        playerAProperty1ChoiceBox.setValue(selectedItem);

//        updatePlayerASelectedDeeds(selectedItem);
        playerAProperty2ChoiceBox.setVisible(true);

    }
    @FXML
    public void playerAProperty2ChoiceBoxSelected(Event e) {
        String selectedItem = playerAProperty2ChoiceBox.getSelectionModel().getSelectedItem();


        updateDeedOptionsList(playerA.getName(), selectedItem, playerAProperty2ChoiceBox);
        updateChoiceBox(playerA, playerAProperty2ChoiceBox);

        playerAProperty2ChoiceBox.setValue(selectedItem);

//        updatePlayerASelectedDeeds(selectedItem);
        playerAProperty3ChoiceBox.setVisible(true);
    }

    @FXML
    public void playerAProperty3ChoiceBoxSelected(Event e) {
        String selectedItem = playerAProperty3ChoiceBox.getSelectionModel().getSelectedItem();


        updateDeedOptionsList(playerA.getName(), selectedItem, playerAProperty3ChoiceBox);
        updateChoiceBox(playerA, playerAProperty3ChoiceBox);

        playerAProperty3ChoiceBox.setValue(selectedItem);

//        updatePlayerASelectedDeeds(selectedItem);

    }

    private void updatePlayerBSelectedDeeds(String selectedItem) {
       if (!playerBSelectedDeeds.contains(selectedItem)) {
           playerBSelectedDeeds.add(selectedItem);
       }
       else {
           playerBSelectedDeeds.remove(selectedItem);
       }
    }


    @FXML
    public void playerBProperty1ChoiceBoxSelected(Event e) {
        String selectedItem = playerBProperty1ChoiceBox.getSelectionModel().getSelectedItem();



        updateDeedOptionsList(playerB.getName(), selectedItem, playerBProperty1ChoiceBox);
        updateChoiceBox(playerB, playerBProperty1ChoiceBox);

        playerBProperty1ChoiceBox.setValue(selectedItem);

        playerBProperty2ChoiceBox.setVisible(true);
    }
    @FXML
    public void playerBProperty2ChoiceBoxSelected(Event e) {
        String selectedItem = playerBProperty2ChoiceBox.getSelectionModel().getSelectedItem();

        updateDeedOptionsList(playerB.getName(), selectedItem, playerBProperty2ChoiceBox);
        updateChoiceBox(playerB, playerBProperty2ChoiceBox);

        playerBProperty2ChoiceBox.setValue(selectedItem);
        playerBProperty3ChoiceBox.setVisible(true);
    }

    @FXML
    public void playerBProperty3ChoiceBoxSelected(Event e) {
        String selectedItem = playerBProperty3ChoiceBox.getSelectionModel().getSelectedItem();

        updateDeedOptionsList(playerB.getName(), selectedItem, playerBProperty3ChoiceBox);

        updateChoiceBox(playerB, playerBProperty3ChoiceBox);

        playerBProperty3ChoiceBox.setValue(selectedItem);
    }


    @FXML
    public void confirmTradeForPlayerA(Event e) {

    }

    @FXML
    public void confirmTradeForPlayerB(Event e) {

    }
}
