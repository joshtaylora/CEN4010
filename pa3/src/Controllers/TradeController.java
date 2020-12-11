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
    ArrayList<Deed> playerATradeDeeds;

    ArrayList<String> playerBSelectedDeeds;
    ArrayList<String> playerBDeedOptions;
    ArrayList<Deed> playerBTradeDeeds;

    ArrayList<ChoiceBox<String>> choiceBoxes;

// =====================================================================================================================

    public void initTradeController(MenuController menuController) {
        this.mainController = menuController.mainController;
        mainController.tradeTab.setClosable(false);
//        playerAProperty1ChoiceBox.getItems().add();
    }



    void populateTradeView(Player initiatingPlayer, Player receivingPlayer) {
        this.playerA = initiatingPlayer;
        this.playerB = receivingPlayer;

        // Disable the confirm trade button from being used until both players have checked their confirm trade checkboxes
        completeTradeButton.setDisable(true);

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

        playerATradeDeeds = new ArrayList<>();
        playerBTradeDeeds = new ArrayList<>();

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
        int option0 = 0;
        int option1 = (int)Math.floor(playerMoney * 0.20);
        int option2 = (int)Math.floor(playerMoney * 0.35);
        int option3 = (int)Math.floor(playerMoney * 0.50);
        int option4 = (int)Math.floor(playerMoney * 0.65);
        int option5 = (int)Math.floor(playerMoney * 0.80);
        int option6 = (int)Math.floor(playerMoney * 0.95);
        // Add all of the possible monetary trade offer options to the choice box list of items
        if (player.getName().equals(this.playerA.getName())) {
            playerAMoneyBox.getItems().addAll(option0, option1, option2, option3, option4, option5, option6);
            playerAMoneyBox.getSelectionModel().select(0);
        }
        else {
            playerBMoneyBox.getItems().addAll(option0, option1, option2, option3, option4, option5, option6);
            playerBMoneyBox.getSelectionModel().select(0);
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

    private void addDeedToTrade(Player tradePlayer, String deedToTrade) {
        for (PropertySet propertySet : tradePlayer.getPlayerPropertySetArray()) {
            for (Deed deed : propertySet.getPropertiesInSet()) {
                if (deed != null) {
                    if (deed.getName().equals(deedToTrade.split(",")[0])) {
                        if (tradePlayer.getName().equals(playerA.getName())) {
                            playerATradeDeeds.add(deed);
                        } else if (tradePlayer.getName().equals(playerB.getName())) {
                            playerBTradeDeeds.add(deed);
                        }
                    }
                }
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

// ====================================================================================================================
// =============================================== Player A Controls ==================================================

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

// ====================================================================================================================
// =============================================== Player B Controls ==================================================
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

    /**
     * Event Listener for when player A checks the confirm trade checkbox. Once checked, it disables player A's
     * inputs so that they can be prepared for the trading mechanism
     * @param e the checkbox being checked or unchecked
     */
    @FXML
    public void confirmTradeForPlayerA(Event e) {
        if (playerAConfirmTradeCheckBox.isSelected()) {
            disablePlayerControls(playerAProperty1ChoiceBox,
                    playerAProperty2ChoiceBox,
                    playerAProperty3ChoiceBox,
                    playerAMoneyBox,
                    playerASelectedDeeds,
                    playerATradeOfferList);
            // Check if both players are ready to complete trade
            if (checkIfPlayersAreReadyToTrade()) {
                completeTradeButton.setDisable(false);
            }
        }
    }

    @FXML
    public void confirmTradeForPlayerB(Event e) {
        if (playerBConfirmTradeCheckBox.isSelected()) {
            disablePlayerControls(playerBProperty1ChoiceBox,
                    playerBProperty2ChoiceBox,
                    playerBProperty3ChoiceBox,
                    playerBMoneyBox,
                    playerBSelectedDeeds,
                    playerBTradeOfferList);
            // Check if both players are ready to complete trade
            if (checkIfPlayersAreReadyToTrade()) {
                completeTradeButton.setDisable(false);
                completeTradeButton.getStyleClass().remove("disableCompleteTradeButton");
                completeTradeButton.getStyleClass().add("activeCompleteTradeButton");

            }
        }
    }



    private void disablePlayerControls(ChoiceBox<String> property1ChoiceBox,
                                       ChoiceBox<String> property2ChoiceBox,
                                       ChoiceBox<String> property3ChoiceBox,
                                       ChoiceBox<Integer> moneyBox,
                                       ArrayList<String> selectedDeeds,
                                       ListView<String> tradeOfferList) {
        // If the checkbox is selected, disable the controls so we can lock in the trade offer
        property1ChoiceBox.setDisable(true);
        property2ChoiceBox.setDisable(true);
        property3ChoiceBox.setDisable(true);
        moneyBox.setDisable(true);
        for (String input : selectedDeeds) {
            tradeOfferList.getItems().add(input);
        }
        tradeOfferList.getItems().add("$ " + Integer.toString(moneyBox.getValue()));
    }

    /**
     * Method that is used to check if both checkboxes have been checked to indicate both players are ready to trade
     * @return boolean indicating if both checkboxes are checked
     */
    public boolean checkIfPlayersAreReadyToTrade() {
        return playerAConfirmTradeCheckBox.isSelected() && playerBConfirmTradeCheckBox.isSelected();
    }

    @FXML
    public void completeTrade(Event e) {
        completeTradeButton.setDisable(true);
        int pAMoney = playerAMoneyBox.getValue();
        int pBMoney = playerBMoneyBox.getValue();
        for (String pADeedStr : playerASelectedDeeds) {
            addDeedToTrade(playerA, pADeedStr);
        }
        for (String pBDeedStr : playerBSelectedDeeds) {
            addDeedToTrade(playerB, pBDeedStr);
        }
        performTrade(playerA, playerB, playerATradeDeeds, playerBTradeDeeds, pAMoney, pBMoney);

        // After the trade has been performed, we can enable the controls again
        enableTradeView();

        mainController.selectGameTab();
        mainController.tradeTab.setClosable(true);
        mainController.closeTradeTab();
    }

    public void performTrade(Player playerA,
							 Player playerB,
							 ArrayList<Deed> pADeeds,
							 ArrayList<Deed> pBDeeds,
							 int pAMoney,
							 int pBMoney) {
        playerA.setAccBalance(playerA.getAccBalance() - pAMoney);
        playerB.setAccBalance(playerB.getAccBalance() + pAMoney);

        playerB.setAccBalance(playerB.getAccBalance() - pBMoney);
        playerA.setAccBalance(playerB.getAccBalance() + pBMoney);

		// Remove deeds from player A first and add them to player B
		transferDeedsFromPlayer(playerA, playerB, pADeeds);
		transferDeedsFromPlayer(playerB, playerA, pBDeeds);
    }
    public void transferDeedsFromPlayer(Player removingPlayer, Player addingPlayer, ArrayList<Deed> playerDeeds) {
		for (Deed removeDeed : playerDeeds) {
            if (removeDeed != null) {
                int removeDeedPSet = removeDeed.getPropertySet();
                // remove the deed from the player's property set
                removingPlayer.getPropertySetArray()[removeDeedPSet].removeProperty(removeDeed);
                System.out.println("Successfully removed deed: " + removeDeed.getName()  + " from " + removingPlayer.getName());
                addingPlayer.getPropertySetArray()[removeDeedPSet].addProperty(removeDeed);

                System.out.println("Successfully traded deed: " + removeDeed.getName()  + " to " + addingPlayer.getName());

                removeDeed.setOwner(addingPlayer);
            }
        }
	}

	public void enableTradeView() {
        // re-enable playerA's controls
        enablePlayerControls(playerAProperty1ChoiceBox,
                playerAProperty2ChoiceBox,
                playerAProperty3ChoiceBox,
                playerAMoneyBox,
                playerASelectedDeeds,
                playerATradeOfferList,
                playerAConfirmTradeCheckBox);

        // re-enable playerB's controls
        enablePlayerControls(playerBProperty1ChoiceBox,
                playerBProperty2ChoiceBox,
                playerBProperty3ChoiceBox,
                playerBMoneyBox,
                playerBSelectedDeeds,
                playerBTradeOfferList,
                playerBConfirmTradeCheckBox);
    }
/**
     * Once the trade has been completed, we need to re-enable the controls for the next trade
     * @param property1ChoiceBox ChoiceBox control for property1
     * @param property2ChoiceBox ChoiceBox control for property2
     * @param property3ChoiceBox ChoiceBox control for property3
     * @param moneyBox ChoiceBox control for the money choice
     * @param selectedDeeds ArrayList of deeds selected by the player
     * @param tradeOfferList ListView control for the summary of the trade
     * @param tradeReadyCheck CheckBox indicating that the player is ready to go through with the trade offer at hand
     */
    private void enablePlayerControls(ChoiceBox<String> property1ChoiceBox,
                                      ChoiceBox<String> property2ChoiceBox,
                                      ChoiceBox<String> property3ChoiceBox,
                                      ChoiceBox<Integer> moneyBox,
                                      ArrayList<String> selectedDeeds,
                                      ListView<String> tradeOfferList,
                                      CheckBox tradeReadyCheck) {
        // re-enable the ChoiceBox options
        clearChoiceBox(property1ChoiceBox);
        clearChoiceBox(property2ChoiceBox);
        clearChoiceBox(property3ChoiceBox);

        // re-enable the moneyBox ChoiceBox
        clearMoneyBox(moneyBox);


        ArrayList<String> removeList = new ArrayList<>();
        // remove deeds from the selectedDeeds array list
        for (String input : selectedDeeds) {
            removeList.add(input);
        }
        selectedDeeds.removeAll(removeList);

        // re-enable the ListView of Strings from the trade offer
        clearTradeOfferList(tradeOfferList);

        clearTradeCheckBox(tradeReadyCheck);
    }

    private void clearTradeCheckBox(CheckBox tradeReadyCheck) {
        tradeReadyCheck.setDisable(false);
        tradeReadyCheck.setSelected(false);
    }

    private void clearMoneyBox(ChoiceBox<Integer> moneyBox) {
        moneyBox.setDisable(false);
        moneyBox.getSelectionModel().clearSelection();
        moneyBox.getItems().clear();
    }

    private void clearChoiceBox(ChoiceBox<String> propertyChoiceBox) {
        propertyChoiceBox.setDisable(false);
        propertyChoiceBox.getSelectionModel().clearSelection();
        propertyChoiceBox.getItems().clear();
    }

    private void clearTradeOfferList(ListView<String> tradeOfferList) {
        // clear out the ListView
        tradeOfferList.setDisable(false); /* re-enable the ListView control */
        tradeOfferList.getSelectionModel().clearSelection(); /* Clear the selected items */
        tradeOfferList.getItems().clear(); /* Clear out the items in the ListView */

    }
}
