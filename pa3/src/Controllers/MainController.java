package Controllers;

import Models.Dice;
import Models.Game;
//import Resources.OSValidator;
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

// =====================================================================================================================
//  ===================================== Class variables needed for start method ======================================



    public void init() {
        menuViewController.injectMainController(this);
        menuViewController.init();
        removeTileTab();
    }

    public void removeTileTab(){
        tabPane.getTabs().remove(tileTab);
    }


        public void addTileTab() {
        tabPane.getTabs().add(tileTab);
        tileTab.setClosable(false);
        tabPane.getSelectionModel().select(tileTab);
    }





}
