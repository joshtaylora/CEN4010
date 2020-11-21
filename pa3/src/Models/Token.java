package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;

/*
* TODO ADD X AND Y COORDINATES
 */
public class Token {
	private Player tokenPlayer;
	private Image tokenImage;
	private int xCoord, yCoord;



	public Token(Player tokenPlayer, Image tokenImage) {
		this.tokenPlayer = tokenPlayer;
		this.tokenImage = tokenImage;
	}

	public Image getTokenImage() {
		return this.tokenImage;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * Josh - removed parameter from getter and used the Player's current tile to
	 * get the tile to have its position returned
	 */
	public int getLocation() {
		Tile tile = this.tokenPlayer.getCurrentTile();
		return tile.getPosition();
	}

	public void setX(int num) {
		xCoord = num;
	}

	public void setY(int num) {
		yCoord = num;
	}

	public int getX() {
		return xCoord;
	}

	public int getY() {
		return yCoord;
	}
}
