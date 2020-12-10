package Resources;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ImageContainer {
    ResourceManager resourceManager;

    public ArrayList<ArrayList<String>> getImageURLCollection() {
        return imageURLCollection;
    }

    ArrayList<ArrayList<String>> imageURLCollection;
    
    String[] dieImageURLs;
    String[] tokenImageURLs;
    String[] tileImageURLs;

    private static Image[] dieImages;
    private static Image[] tokenImages;
    private static Image[] tileImages;

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

    private static Image purpleDeedImage = null;
    private static Image lBlueDeedImage = null;
    private static Image pinkDeedImage = null;
    private static Image orangeDeedImage = null;
    private static Image redDeedImage = null;
    private static Image yellowDeedImage = null;
    private static Image greenDeedImage = null;
    private static Image dBlueDeedImage = null;
    private static Image waterDeedImage = null;
    private static Image electricDeedImage = null;

    public ImageContainer() {
        // Initialize the ResourceManager object that will be used to retrieve correct working dir path
        this.resourceManager = new ResourceManager();
        // The arrays that will hold the Image objects themselves
        dieImages = new Image[6];

        tokenImages = new Image[4];

        tileImages = new Image[10];
        initImageURLCollection();
        initImageObjectCollection();


    }

    private void initImageURLCollection() {
        /* Array for the Strings containing the URLs for the dice images */
        this.dieImageURLs = new String[6];
        String die1File = resourceManager.getPathToFile("die1.png", "Resources/Images");
        String die2File = resourceManager.getPathToFile("die2.png", "Resources/Images");
        String die3File = resourceManager.getPathToFile("die3.png", "Resources/Images");
        String die4File = resourceManager.getPathToFile("die4.png", "Resources/Images");
        String die5File = resourceManager.getPathToFile("die5.png", "Resources/Images");
        String die6File = resourceManager.getPathToFile("die6.png", "Resources/Images");
        dieImageURLs[0] = die1File;
        dieImageURLs[1] = die2File;
        dieImageURLs[2] = die3File;
        dieImageURLs[3] = die4File;
        dieImageURLs[4] = die5File;
        dieImageURLs[5] = die6File;

        /* Array for the Strings containing the URLs for the token images */
        this.tokenImageURLs = new String[4];
        String dogFile = resourceManager.getPathToFile("dog.png", "Resources/Images");
        String shoeFile = resourceManager.getPathToFile("shoe.png", "Resources/Images");
        String racecarFile = resourceManager.getPathToFile("racecar.png", "Resources/Images");
        String thimbleFile = resourceManager.getPathToFile("thimble.png", "Resources/Images");
        tokenImageURLs[0] = dogFile;
        tokenImageURLs[1] = shoeFile;
        tokenImageURLs[2] = racecarFile;
        tokenImageURLs[3] = thimbleFile;

        /* Array for the Strings containing the URLs for the tile images */
        this.tileImageURLs = new String[10];
        String purpleFile = resourceManager.getPathToFile("PurpleDeed.png", "Resources/Images");
        String lblueFile = resourceManager.getPathToFile("LightBlueDeed.png", "Resources/Images");
        String pinkFile = resourceManager.getPathToFile("PinkDeed.png", "Resources/Images");
        String orangeFile = resourceManager.getPathToFile("OrangeDeed.png", "Resources/Images");
        String redFile = resourceManager.getPathToFile("RedDeed.png", "Resources/Images");
        String yellowFile = resourceManager.getPathToFile("YellowDeed.png", "Resources/Images");
        String greenFile = resourceManager.getPathToFile("GreenDeed.png", "Resources/Images");
        String dblueFile = resourceManager.getPathToFile("DarkBlueDeed.png", "Resources/Images");
        String waterFile = resourceManager.getPathToFile("WaterWorks.png", "Resources/Images");
        String electricFile = resourceManager.getPathToFile("ElectricCompany.png", "Resources/Images");
        tileImageURLs[0] = purpleFile;
        tileImageURLs[1] = lblueFile;
        tileImageURLs[2] = pinkFile;
        tileImageURLs[3] = orangeFile;
        tileImageURLs[4] = redFile;
        tileImageURLs[5] = yellowFile;
        tileImageURLs[6] = greenFile;
        tileImageURLs[7] = dblueFile;
        tileImageURLs[8] = waterFile;
        tileImageURLs[9] = electricFile;
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
            /* Initialize the elements of the tile Image array to the proper images */
            purpleDeedImage =   new Image(tileImageURLs[0]);
            lBlueDeedImage =    new Image(tileImageURLs[1]);
            pinkDeedImage =     new Image(tileImageURLs[2]);
            orangeDeedImage =   new Image(tileImageURLs[3]);
            redDeedImage =      new Image(tileImageURLs[4]);
            yellowDeedImage =   new Image(tileImageURLs[5]);
            greenDeedImage =    new Image(tileImageURLs[6]);
            dBlueDeedImage =    new Image(tileImageURLs[7]);
            waterDeedImage =    new Image(tileImageURLs[8]);
            electricDeedImage = new Image(tileImageURLs[9]);
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
        // add the images directly to the class variable array of tile image objects
        tileImages[0] = purpleDeedImage;
        tileImages[1] = lBlueDeedImage;
        tileImages[2] = pinkDeedImage;
        tileImages[3] = orangeDeedImage;
        tileImages[4] = redDeedImage;
        tileImages[5] = yellowDeedImage;
        tileImages[6] = greenDeedImage;
        tileImages[7] = dBlueDeedImage;
        tileImages[8] = waterDeedImage;
        tileImages[9] = electricDeedImage;
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

    /**
     * Method that can be used to get an image object for a specific token
     * @param tokenName the name of the token
     * @return the Image object for the token specified
     */
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

    /**
     * Method that can be used to get an image object for a specific tile
     * @param tileName the name of the tile
     * @return the Image object for the tile specified
     */
    public Image getTileImage(int tileName){
        Image returnTilImg = null;
        switch(tileName) {
            case 0:
                returnTilImg = tileImages[0];
                break;
            case 1:
                returnTilImg = tileImages[1];
                break;
            case 2:
                returnTilImg = tileImages[2];
                break;
            case 3:
                returnTilImg = tileImages[3];
                break;
            case 4:
                returnTilImg = tileImages[4];
                break;
            case 5:
                returnTilImg = tileImages[5];
                break;
            case 6:
                returnTilImg = tileImages[6];
                break;
            case 7:
                returnTilImg = tileImages[7];
                break;
            case 8:
                returnTilImg = tileImages[8];
                break;
            case 9:
                returnTilImg = tileImages[9];
                break;
            default:
                break;
        }
        return returnTilImg;
    }

}
