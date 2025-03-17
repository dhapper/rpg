package entity;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gamestates.Overworld;
import main.Game;


public class Player extends GameCharacter{

	private int interactBoxOffsetX, interactBoxOffsetY, interactBoxWidth, interactBoxHeight;

	public Player(float x, float y, int width, int height) {
		int spriteRowIndex = 0;
		defaultInit(x, y, width, height, spriteRowIndex);
	}

	@Override
	public void update() {
		move();
		updateInteractBoxOffset(currentDir);

		updateAnimation();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(sprites[currentDir][spriteOrder[aniIndex]], (int) x - xOffset, (int) y - yOffset, width, height, null);

		if(Overworld.SHOW_HITBOXES) {
			drawHitbox(g, xOffset, yOffset, hitbox);
			g.setColor(Color.WHITE);
			g.drawRect((int) x - xOffset + interactBoxOffsetX, (int) y - yOffset + interactBoxOffsetY, interactBoxWidth, interactBoxHeight);
		}
	}

	private void updateInteractBoxOffset(int currentDir) {
		if(currentDir == UP) {
			interactBoxWidth = (int) (2 * Game.SCALE);
			interactBoxHeight = (int) (10 * Game.SCALE);
			interactBoxOffsetX = (int) (Game.TILES_SIZE/2 - 1 * Game.SCALE);
			interactBoxOffsetY = -interactBoxHeight;
		}else if(currentDir == DOWN) {
			interactBoxWidth = (int) (2 * Game.SCALE);
			interactBoxHeight = (int) (10 * Game.SCALE);
			interactBoxOffsetX = (int) (Game.TILES_SIZE/2 - 1 * Game.SCALE);
			interactBoxOffsetY = (int) (Game.TILES_SIZE + 1 * Game.SCALE);
		}else if(currentDir == LEFT) {
			interactBoxWidth = (int) (10 * Game.SCALE);
			interactBoxHeight = (int) (2 * Game.SCALE);
			interactBoxOffsetX = -interactBoxWidth;
			interactBoxOffsetY = (int) (Game.TILES_SIZE/2 - 1 * Game.SCALE);
		}else if(currentDir == RIGHT) {
			interactBoxWidth = (int) (10 * Game.SCALE);
			interactBoxHeight = (int) (2 * Game.SCALE);
			interactBoxOffsetX = Game.TILES_SIZE;
			interactBoxOffsetY = (int) (Game.TILES_SIZE/2 - 1 * Game.SCALE);
		}
	}

	public Rectangle2D.Float getInteractionBox(int xOffset, int yOffset){

		return new Rectangle2D.Float((int) x - xOffset + interactBoxOffsetX, (int) y - yOffset + interactBoxOffsetY, interactBoxWidth, interactBoxHeight);
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void resetMovementBools() {
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
		hitbox.x = x + xDrawOffset;
		hitbox.y = y + yDrawOffset;
	}
}
