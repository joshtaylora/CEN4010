package Controllers;

import Controllers.GameController;
import Controllers.MainController;
import Controllers.MenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Monopoly extends Application {

   public void main(String[] args) {
      launch(args);
   }
   public void start(Stage primaryStage) throws Exception {
      // Make the scene
      Scene scene = new Scene(new StackPane());
      String resource = "/Views/MenuView.fxml";
      URL location = getClass().getResource(resource);


      FXMLLoader loader = new FXMLLoader(location);
      scene.setRoot(loader.load());
      MainController controller = loader.getController();

      primaryStage.setScene(scene);
      primaryStage.setTitle("Monopoly");
      primaryStage.show();
   }

}
