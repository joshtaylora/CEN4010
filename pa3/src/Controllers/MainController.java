package Controllers;

import Models.Dice;
import Models.Game;
//import Resources.OSValidator;
import Resources.ResourceManager;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class MainController {

// =====================================================================================================================
// ========================================= FXML elements for the main TabPane ========================================
    @FXML TabPane tabPane;

    @FXML Tab menuTab;
    @FXML MenuController menuViewController;

    @FXML Tab gameTab;
    @FXML  GameController gameViewController;

    @FXML Tab turnOrderTab;
    @FXML  TurnOrderController turnOrderViewController;

    @FXML Tab tileTab;
    @FXML TilePopController tileViewController;

    @FXML Tab tradeTab;
    @FXML TradeController tradeViewController;

// =====================================================================================================================
//  ===================================== Class variables needed for start method ======================================
    ResourceManager resourceManager = new ResourceManager();


    public void init() {
        initMenuController();
//        menuViewController.injectMainController(this);
//        menuViewController.init();
        removeTileTab();
    }

    /**
     * Method to initialize the menu controller by injecting the active instance of MainController
     */
    public void initMenuController() {
        this.menuViewController.injectMainController(this);
        // now initialize the TurnOrderController so that it is ready for the tab selection switch
        this.initTurnOrderController();
//        this.turnOrderViewController.init;
    }

    public void initTurnOrderController() {
        this.turnOrderViewController.injectMainController(this);
    }

    public void initTilePopController() {
        this.tileViewController.injectMain(this);
        removeTileTab();
    }

    public void removeTileTab(){
        tabPane.getTabs().remove(tileTab);
    }


    public void addTileTab() {
        this.tileViewController.injectMain(this);
        if (!this.tabPane.getTabs().contains(tileTab)) {
            tabPane.getTabs().add(tileTab);
            tileTab.setClosable(false);
            tabPane.getSelectionModel().select(tileTab);
        }
    }

    public void openTradePopup() throws IOException{
        String tradePopupPath = resourceManager.getPathToFile("TradePopup.fxml", "Views");

        Parent parent = FXMLLoader.load(getClass().getResource(tradePopupPath));
    }

    public void selectTurnOrderTab() {
        tabPane.getSelectionModel().select(turnOrderTab);
    }

    /**
     * Method to select the Game tab as the active tab
     */
    public void selectGameTab() {
        tabPane.getSelectionModel().select(gameTab);
    }

    /**
     * Method to close the menu tab once the number of players and time limit has been decided
     */
    public void closeMenuTab() {
        // only close the menu tab if it is not selected
        if (!this.menuTab.isSelected()) {
            tabPane.getTabs().remove(menuTab);
        }
    }

    /**
     * Method to close the turn order tab once the players have had their turn order decided
     */
    public void closeTurnOrderTab() {
        if (!this.turnOrderTab.isSelected()) {
            tabPane.getTabs().remove(turnOrderTab);
        }
    }

    public void closeTradeTab() {
        if (!this.tradeTab.isSelected()) {
            tabPane.getTabs().remove(tradeTab);
        }
        else {
            tabPane.getSelectionModel().select(gameTab);
            tabPane.getTabs().remove(tradeTab);
        }
    }
}
