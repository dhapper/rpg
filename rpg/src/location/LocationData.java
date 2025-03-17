package location;

import entity.NPC;

import gamestates.Overworld;
import main.Game;

import static utilz.Constants.Directions.*;

public class LocationData {

	public static void LoadLocationData(int locationIndex, Overworld overworld) {

		NPC npc;
		
		switch(locationIndex) {
		case 0:
			
			npc = new NPC(8*Game.TILES_SIZE, 3*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, 1);
			npc.setOverworldVars(overworld);
			overworld.getLocationManager().getCharacterList().add(npc);

			break;
		case 1:
			
			npc = new NPC(12*Game.TILES_SIZE, 8*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, 2);
			npc.setOverworldVars(overworld);
			overworld.getLocationManager().getCharacterList().add(npc);

			// exit zones
			overworld.getExitZones().add(new ExitZone(0, RIGHT, Game.TILES_SIZE * 15, Game.TILES_SIZE * 1, Game.TILES_SIZE * 2, Game.TILES_SIZE * 2, overworld));
			overworld.getExitZones().add(new ExitZone(0, UP, Game.TILES_SIZE * 3, Game.TILES_SIZE * 0, Game.TILES_SIZE * 9, Game.TILES_SIZE * 6, overworld));
			
			break;
		}
	}

}
