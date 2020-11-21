package Controllers;

import Models.Game;
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
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class MenuController{

	@FXML private TabPane tabPane;
	@FXML private Tab menuTab;
	@FXML private Tab gameTab;

	@FXML private GameController gameTabPageController;

	@FXML
	ChoiceBox<String> playerChoiceBox;

	@FXML
	ChoiceBox<String> timerChoiceBox;

	@FXML
	Button startGame;

	private Game game;

	/**
	 * Event handler for when the start button has been clicked
	 * @param e event for when the start button gets clicked
	 */
	@FXML
	public void startButtonClicked(Event e) {
		// initialize the data for the Game object that will be used by the GameController
		initGameData();

		if (!tabPane.getTabs().contains(gameTab)) {
			// add the game tab to the view now that the necessary data to start the game has been received
			tabPane.getTabs().add(gameTab);
			// set the selected tab to the game tab
			tabPane.getSelectionModel().select(gameTab);
			// remove the menu tab now that we don't need it
			tabPane.getTabs().remove(menuTab);
			// pass the game object to the controller responsible for the game logic and view
			gameTabPageController.init(this.game);
		}
	}

	/*
	 * This initializes the Game object for use by the game controller
	 */
	private void initGameData() {

		// Grab the value of the selected player choice box input
		String playerChoiceBoxValue = playerChoiceBox.getValue();
		// split the value into a string part and a digit part
		String[] playerSplit = playerChoiceBoxValue.split("\\s");
		int numPlayers = Integer.parseInt(playerSplit[0]);

		System.out.println("Number of Players Selected: " + numPlayers);

		// Grab the value of the selected timer choice box input
		String timerChoiceBoxValue = timerChoiceBox.getValue();
		// split the value into a string part and a digit part
		String[] timerSplit = timerChoiceBoxValue.split("\\s");
		int timerVal = Integer.parseInt(timerSplit[0]);

		System.out.println("Game timer length Selected: " + timerVal);

		// initialize the game
		this.game = new Game(numPlayers, timerVal);

	}

}
