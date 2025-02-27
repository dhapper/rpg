package utilz;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

	public static class PlayerConstants{
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

}
