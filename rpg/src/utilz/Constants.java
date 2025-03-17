package utilz;

import java.util.ArrayList;
import java.util.Arrays;

import main.Game;

public class Constants {

	public static class Directions{
		public static final int DOWN = 0;
		public static final int LEFT = 1;
		public static final int UP = 2;
		public static final int RIGHT = 3;
	}

	public static class SpriteConstants{
		public static final String BACKGROUND_FILENAME = "0_background.png";
		public static final String ANIMATION_FILENAME = "1_animations.png";
		public static final String WALKABLE_FILENAME = "2_walkable.png";
		public static final String SOLIDS_FILENAME = "3_solids.png";
		public static final String EXTRAS_FILENAME = "4_extras.png";
		public static final String OBJECTS_FILENAME = "5_objects.png";
		public static final String CHARACTER_FILENAME = "chars.png";
	}

	public static class MapConstants{
		public static final ArrayList<String> LAYER_SHEET_NAMES = new ArrayList<>(Arrays.asList(
				SpriteConstants.BACKGROUND_FILENAME,
				SpriteConstants.ANIMATION_FILENAME,
				SpriteConstants.WALKABLE_FILENAME,
				SpriteConstants.SOLIDS_FILENAME,
				SpriteConstants.EXTRAS_FILENAME,
				SpriteConstants.OBJECTS_FILENAME));

		public static final int[] SOLID_LAYERS = new int[]{
				LAYER_SHEET_NAMES.indexOf(SpriteConstants.ANIMATION_FILENAME),
				LAYER_SHEET_NAMES.indexOf(SpriteConstants.SOLIDS_FILENAME)};

		public static final int[] WALKABLE_OVERLAP_LAYERS = new int[]{
				LAYER_SHEET_NAMES.indexOf(SpriteConstants.WALKABLE_FILENAME)};

		public static final int ANIMATION_LAYER = LAYER_SHEET_NAMES.indexOf(SpriteConstants.ANIMATION_FILENAME);
		public static final int OBJECT_LAYER = LAYER_SHEET_NAMES.indexOf(SpriteConstants.OBJECTS_FILENAME);
	}

	public static class AnimationConstants{
		public static final int WATER_REST_DURATION = 500;
		public static final int WATER_MOVE_DURATION = 30;
		public static final int WATER_EDGE_DURATION = 100;
	}
	
	public static class BattleGraphicConstants{
		public static int X_OFFSET_ARMOUR = Game.TILES_SIZE * 2;
		public static int X_OFFSET_ITEMS = Game.TILES_SIZE * 4;
		public static int X_OFFSET_PLAYER = Game.TILES_SIZE * 4;
		public static int Y_OFFSET_PLAYER = (int) (Game.TILES_SIZE * 4.5);
		public static int PLAYER_SIZE = Game.TILES_SIZE * 2;
		public static int ITEM_SIZE = Game.TILES_SIZE;
		public static int X_ADJUST_PLAYER = Game.GAME_WIDTH - PLAYER_SIZE;
		public static int X_ADJUST_ITEM = Game.GAME_WIDTH - ITEM_SIZE;
		public static int Y_OFFSET_1 = ITEM_SIZE * 2;
		public static int Y_OFFSET_2 = ITEM_SIZE * 4;
		public static int Y_OFFSET_3 = ITEM_SIZE * 6;
		public static int Y_OFFSET_4 = ITEM_SIZE * 8;
		
		public static int MARGIN = (int) (2 * Game.SCALE);
		public static int BORDER = (int) (1 * Game.SCALE);
		
		public static int X_BAR_OFFSET = Game.TILES_SIZE * 2 - MARGIN;
		public static int HEALTH_BAR_HEIGHT = (int) (Game.SCALE * 6);
		public static int STAMINA_BAR_HEIGHT = (int) (Game.SCALE * 6);
		public static int Y_HEALTH_BAR_OFFSET = (int) (Game.SCALE * 3); 
		public static int Y_STAMINA_BAR_OFFSET = Y_HEALTH_BAR_OFFSET + HEALTH_BAR_HEIGHT;
		public static int Y_ESSENCE_BAR_OFFSET = Y_STAMINA_BAR_OFFSET + STAMINA_BAR_HEIGHT;
		public static int BAR_WIDTH = Game.TILES_SIZE * 4;
		public static int X_ADJUST_BAR = Game.GAME_WIDTH - BAR_WIDTH;
		
		public static int X_EXIT_OFFSET = 0;
		public static int Y_EXIT_OFFSET = 0;
		public static int EXIT_SIZE = Game.TILES_SIZE;
		
	}
	
	public static class BattleLogicConstants{
		public static int HELM_BUTTON_INDEX = 0;
		public static int CHESTPIECE_BUTTON_INDEX = 1;
		public static int LEGGINGS_BUTTON_INDEX = 2;
		public static int FOOTWEAR_BUTTON_INDEX = 3;
		public static int SWORD_BUTTON_INDEX = 4;
		public static int SHIELD_BUTTON_INDEX = 5;
		
		public static int EXIT_BUTTON_INDEX = 6;
	}

}
