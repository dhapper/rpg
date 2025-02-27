package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import gamestates.Overworld;
import main.Game;
import utilz.Constants;
import utilz.LoadSave;

public class EnvironmentObject extends Entity {

	private BufferedImage sprite;
	private int index;

	private int shakeDuration = 100;
	private boolean shaking = false;
	private long shakeStartTime;
	private float shakeOffsetX, shakeOffsetY;
	private float originalX, originalY;
	private Random random;

	public EnvironmentObject(float x, float y, int index) {
		this.index = index;

		this.x = x;
		this.y = y;
		this.originalX = x;
		this.originalY = y;

		hitbox.x = x + 1*Game.SCALE;
		hitbox.y = y + 1*Game.SCALE;
		hitbox.width = width - 2*Game.SCALE;
		hitbox.height = height - 2*Game.SCALE;

		random = new Random();

		LoadSprite(index);
	}

	private void LoadSprite(int index) {
		BufferedImage sheet = LoadSave.LoadImage("spritesheets/"+Constants.SpriteConstants.OBJECTS_FILENAME);
		int spritesWide = sheet.getWidth()/Game.TILES_DEFAULT_SIZE;
		sprite = LoadSave.LoadSprite(sheet, index%spritesWide, index/spritesWide);
	}

	public void draw(Graphics g, int xOffset, int yOffset) {
		g.drawImage(sprite, (int) (x - xOffset), (int) (y - yOffset), width, height, null);

		if(Overworld.SHOW_HITBOXES)
			drawHitbox(g, xOffset, yOffset, hitbox);
	}

	public void interact() {

		shake();

//		if(index == 5) {
//			System.out.println("ladder");
//		}else {
//			shake();
//		}
	}

	private void shake() {
	    shaking = true;
	    shakeStartTime = System.currentTimeMillis(); // Track the start time of the shake
	    shakeOffsetX = random.nextInt(5) - 2; // Random offset in the range [-2, 2]
	    shakeOffsetY = random.nextInt(5) - 2; // Random offset in the range [-2, 2]
	}

	public void update() {
	    if (shaking) {
	        long elapsedTime = System.currentTimeMillis() - shakeStartTime;
	        if (elapsedTime < shakeDuration) {
	            // Apply the shake offset
	            this.x = originalX + shakeOffsetX;
	            this.y = originalY + shakeOffsetY;
	        } else {
	            // End shaking after the duration
	            shaking = false;
	            this.x = originalX; // Reset to the original position
	            this.y = originalY; // Reset to the original position
	        }
	    }
	}

	public int getIndex() {
		return index;
	}
}
