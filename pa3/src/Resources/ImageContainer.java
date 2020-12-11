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
    String[] playerImageURLs;

    private static Image[] dieImages;
    private static Image[] tokenImages;
    private static Image[] tileImages;
    private static Image[] playerImages;

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
    private static Image blankDeedImage = null;
    private static Image railRoadImage = null;
    private static Image chanceImage = null;
    private static Image communityChestImage = null;
    private static Image electricCompanyImage = null;
    private static Image goImage = null;
    private static Image goToJailImage = null;
    private static Image jailImage = null;

    private static Image player1Image = null;
    private static Image player2Image = null;
    private static Image player3Image = null;
    private static Image player4Image = null;

    public ImageContainer() {
        // Initialize the ResourceManager object that will be used to retrieve correct working dir path
        this.resourceManager = new ResourceManager();
        // The arrays that will hold the Image objects themselves
        dieImages = new Image[6];

        tokenImages = new Image[4];

        tileImages = new Image[17];

        playerImages = new Image[4];

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
        this.tileImageURLs = new String[17];
        String purpleFile = resourceManager.getPathToFile("PurpleDeed.png", "Resources/Images");
        String lblueFile = resourceManager.getPathToFile("LightBlueDeed.png", "Resources/Images");
        String pinkFile = resourceManager.getPathToFile("PinkDeed.png", "Resources/Images");
        String orangeFile = resourceManager.getPathToFile("OrangeDeed.png", "Resources/Images");
        String redFile = resourceManager.getPathToFile("RedDeed.png", "Resources/Images");
        String yellowFile = resourceManager.getPathToFile("YellowDeed.png", "Resources/Images");
        String greenFile = resourceManager.getPathToFile("GreenDeed.png", "Resources/Images");
        String dblueFile = resourceManager.getPathToFile("DarkBlueDeed.png", "Resources/Images");
        String waterFile = resourceManager.getPathToFile("WaterWorks.png", "Resources/Images");
        String blankDeedFile = resourceManager.getPathToFile("blankTile.png", "Resources/Images");
        String railRoadFile = resourceManager.getPathToFile("RailRoad.png", "Resources/Images");
        String chanceFile = resourceManager.getPathToFile("Chance.png", "Resources/Images");
        String communityChestFile = resourceManager.getPathToFile("CommunityChest.png", "Resources/Images");
        String electricCompanyFile = resourceManager.getPathToFile("Electric.png", "Resources/Images");
        String goFile = resourceManager.getPathToFile("Go.png", "Resources/Images");
        String goToJailFile = resourceManager.getPathToFile("GoToJail.png", "Resources/Images");
        String jailFile = resourceManager.getPathToFile("Jail.png", "Resources/Images");
        tileImageURLs[0] = purpleFile;
        tileImageURLs[1] = lblueFile;
        tileImageURLs[2] = pinkFile;
        tileImageURLs[3] = orangeFile;
        tileImageURLs[4] = redFile;
        tileImageURLs[5] = yellowFile;
        tileImageURLs[6] = greenFile;
        tileImageURLs[7] = dblueFile;
        tileImageURLs[8] = waterFile;
        tileImageURLs[9] = blankDeedFile;
        tileImageURLs[10] = railRoadFile;
        tileImageURLs[11] = chanceFile;
        tileImageURLs[12] = communityChestFile;
        tileImageURLs[13] = electricCompanyFile;
        tileImageURLs[14] = goFile;
        tileImageURLs[15] = goToJailFile;
        tileImageURLs[16] = jailFile;

        /* Array for the Strings containing the URLs for the player images */
        this.playerImageURLs = new String[4];
        String player1File = resourceManager.getPathToFile("PLAYER1.png", "Resources/Images");
        String player2File = resourceManager.getPathToFile("PLAYER2.png", "Resources/Images");
        String player3File = resourceManager.getPathToFile("PLAYER3.png", "Resources/Images");
        String player4File = resourceManager.getPathToFile("PLAYER4.png", "Resources/Images");
        playerImageURLs[0] = player1File;
        playerImageURLs[1] = player2File;
        playerImageURLs[2] = player3File;
        playerImageURLs[3] = player4File;
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
            purpleDeedImage =       new Image(tileImageURLs[0]);
            lBlueDeedImage =        new Image(tileImageURLs[1]);
            pinkDeedImage =         new Image(tileImageURLs[2]);
            orangeDeedImage =       new Image(tileImageURLs[3]);
            redDeedImage =          new Image(tileImageURLs[4]);
            yellowDeedImage =       new Image(tileImageURLs[5]);
            greenDeedImage =        new Image(tileImageURLs[6]);
            dBlueDeedImage =        new Image(tileImageURLs[7]);
            waterDeedImage =        new Image(tileImageURLs[8]);
            blankDeedImage =     new Image(tileImageURLs[9]);
            railRoadImage =         new Image(tileImageURLs[10]);
            chanceImage =           new Image(tileImageURLs[11]);
            communityChestImage =   new Image(tileImageURLs[12]);
            electricCompanyImage =  new Image(tileImageURLs[13]);
            goImage =               new Image(tileImageURLs[14]);
            goToJailImage =         new Image(tileImageURLs[15]);
            jailImage =             new Image(tileImageURLs[16]);

            /* Initialize the elements of the player Image array to the proper images */
            player1Image = new Image(playerImageURLs[0]);
            player2Image = new Image(playerImageURLs[1]);
            player3Image = new Image(playerImageURLs[2]);
            player4Image = new Image(playerImageURLs[3]);
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
        tileImages[9] = blankDeedImage;
        tileImages[10] = railRoadImage;
        tileImages[11] = chanceImage;
        tileImages[12] = communityChestImage;
        tileImages[13] = electricCompanyImage;
        tileImages[14] = goImage;
        tileImages[15] = goToJailImage;
        tileImages[16] = jailImage;


        // add the images directly to the class variable array of player image
        playerImages[0] = player1Image;
        playerImages[1] = player2Image;
        playerImages[2] = player3Image;
        playerImages[3] = player4Image;
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
            case 10:
                returnTilImg = tileImages[10];
                break;
            case 11:
                returnTilImg = tileImages[11];
                break;
            case 12:
                returnTilImg = tileImages[12];
                break;
            case 13:
                returnTilImg = tileImages[13];
                break;
            case 14:
                returnTilImg = tileImages[14];
                break;
            case 15:
                returnTilImg = tileImages[15];
                break;
            case 16:
                returnTilImg = tileImages[16];
                break;
            default:
                break;
        }
        return returnTilImg;
    }

    /**
     * Function used to get the image for a winning player
     * @param index the value of the player rolled
     * @return the Image for the appropriate winner
     */
    public Image getPlayerImage(int index) {
        // Image that will be returned
        Image returnImage = null;

        if (playerImages[index] == null) {
            System.out.println("Error with player image retrieval in ImageContainer class");
            System.exit(1);
        }
        else {
            returnImage = playerImages[index];
        }
        return returnImage;
    }

}
