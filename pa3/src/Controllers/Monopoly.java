package Controllers;

import Resources.OSValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Monopoly extends Application {
    Stage primaryStage;
    /**
     * @param primaryStage The stage that is passed from the JavaFX runtime
     * @throws Exception Exceptions that can occur in this method are mostly dealing with the URL of the view being loaded
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        Scene scene = new Scene(new StackPane());

        String sysPath = System.getProperty("user.dir");
        //System.out.println(sysPath);


        OSValidator osValidator = new OSValidator();
        String os = osValidator.os;
        String tabbedViewPath = null;

        if (os.equals("windows")) {
            if (sysPath.contains("pa3")) {
                tabbedViewPath = "\\src\\Views\\TabbedView.fxml";
            } else {
                tabbedViewPath = "\\pa3\\src\\Views\\TabbedView.fxml";
            }
        }
        else if (os.equals("mac")) {
            if (sysPath.contains("pa3")) {
                tabbedViewPath = "src/Views/TabbedView.fxml";
            }
            else {
                tabbedViewPath = sysPath.concat("/pa3/src/Views/TabbedView.fxml");
            }
        }
        else {
            System.out.println("ERROR: operating system not supported");
            System.exit(1);
        }
        String systemPathURL = sysPath.concat(tabbedViewPath);
        File tabbedViewFile = new File(systemPathURL);
        URL tabbedViewURL = tabbedViewFile.toURI().toURL();

        loader.setLocation(tabbedViewURL);
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.init();
        /*
        Parent root = loader.load();
        Scene scene = new Scene(root);
         */

        this.primaryStage.setScene(scene);

        this.primaryStage.setTitle("Main Menu");

        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
