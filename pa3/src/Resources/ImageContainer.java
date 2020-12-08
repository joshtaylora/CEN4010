package Resources;

import Models.Token;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImageContainer {
    OSValidator osValidator;
    ArrayList<ArrayList<String>> imageURLCollection;
    
    String[] diceImageURLs;
    String[] tokenImageURLs;

    Image[] diceImages;
    Image[] tokenImages;

    public ImageContainer() {
        /* Initialize the OSValidator object that will be used to retrieve correct working dir path */
        this.osValidator = new OSValidator();
        /* Initialize the collection of array lists of image URLs that will be queried */
        /* The arrays that will hold the Image objects themselves */
        this.diceImages = new Image[6];
        this.tokenImages = new Image[4];

        /* Initialize the elements of the dice Image array to the proper images */
        this.diceImages[0] = new Image(diceImageURLs[0]);
        this.diceImages[1] = new Image(diceImageURLs[1]);
        this.diceImages[2] = new Image(diceImageURLs[2]);
        this.diceImages[3] = new Image(diceImageURLs[3]);
        this.diceImages[4] = new Image(diceImageURLs[4]);
        this.diceImages[5] = new Image(diceImageURLs[5]);
        /* Initialize the elements of the token Image array to the proper images */
        this.tokenImages[0] = new Image(tokenImageURLs[0]);
        this.tokenImages[1] = new Image(tokenImageURLs[1]);
        this.tokenImages[2] = new Image(tokenImageURLs[2]);
        this.tokenImages[3] = new Image(tokenImageURLs[3]);

    }

    private void initImageURLCollection() {
        /* Array for the Strings containing the URLs for the dice images */
        this.diceImageURLs = new String[6];
        this.diceImageURLs[0] = osValidator.getPathToFile("die1.png", "Resources/Images");
        this.diceImageURLs[1] = osValidator.getPathToFile("die2.png", "Resources/Images");
        this.diceImageURLs[2] = osValidator.getPathToFile("die3.png", "Resources/Images");
        this.diceImageURLs[3] = osValidator.getPathToFile("die4.png", "Resources/Images");
        this.diceImageURLs[4] = osValidator.getPathToFile("die5.png", "Resources/Images");
        this.diceImageURLs[5] = osValidator.getPathToFile("die6.png", "Resources/Images");
        /* Array for the Strings containing the URLs for the token images */
        this.tokenImageURLs = new String[4];
        this.tokenImageURLs[0] = osValidator.getPathToFile("dog.png", "Resources/Images");
        this.tokenImageURLs[1] = osValidator.getPathToFile("shoe.png", "Resources/Images");
        this.tokenImageURLs[2] = osValidator.getPathToFile("racecar.png", "Resources/Images");
        this.tokenImageURLs[3] = osValidator.getPathToFile("thimble.png", "Resources/Images");

    }

    /**
     * Function used to get the image for a specific dice roll
     * @param diceRoll the value of one of the die rolled
     * @return the Image for the appropriate roll of the die
     */
    public Image getDiceImage(int diceRoll) {
        Image returnImage = null;
        switch (diceRoll) {
            case 1:
                returnImage = this.diceImages[0];
                break;
            case 2:
                returnImage = this.diceImages[1];
                break;
            case 3:
                returnImage = this.diceImages[2];
                break;
            case 4:
                returnImage = this.diceImages[3];
                break;
            case 5:
                returnImage = this.diceImages[4];
                break;
            case 6:
                returnImage = this.diceImages[5];
                break;
            default:
                break;

        }
        return returnImage;
    }

    public Image getTokenImage(String tokenName) {
        Image returnTokImg = null;
        switch(tokenName) {
            case "dog":
                returnTokImg = this.tokenImages[0];
                break;
            case "shoe":
                returnTokImg = this.tokenImages[1];
                break;
            case "racecar":
                returnTokImg = this.tokenImages[2];
                break;
            case "thimble":
                returnTokImg = this.tokenImages[3];
                break;
            default:
                break;
        }
        return returnTokImg;
    }

}
