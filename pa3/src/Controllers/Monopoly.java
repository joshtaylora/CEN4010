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

public class Monopoly extends Application {

   public void main(String[] args) {
      launch(args);
   }
   public void start(Stage primaryStage) throws Exception {
      Scene scene = new Scene(new StackPane());
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TabbedView.fxml"));
      scene.setRoot(loader.load());

      MainController controller = loader.getController();
      controller.init();

      primaryStage.setScene(scene);
      primaryStage.setTitle("Monopoly");
      primaryStage.show();
   }

}
