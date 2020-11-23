import java.util.Scanner;
import java.lang.*;

class Game{
  
  private Player currentPlayer;
  private LinkedList<Player> playerList;
  private int numPlayers;
  private int timeLimit;
  private Board gameBoard;
  private Dice gameDice;
  private Timer gameTimer;
  private ArrayList<Token> tokenList;

  public Game(int numPlayer, int timeLimit){
    // assign the number of players
    this.numPlayers = numPlayers;
    this.timeLimit = timeLimit;
    // TODO need to add initializer for tokenList
    this.gameBoard = new Board();
    this.playerList = new LinkedList<Player>();
    this.gameTimer = new Timer(this.timeLimit);
    timeLimit = timeLimit * 60000;

    
    while(currentTimeMillis() < timeInput){
      takeTurn(currentPlayer);
      currentPlayer = currentPlayer.next();
    }
    checkWinner();
    System.out.println("The winner is: " + currentPlayer.name);
  }


  public Tile takeTurn(Player currentPlayer, int spaces, boolean doubles){
    Tile temp;
    //Check doubles to see if another turn should occur
    if(doubles){
      currentPlayer.incrementDoubles();
      //If number of doubles rolled = 3, player goes to jail
      if(currentPlayer.getDoubles() == 3){
        temp = gameBoard.searchTile("In Jail/ Just Visiting");
        currentPlayer.setCurrentTile(temp);
        currentPlayer.setDoubles();
        return temp;
      }
      //Change currentTile and return the tile
      temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
      currentPlayer.setCurrentTile(temp);
      return temp;
      
    }
    //Change currentTile and return the tile
    temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
    currentPlayer.setCurrentTile(temp);
    currentPlayer.setDoubles();
    return temp;
  }

  public Tile takeTurnInJail(Player currentPlayer){
    Dice jailDice = new Dice();
    Tile temp;
    int spaces = jailDice.roll();
    if(jailDice.checkDoubles()){
      temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
      currentPlayer.setCurrentTile(temp);
      currentPlayer.setDoubles();
      return temp;
    }
    else{
      currentPlayer.incrementDoubles();
      if(currentPlayer.getDoubles() == 3){
        currentPlayer.setAccBalance(currentPlayer.getAccBalance() - 50);
        temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
        currentPlayer.setCurrentTile(temp);
        currentPlayer.setDoubles();
        return temp;
      }
    }

  }
  public void buyProperty(Tile currentTile, Player currentPlayer){
    String tileType = currentTile.getType();
    if(tileType.equals("Deed")){
      currentPlayer.purchaseDeed(currentTile);
      currentTile.setOwner(currentPlayer);
      if(currentPlayer.getPropertySet().checkMonopoly()){
        currentTile.setHouses();
      }
      
    }
    else if(tileType.equals("RailRoad")){
      currentTile.setOwner(currentPlayer);
      currentPlayer.increaseRailroads();
    }
    else if(tileType.equals("Utility")){
      currentTile.setOwner(currentPlayer);
      currentPlayer.increaseUtilities();
    }
  }


  public String checkWinner(){
    //Check winner by finding out sum of houses, hotels, properties, and currentMoney
    //propertySum is the method to use
    //make an array to store the property set array
    int sum;
    PropertySet[] tempArray = currentPlayer.getPlayerDeeds();
    String winner = currentPlayer.name();
    for(int i = 0; i < 9; i++){
      sum = sum + tempArray[i].propertySum;
    }
    int sum1;
    currentPlayer = currentPlayer.next();
    String temp1 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum1 = sum1 + tempArray[i].propertySum;
    }
    int sum2;
    currentPlayer = currentPlayer.next();
    String temp2 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum2 = sum2 + tempArray[i].propertySum;
    }
    int sum3;
    currentPlayer = currentPlayer.next();
    String temp3 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum3 = sum3 + tempArray[i].propertySum;
    }
    if(sum > sum1){
      if(sum > sum2){
        if(sum > sum3){
          return winner;
        }
      }
    }
    if( sum1 > sum){
      if(sum1 > sum2){
        if(sum1 > sum3){
          return temp1;
        }
      }
    }
    if( sum2 > sum){
      if(sum2 > sum1){
        if(sum2 > sum3){
          return temp2;
        }
      }
    }
    if(sum3 > sum){
      if(sum3 > sum1){
        if(sum3 > sum2){
          return temp3;
        }
      }
    }
  }

  public Player getCurrentPlayer(){
    return currentPlayer;
  }
}