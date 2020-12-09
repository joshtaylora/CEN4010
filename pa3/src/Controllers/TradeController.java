package Controllers;

import Models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class TradeController {
    @FXML Label playerALabel;
    @FXML Label playerBLabel;

    @FXML
    SplitMenuButton playerAMenu;

    @FXML
    MenuItem playerAProperty1MenuItem;
    @FXML
    ChoiceBox playerAProperty1ChoiceBox;

    @FXML
    MenuItem playerAProperty2MenuItem;
    @FXML
    ChoiceBox playerAProperty2ChoiceBox;

    @FXML
    MenuItem playerAProperty3MenuItem;
    @FXML
    ChoiceBox playerAProperty3ChoiceBox;

    @FXML
    SplitMenuButton playerBMenu;

    @FXML
    MenuItem playerBProperty1MenuItem;
    @FXML
    ChoiceBox playerBProperty1ChoiceBox;

    @FXML
    MenuItem playerBProperty2MenuItem;
    @FXML
    ChoiceBox playerBProperty2ChoiceBox;

    @FXML
    MenuItem playerBProperty3MenuItem;
    @FXML
    ChoiceBox playerBProperty3ChoiceBox;
// =====================================================================================================================
// ============================================ Global Variables =======================================================

    MainController mainController;
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
    void performTrade(Player playerA, Player playerB) {

    }


}
