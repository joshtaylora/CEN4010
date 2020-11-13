package Controllers;

import Models.Game;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainMenuController extends Application {
    private Stage primaryStage;
    private GridPane rootLayout;

    private int timerVal;

    /**
     *
     */
    @FXML private ToggleGroup timerGroup;
    @FXML private RadioButton timerButton10;
    @FXML private RadioButton timerButton20;
    @FXML private RadioButton timerButton30;
    @FXML private RadioButton timerButton45;

    @FXML private ComboBox<Integer> numPlayers;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Main Menu");


        initMainMenuLayout();
        initGameObject();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initMainMenuLayout() {
        try {
            String mainMenuViewPath = "../Views/MainMenuView.fxml";
            FileInputStream fxmlStream = new FileInputStream(mainMenuViewPath);

            FXMLLoader loader = new FXMLLoader();
            rootLayout = loader.load(fxmlStream);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game initGameObject() {
        int initTimerVal;
        int players;


        //TODO removed action listeners and need to find how to get the user data values set without getting null
        initTimerVal = Integer.parseInt(timerGroup.getSelectedToggle().getUserData().toString());
        players = numPlayers.getSelectionModel().getSelectedItem();
        Game gameObj = new Game(players, initTimerVal);
        return gameObj;
    }

}
