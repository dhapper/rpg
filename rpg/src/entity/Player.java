package entity;

import static utilz.Constants.PlayerConstants.DOWN;
import static utilz.Constants.PlayerConstants.LEFT;
import static utilz.Constants.PlayerConstants.RIGHT;
import static utilz.Constants.PlayerConstants.UP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gamestates.Overworld;
import main.Game;


public class Player extends GameCharacter{

//	private ArrayList<int[][]> mapData;
//	private ArrayList<EnvironmentObject> mapObjects;

//	private boolean left, up, right, down;
//	private float playerSpeed = 0.65f * Game.SCALE;
//	private float xSpeed, ySpeed;
//	private int currentDir = 0;

	private int interactBoxOffsetX, interactBoxOffsetY, interactBoxWidth, interactBoxHeight;

	// animation vars
//	private int aniTick = 0;
//	private int aniIndex = 0;
//	private BufferedImage[][] sprites;
//	private int[] spriteOrder = new int[] {0, 1, 0, 2};

	public Player(float x, float y, int width, int height) {
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
//
//		xDrawOffset = (int) (3 * Game.SCALE);
//		yDrawOffset = (int) (3 * Game.SCALE);
//		hitboxWidth = (int) (10 * Game.SCALE);
//		hitboxHeight = (int) (13 * Game.SCALE);
//		this.hitbox = new Rectangle2D.Float(x + xDrawOffset, y + yDrawOffset, hitboxWidth, hitboxHeight);

		int spriteRowIndex = 0;
		defaultInit(x, y, width, height, spriteRowIndex);

		//loadSprites(0);
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

//	public void loadSprites(int rowIndex) {
//		BufferedImage sheet = LoadSave.LoadImage("spritesheets/"+Constants.SpriteConstants.CHARACTER_FILENAME);
//		sprites = new BufferedImage[4][3];
//		for(int i = 0; i < 3; i++) {
//			for(int j = 0; j < 3; j++) {
//				sprites[i][j] = LoadSave.LoadSprite(sheet, i + j*3, rowIndex);
//			}
//		}
//
//		for(int i = 0; i < 3; i++)
//			sprites[3][i] = GraphicsHelp.MirrorImage(sprites[1][i]);
//	}

//	private void updateAnimation() {
//
//		if(ySpeed == 0 && xSpeed == 0) {
//			aniIndex = 0;
//			return;
//		}
//
//	    aniTick++;
//
//	    if (aniTick >= 50) {
//	        aniTick = 0;
//	        aniIndex++;
//	        if (aniIndex >= spriteOrder.length)
//	            aniIndex = 0;
//	    }
//	}

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

//	public void move() {
//	    xSpeed = 0;
//	    ySpeed = 0;
//
//
//	    if(up && down) {
//	    	ySpeed = 0;
//	    }else if (up) {
//	        ySpeed = -playerSpeed;
//	        currentDir = UP;
//	    }else if (down) {
//	        ySpeed = playerSpeed;
//	        currentDir = DOWN;
//	    }
//
//
//	    if(left && right) {
//    		xSpeed = 0;
//    	}else if (left) {
//	        xSpeed = -playerSpeed;
//	        currentDir = LEFT;
//	    }else if (right) {
//	        xSpeed = playerSpeed;
//	        currentDir = RIGHT;
//	    }
//
//	    // Adjust speeds for diagonal movement
//	    if (xSpeed != 0 && ySpeed != 0) {
//	        float diagonalFactor = (float) Math.sqrt(0.5);
//	        xSpeed *= diagonalFactor;
//	        ySpeed *= diagonalFactor;
//	    }
//
//	    // Check for horizontal movement
//	    if (xSpeed != 0) {
//	        if (HelpMethods.CanMoveHere(this, xSpeed, 0, mapData, mapObjects)) {
//	            this.x += xSpeed; // Move horizontally
//	            hitbox.x += xSpeed;
//	        } else {
//	            // Handle hitting a wall directly in the x direction
//	            xSpeed = 0; // Stop horizontal movement
//	        }
//	    }
//
//	    // Check for vertical movement only after horizontal movement check
//	    if (ySpeed != 0) {
//	        if (HelpMethods.CanMoveHere(this, 0, ySpeed, mapData, mapObjects)) {
//	            this.y += ySpeed; // Move vertically
//	            hitbox.y += ySpeed;
//	        } else {
//	            // Handle hitting a wall directly in the y direction
//	            ySpeed = 0; // Stop vertical movement
//	        }
//	    }
//	}

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
