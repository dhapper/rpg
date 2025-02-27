package graphics;

import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Animation {

	// animation vars
	private int aniTick = 0;
	private int aniIndex = 0;
	private BufferedImage[] sprites;

	private int numOfFrames;
	private int[] frameDurations;

	private BufferedImage sheet;
	private int rowIndex;

	public Animation(int index, String fileName, int numOfFrames, int[] frameDurations) {
		this.numOfFrames = numOfFrames;
		this.frameDurations = frameDurations;

		this.sheet = LoadSave.LoadImage("spritesheets/"+fileName);

		this.rowIndex = index * this.sheet.getWidth()/Game.TILES_DEFAULT_SIZE;

		loadSprites(index);
	}

	private void loadSprites(int index) {
		sprites = new BufferedImage[numOfFrames];
		for(int i = 0; i < this.numOfFrames; i++) {
			sprites[i] = LoadSave.LoadSprite(sheet, i, index);
		}
	}

	public void update() {
		aniTick++;
	    if (aniTick >= frameDurations[aniIndex]) {
	        aniTick = 0;
	        aniIndex++;
	        if (aniIndex >= frameDurations.length)
	            aniIndex = 0;
	    }
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public BufferedImage getCurrentFrame() {
		return sprites[aniIndex];
	}

}
