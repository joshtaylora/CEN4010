package Controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class InterController extends Application {
    //@FXML
    //private

    @FXML
    Button ROLL;

    @FXML
    private ImageView dieOne;
    @FXML
    private ImageView dieTwo;
    @FXML
    private ImageView dieThree;
    @FXML
    private ImageView dieFour;

    @FXML
    private Label labelOne;
    @FXML
    private Label labelTwo;
    @FXML
    private Label labelThree;
    @FXML
    private Label labelFour;

    private Stage primaryStage;
    private Scene primaryScene;
    private Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
         * Step 1) Load FXML document as the parent (root) node Step 2) Make a new scene
         * using the root as the argument Step 3) Set the scene using
         * primaryStage.setScene
         *
         */
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();

        URL pathToOpeningView = new URL("file:src/Views/IntermediateView.fxml");
        loader.setLocation(pathToOpeningView);
        Parent root = loader.load();
        this.primaryScene = new Scene(root);

        primaryStage.setScene(primaryScene);

        primaryStage.setTitle("Roll For Order");

        primaryStage.show();
    }

    @FXML
    public void orderDice(Event e){
        displayDice();
    }

    private void displayDice(){
        //get number of players somehow
        int numPlayers = 2;
        //initialize Dice
        Dice order = new Dice();

        //roll dice relevant to number of players
        int one;
        int two;
        int three;
        int four;
        switch(numPlayers){
            case 2:
                one = order.roll()/2;
                two = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));

                //display player order on labels
                if(one>=two){
                    labelOne.setText("Player 1");
                    labelTwo.setText("Player 2");
                }
                else{
                    labelOne.setText("Player 2");
                    labelTwo.setText("Player 1");
                }
                break;
            case 3:
                one = order.roll()/2;
                two = order.roll()/2;
                three = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));
                dieThree.setImage(die(three));

                //determine player order, then update labels
                int[] sort3 = {3, 3, 3};
                int[] comp3 = {one, two, three};
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(comp3[i] > comp3[j]){
                            sort3[i]--;
                        }
                        else if(i<j && comp3[i] == comp3[j]){
                            sort3[i]--;
                        }
                    }
                }
                labeling(labelOne, sort3[0]);
                labeling(labelTwo, sort3[1]);
                labeling(labelThree, sort3[2]);

                break;
            case 4:
                one = order.roll()/2;
                two = order.roll()/2;
                three = order.roll()/2;
                four = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));
                dieThree.setImage(die(three));
                dieFour.setImage(die(four));

                //determine player order, then update labels
                int[] sort = {4, 4, 4, 4};
                int[] comp = {one, two, three, four};
                for(int i=0; i<4; i++){
                    for(int j=0; j<4; j++){
                        if(comp[i] > comp[j]){
                            sort[i]--;
                        }
                        else if(i<j && comp[i] == comp[j]){
                            sort[i]--;
                        }
                    }
                }
                labeling(labelOne, sort[0]);
                labeling(labelTwo, sort[1]);
                labeling(labelThree, sort[2]);
                labeling(labelFour, sort[3]);

                break;
            default:
                break;
        }

    }

    private Image die(int rollVal){
        Image one = new Image("file: Resources/die1.png");
        Image two = new Image("file: Resources/die2.png");
        Image thr = new Image("file: Resources/die3.png");
        Image fou = new Image("file: Resources/die4.png");
        Image fiv = new Image("file: Resources/die5.png");
        Image six = new Image("file: Resources/die6.png");
        switch(rollVal){
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return thr;
            case 4:
                return fou;
            case 5:
                return fiv;
            default:
                return six;
        }
    }

    private void labeling(Label label, int position){
        switch(position){
            case 1:
                label.setText("Player 1");
                return;
            case 2:
                label.setText("Player 2");
                return;
            case 3:
                label.setText("Player 3");
                return;
            case 4:
                label.setText("Player 4");
                return;
            default:
                return;
        }

    }
}
