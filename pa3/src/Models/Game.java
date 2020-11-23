package Models;
/**
 * @author Joshua
 * @version 1.0.1
 */

import java.util.LinkedList;
import java.util.ArrayList;
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
  

}
