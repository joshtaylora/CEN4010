package Controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane tabPane;

//   To inject a controller for an included FXML file, you need an fx:id attribute on the <fx:include> element
//   The controller will be injected to a field with "Controller" appended to the value of fx:id
//   To inject the actual tab as well, we need a separate fx:id for that as well

    @FXML private Tab menuTab;
    @FXML private MenuController menuTabPageController;

    @FXML private Tab gameTab;
    @FXML private GameController gameTabPageController;

    public void init() {
        // Remove the game tab until the players have input the required fields to start the game
        tabPane.getTabs().remove(gameTab);
    }

}
