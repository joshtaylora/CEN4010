package Controllers;

import Models.Deed;
import Models.Player;
import Models.PropertySet;
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

    // TODO will remove the example objects once concrete methodology fixed
    void populateTradeView(Player initiatingPlayer, Player receivingPlayer) {

        PropertySet exPropertySet1 = new PropertySet(3);
        Deed exDeed1 = new Deed("Marvin Gardens",
                        280,
                        5,
                        24,
                        120,
                        360,
                        850,
                        1025,
                        1200,
                        140,
                        150,
                        150,
                        29);
        Deed exDeed2 = new Deed("North Carolina Ave.",
                300,
                6,
                26,
                130,
                390,
                900,
                1100,
                1275,
                150,
                200,
                200,
                32);
        Deed exDeed3 = new Deed("Pennsylvania Ave.",
                320,
                6,
                28,
                150,
                450,
                100,
                1200,
                1400,
                160,
                200,
                200,
                34);
        exPropertySet1.addProperty(exDeed1);
        exPropertySet1.addProperty(exDeed2);
        exPropertySet1.addProperty(exDeed3);


        PropertySet exPropertySet2 = new PropertySet(3);
        Deed exDeed4 = new Deed("Marvin Gardens",
                280,
                5,
                24,
                120,
                360,
                850,
                1025,
                1200,
                140,
                150,
                150,
                29);
        Deed exDeed5 = new Deed("North Carolina Ave.",
                300,
                6,
                26,
                130,
                390,
                900,
                1100,
                1275,
                150,
                200,
                200,
                32);
        Deed exDeed6 = new Deed("Pennsylvania Ave.",
                320,
                6,
                28,
                150,
                450,
                100,
                1200,
                1400,
                160,
                200,
                200,
                34);
        exPropertySet2.addProperty(exDeed4);
        exPropertySet2.addProperty(exDeed5);
        exPropertySet2.addProperty(exDeed6);

        PropertySet[] exPropertySetArray = { exPropertySet1, exPropertySet2 };

        // initiatingPlayer.getPlayerDeeds()
        for (PropertySet propertySet : exPropertySetArray) {
            for (Deed displayDeed : propertySet.getPropertiesInSet()) {
                String deedName = displayDeed.getName();
                String displayStr = deedName.concat(", " + displayDeed.getPropertySet());
                playerAProperty1ChoiceBox.getItems().add(displayStr);
            }
        }
    }


}
