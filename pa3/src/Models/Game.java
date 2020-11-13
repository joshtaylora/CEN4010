package Models;

import java.util.LinkedList;

public class Game {
    private Player startingPlayer;
    private LinkedList<Player> playerList;
    private Board gameBoard;
    private Dice gameDice;
    private Timer gameTimer;
    private Token[] tokenArray;
    /**
     * What should the main menu pass to the game class to instantiate the game object
     * - Timer object
     * - LinkedList of Player objects
     * -
     */
    Game(LinkedList<Player> playerLinkedList, Timer gameTimer) {
        this.playerList = playerLinkedList;
        this.gameTimer = gameTimer;
    }
}
