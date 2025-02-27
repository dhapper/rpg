package entity;

import static utilz.Constants.PlayerConstants.DOWN;
import static utilz.Constants.PlayerConstants.LEFT;
import static utilz.Constants.PlayerConstants.RIGHT;
import static utilz.Constants.PlayerConstants.UP;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Overworld;
import graphics.GraphicsHelp;
import graphics.TextBox;
import main.Game;
import utilz.Constants;
import utilz.HelpMethods;
import utilz.LoadSave;

public abstract class GameCharacter extends Entity{

	protected Overworld overworld;
	protected ArrayList<int[][]> mapData;
	protected ArrayList<EnvironmentObject> mapObjects;
	protected ArrayList<GameCharacter> characterList;

	protected boolean left, up, right, down;
	protected float playerSpeed = 0.35f * Game.SCALE;
	protected float xSpeed, ySpeed;
	protected int currentDir = 0;

	protected int aniTick = 0;
	protected int aniIndex = 0;
	protected BufferedImage[][] sprites;
	protected int[] spriteOrder = new int[] {0, 1, 0, 2};
	
	protected boolean activeInteraction = false;
	protected TextBox textBox;

	protected void defaultInit(float x, float y, int width, int height, int spriteRowIndex) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		xDrawOffset = (int) (3 * Game.SCALE);
		yDrawOffset = (int) (3 * Game.SCALE);
		hitboxWidth = (int) (10 * Game.SCALE);
		hitboxHeight = (int) (13 * Game.SCALE);
		this.hitbox = new Rectangle2D.Float(x + xDrawOffset, y + yDrawOffset, hitboxWidth, hitboxHeight);

		loadSprites(spriteRowIndex);
	}

	public void update() {

	}

	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(sprites[currentDir][spriteOrder[aniIndex]], (int) x - xOffset, (int) y - yOffset, width, height, null);

		if(Overworld.SHOW_HITBOXES)
			drawHitbox(g, xOffset, yOffset, hitbox);
	}

	protected void loadSprites(int rowIndex) {
		BufferedImage sheet = LoadSave.LoadImage("spritesheets/"+Constants.SpriteConstants.CHARACTER_FILENAME);
		sprites = new BufferedImage[4][3];

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				sprites[i][j] = LoadSave.LoadSprite(sheet, i + j*3, rowIndex);

		for(int i = 0; i < 3; i++)
			sprites[3][i] = GraphicsHelp.MirrorImage(sprites[1][i]);
	}

	public void move() {
	    xSpeed = 0;
	    ySpeed = 0;


	    if(up && down) {
	    	ySpeed = 0;
	    }else if (up) {
	        ySpeed = -playerSpeed;
	        currentDir = UP;
	    }else if (down) {
	        ySpeed = playerSpeed;
	        currentDir = DOWN;
	    }


	    if(left && right) {
    		xSpeed = 0;
    	}else if (left) {
	        xSpeed = -playerSpeed;
	        currentDir = LEFT;
	    }else if (right) {
	        xSpeed = playerSpeed;
	        currentDir = RIGHT;
	    }

	    // Adjust speeds for diagonal movement
	    if (xSpeed != 0 && ySpeed != 0) {
	        float diagonalFactor = (float) Math.sqrt(0.5);
	        xSpeed *= diagonalFactor;
	        ySpeed *= diagonalFactor;
	    }

	    // Check for horizontal movement
	    if (xSpeed != 0) {
	        if (HelpMethods.CanMoveHere(this, xSpeed, 0, mapData, mapObjects, characterList)) {
	            this.x += xSpeed; // Move horizontally
	            hitbox.x += xSpeed;
	        } else {
	            // Handle hitting a wall directly in the x direction
	            xSpeed = 0; // Stop horizontal movement
	        }
	    }

	    // Check for vertical movement only after horizontal movement check
	    if (ySpeed != 0) {
	        if (HelpMethods.CanMoveHere(this, 0, ySpeed, mapData, mapObjects, characterList)) {
	            this.y += ySpeed; // Move vertically
	            hitbox.y += ySpeed;
	        } else {
	            // Handle hitting a wall directly in the y direction
	            ySpeed = 0; // Stop vertical movement
	        }
	    }


	    // edge check fix, rest is in IsSolid()
	    x = Math.max(-xDrawOffset, x);
	    y = Math.max(-yDrawOffset, y);
	    hitbox.x = x + xDrawOffset;
	    hitbox.y = y + yDrawOffset;

	}

	protected void updateAnimation() {

		if(ySpeed == 0 && xSpeed == 0) {
			aniIndex = 0;
			return;
		}

	    aniTick++;

	    if (aniTick >= 50) {
	        aniTick = 0;
	        aniIndex++;
	        if (aniIndex >= spriteOrder.length)
	            aniIndex = 0;
	    }
	}
	
	public void setOverworldVars(Overworld overworld) {
		this.overworld = overworld;
		this.mapData = overworld.getLayers();
		this.mapObjects = overworld.getLocationManager().getObjects();
		this.characterList = overworld.getLocationManager().getCharacterList();
	}

	public void fixPos(int subtractFromX, int subtractFromY) {
		x -= subtractFromX;
		y -= subtractFromY;

		hitbox.x = x + xDrawOffset;
	    hitbox.y = y + yDrawOffset;
	}

	public void interact() {
		System.out.println("Default Interaction");
	}
	
	public boolean getActiveInteraction() {
		return activeInteraction;
	}
	
	public void setActiveInteraction(boolean activeInteraction) {
		this.activeInteraction = activeInteraction;
	}
	
	public TextBox getTextBox() {
    	return textBox;
    }
	
	public void renderTextBox(Graphics g) {
	
	}

}
