package gamestates.overworld;

import entity.Player;
import main.Game;

public class Camera {

	// camera offset vars
	private int xLocationOffset, yLocationOffset;
	private int leftBorder = (int) (0.4 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.6 * Game.GAME_WIDTH);
	private int upperBorder = (int) (0.4 * Game.GAME_HEIGHT);
	private int lowerBorder = (int) (0.6 * Game.GAME_HEIGHT);
	private int maxTilesOffsetX;
	private int maxTilesOffsetY;
	private int maxLocationOffsetX;
	private int maxLocationOffsetY;

	public Camera(int locationTilesWide, int locationTilesHigh) {
//		locationTilesWide = layers.get(0).length;
//		locationTilesHigh = layers.get(0)[0].length;
		maxTilesOffsetX = locationTilesWide - Game.TILES_IN_WIDTH;
		maxTilesOffsetY = locationTilesHigh - Game.TILES_IN_HEIGHT;
		maxLocationOffsetX = maxTilesOffsetX * Game.TILES_SIZE;
		maxLocationOffsetY = maxTilesOffsetY * Game.TILES_SIZE;
	}

	// calculates screen offset values when close to border
	public void checkCloseToBorder(Player player) {
		int playerX = (int) player.getHitbox().x;
		int playerY = (int) player.getHitbox().y;
		int xDiff = playerX - xLocationOffset;
		int yDiff = playerY - yLocationOffset;

		//System.out.println(playerX + " "+playerY);

		if(xDiff > rightBorder)
			xLocationOffset += xDiff - rightBorder;
		else if(xDiff < leftBorder)
			xLocationOffset += xDiff - leftBorder;

		if(yDiff > lowerBorder)
			yLocationOffset += yDiff - lowerBorder;
		else if(yDiff < upperBorder)
			yLocationOffset += yDiff - upperBorder;

		if(xLocationOffset > maxLocationOffsetX)
			xLocationOffset = maxLocationOffsetX;

		if (xLocationOffset < 0)
			xLocationOffset = 0;

		if(yLocationOffset > maxLocationOffsetY)
			yLocationOffset = maxLocationOffsetY;

		if (yLocationOffset < 0)
			yLocationOffset = 0;
	}

	public int getxLocationOffset() {
		return xLocationOffset;
	}

	public int getyLocationOffset() {
		return yLocationOffset;
	}

}
