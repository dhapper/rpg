package location;

import java.util.ArrayList;

import entity.NPC;
import entity.Player;
import gamestates.Overworld;
import main.Game;

public class LocationData {

	public static void LoadLocationData(int locationIndex, Overworld overworld) {

		NPC npc;
		
		switch(locationIndex) {
		case 0:
			overworld.getGame().getPlayer().setPos(2 * Game.TILES_SIZE, 2 * Game.TILES_SIZE);
			
			npc = new NPC(8*Game.TILES_SIZE, 3*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, 1);
			npc.setOverworldVars(overworld);
			overworld.getLocationManager().getCharacterList().add(npc);

			break;
		case 1:
			overworld.getGame().getPlayer().setPos(3 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
			
			npc = new NPC(10*Game.TILES_SIZE, 5*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, 2);
			npc.setOverworldVars(overworld);
			overworld.getLocationManager().getCharacterList().add(npc);

			break;
		}
	}

}
