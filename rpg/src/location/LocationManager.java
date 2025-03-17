package location;

import static utilz.Constants.AnimationConstants.WATER_EDGE_DURATION;
import static utilz.Constants.AnimationConstants.WATER_MOVE_DURATION;
import static utilz.Constants.AnimationConstants.WATER_REST_DURATION;
import static utilz.Constants.MapConstants.ANIMATION_LAYER;
import static utilz.Constants.MapConstants.LAYER_SHEET_NAMES;
import static utilz.Constants.MapConstants.OBJECT_LAYER;
import static utilz.Constants.SpriteConstants.ANIMATION_FILENAME;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import entity.EnvironmentObject;
import entity.GameCharacter;
import entity.Ladder;
import gamestates.Overworld;
import graphics.Animation;
import main.Game;
import utilz.LoadSave;

public class LocationManager {

	private Overworld overworld;
	private ArrayList<BufferedImage[]> sprites;
	private Animation[] animations;
	private ArrayList<EnvironmentObject> objects;
	private ArrayList<GameCharacter> characterList;
	//private ArrayList<int[][]> layers;

	public LocationManager (Overworld overworld) {
		this.overworld = overworld;

		characterList = new ArrayList<GameCharacter>();

		loadSprites();
		loadAnimations();
	}

	public void update() {
		for(Animation anim: animations)
			anim.update();

		for(EnvironmentObject obj : objects)
			obj.update();
	}

	private void loadAnimations() {
		animations = new Animation[4];
		animations[0] = new Animation(0, ANIMATION_FILENAME, 7, new int[] {WATER_REST_DURATION, WATER_MOVE_DURATION,
				WATER_MOVE_DURATION, WATER_MOVE_DURATION, WATER_MOVE_DURATION, WATER_MOVE_DURATION, WATER_MOVE_DURATION});
		animations[1] = new Animation(1, ANIMATION_FILENAME, 4, new int[] {WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION});
		animations[2] = new Animation(2, ANIMATION_FILENAME, 4, new int[] {WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION});
		animations[3] = new Animation(3, ANIMATION_FILENAME, 4, new int[] {WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION, WATER_EDGE_DURATION});

	}

	public void loadObjects() {
		int ladderID = 0;
		objects = new ArrayList<EnvironmentObject>();
		for(int i = 0; i < overworld.getLayers().get(0).length; i++) {
			for(int j = 0; j < overworld.getLayers().get(0)[0].length; j++) {
				int spriteIndex = overworld.getLayers().get(OBJECT_LAYER)[i][j];
				if(spriteIndex != -1) {
					if(spriteIndex == 4) {	//ladder
						objects.add(new Ladder(i*Game.TILES_SIZE, j*Game.TILES_SIZE, spriteIndex, overworld.getLocationIndex()*10 + ladderID));
						ladderID++;
					}else {
						objects.add(new EnvironmentObject(i*Game.TILES_SIZE, j*Game.TILES_SIZE, spriteIndex));
					}
				}
			}
		}
	}

	public void draw(Graphics g, int xOffset, int yOffset) {

		for(int k = 0; k < overworld.getLayers().size(); k++) {
			for(int i = 0; i < overworld.getLayers().get(0).length; i++) {
				for(int j = 0; j < overworld.getLayers().get(0)[0].length; j++) {

					int spriteIndex = overworld.getLayers().get(k)[i][j];
					if(k == OBJECT_LAYER)
						continue;

					if(k == ANIMATION_LAYER) {	// animation layers
						for(Animation anim : animations) {
							if(spriteIndex == anim.getRowIndex()) {
								g.drawImage(anim.getCurrentFrame(), i*Game.TILES_SIZE - xOffset, j*Game.TILES_SIZE - yOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
							}
						}
					}

					else if(spriteIndex != -1)
						g.drawImage(sprites.get(k)[spriteIndex], i*Game.TILES_SIZE - xOffset, j*Game.TILES_SIZE - yOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
				}
			}
		}

		for(EnvironmentObject obj : objects)
			obj.draw(g, xOffset, yOffset);
	}

	public void loadSprites() {
		if(Game.DEV_MODE)
			System.out.println("Loading Sprite Sheets...");

		sprites = new ArrayList<BufferedImage[]>();
		for(String fileName : LAYER_SHEET_NAMES) {
			BufferedImage sheet = LoadSave.LoadImage("spritesheets/"+fileName);
			int spritesWide = sheet.getWidth()/Game.TILES_DEFAULT_SIZE;
			int spritesHigh = sheet.getHeight()/Game.TILES_DEFAULT_SIZE;

			sprites.add(new BufferedImage[spritesWide*spritesHigh]);

			for(int i = 0 ; i < spritesWide; i++) {
				for(int j = 0 ; j < spritesHigh; j++) {
					sprites.get(sprites.size()-1)[j*spritesWide+i] = LoadSave.LoadSprite(sheet, i, j);
				}
			}

		}
	}

	public static ArrayList<int[][]> LoadLocation(int locationIndex) {
        ArrayList<int[][]> layers = new ArrayList<>();
        String fileName = "mapdata/MAP_DATA_"+locationIndex+".txt";

        try (InputStream inputStream = LocationManager.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                System.err.println("Error: File not found: " + fileName);
                return null;
            }

            ArrayList<int[]> currentLayer = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    if (!currentLayer.isEmpty()) {
                        layers.add(currentLayer.toArray(new int[0][]));
                        currentLayer.clear();
                    }
                    continue;
                }

                int[] row = Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                currentLayer.add(row);
            }

            if (!currentLayer.isEmpty()) {
                layers.add(currentLayer.toArray(new int[0][]));
            }

            if(Game.DEV_MODE)
            	System.out.println("Map loaded successfully with " + layers.size() + " layers.");

            return layers;

        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
        }
        return null;
    }

	public ArrayList<EnvironmentObject> getObjects(){
		return objects;
	}

	public ArrayList<GameCharacter> getCharacterList(){
		return characterList;
	}

}
