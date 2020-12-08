package Resources;

import Models.Token;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImageContainer {
    OSValidator osValidator;
    ArrayList<ArrayList<String>> imageURLCollection;
    
    String[] dieImageURLs;
    String[] tokenImageURLs;

    private static Image[] dieImages;
    private static Image[] tokenImages;

    private static Image die1Image = null;
    private static Image die2Image = null;
    private static Image die3Image = null;
    private static Image die4Image = null;
    private static Image die5Image = null;
    private static Image die6Image = null;


    private static Image dogTokenImage = null;
    private static Image shoeTokenImage = null;
    private static Image racecarTokenImage = null;
    private static Image thimbleTokenImage = null;

    public ImageContainer() {
        // Initialize the OSValidator object that will be used to retrieve correct working dir path
        this.osValidator = new OSValidator();
        // The arrays that will hold the Image objects themselves
        dieImages = new Image[6];


        tokenImages = new Image[4];
        initImageURLCollection();
        initImageObjectCollection();


    }

    private void initImageURLCollection() {
        /* Array for the Strings containing the URLs for the dice images */
        this.dieImageURLs = new String[6];
        String die1File = osValidator.getPathToFile("die1.png", "Resources/Images");
        String die2File = osValidator.getPathToFile("die2.png", "Resources/Images");
        String die3File = osValidator.getPathToFile("die3.png", "Resources/Images");
        String die4File = osValidator.getPathToFile("die4.png", "Resources/Images");
        String die5File = osValidator.getPathToFile("die5.png", "Resources/Images");
        String die6File = osValidator.getPathToFile("die6.png", "Resources/Images");
        dieImageURLs[0] = die1File;
        dieImageURLs[1] = die2File;
        dieImageURLs[2] = die3File;
        dieImageURLs[3] = die4File;
        dieImageURLs[4] = die5File;
        dieImageURLs[5] = die6File;
        /* Array for the Strings containing the URLs for the token images */
        this.tokenImageURLs = new String[4];
        String dogFile = osValidator.getPathToFile("dog.png", "Resources/Images");
        String shoeFile = osValidator.getPathToFile("shoe.png", "Resources/Images");
        String racecarFile = osValidator.getPathToFile("racecar.png", "Resources/Images");
        String thimbleFile = osValidator.getPathToFile("thimble.png", "Resources/Images");
        tokenImageURLs[0] = dogFile;
        tokenImageURLs[1] = shoeFile;
        tokenImageURLs[2] = racecarFile;
        tokenImageURLs[3] = thimbleFile;

    }

    private void initImageObjectCollection() {
        /* Initialize the elements of the dice Image array to the proper images */
        try {
            die1Image = new Image(dieImageURLs[0]);
            die2Image = new Image(dieImageURLs[1]);
            die3Image = new Image(dieImageURLs[2]);
            die4Image = new Image(dieImageURLs[3]);
            die5Image = new Image(dieImageURLs[4]);
            die6Image = new Image(dieImageURLs[5]);
            /* Initialize the elements of the token Image array to the proper images */
            dogTokenImage = new Image(tokenImageURLs[0]);
            shoeTokenImage = new Image(tokenImageURLs[1]);
            racecarTokenImage = new Image(tokenImageURLs[2]);
            thimbleTokenImage = new Image(tokenImageURLs[3]);
        } catch(NullPointerException e) {
            for (String dieImageURL : this.dieImageURLs) {
                System.out.println(dieImageURL);
            }
            System.exit(1);
        }
        addImagesToClass();
    }

    private void addImagesToClass() {
        // add the images directly to the class variable array of die image objects
        dieImages[0] = die1Image;
        dieImages[1] = die2Image;
        dieImages[2] = die3Image;
        dieImages[3] = die4Image;
        dieImages[4] = die5Image;
        dieImages[5] = die6Image;
        // add the images directly to the class variable array of token image objects
        tokenImages[0] = dogTokenImage;
        tokenImages[1] = shoeTokenImage;
        tokenImages[2] = racecarTokenImage;
        tokenImages[3] = thimbleTokenImage;
    }

    /**
     * Function used to get the image for a specific dice roll
     * @param diceRoll the value of one of the die rolled
     * @return the Image for the appropriate roll of the die
     */
    public Image getDieImage(int diceRoll) {
        // Image that will be returned
        Image returnImage = null;

        if (dieImages[diceRoll - 1] == null) {
            System.out.println("Error with die image retrieval in ImageContainer class");
            System.exit(1);
        }
        else {
            returnImage = dieImages[diceRoll - 1];
        }
        return returnImage;
    }

    public Image getTokenImage(String tokenName) {
        Image returnTokImg = null;
        switch(tokenName) {
            case "dog":
                returnTokImg = tokenImages[0];
                break;
            case "shoe":
                returnTokImg = tokenImages[1];
                break;
            case "racecar":
                returnTokImg = tokenImages[2];
                break;
            case "thimble":
                returnTokImg = tokenImages[3];
                break;
            default:
                break;
        }
        return returnTokImg;
    }

}
