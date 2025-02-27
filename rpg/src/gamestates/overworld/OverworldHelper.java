package gamestates.overworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.EnvironmentObject;
import entity.GameCharacter;
import entity.Player;

public class OverworldHelper {

	public static void interact(Player player, ArrayList<EnvironmentObject> objects, ArrayList<GameCharacter> characterList) {

		for(GameCharacter gc : characterList) {
			if(gc.getHitbox().intersects(player.getInteractionBox(0, 0))){
				gc.interact();
				return;
			}
		}

		for(EnvironmentObject object : objects) {
			if(object.getHitbox().intersects(player.getInteractionBox(0, 0))){
					object.interact();
					return;
			}
		}

		System.out.println("Nothing to interact with :(");
	}

	public static ArrayList<GameCharacter> orderCharactersByHeight(ArrayList<GameCharacter> characterList) {

	    ArrayList<GameCharacter> sortedCharacters = new ArrayList<>(characterList);

	    Collections.sort(sortedCharacters, new Comparator<GameCharacter>() {
	        @Override
	        public int compare(GameCharacter gc1, GameCharacter gc2) {
	            return Float.compare(gc1.getHitbox().y, gc2.getHitbox().y);
	        }
	    });

//	    if(Game.DEV_MODE)
//		    for (GameCharacter gc : sortedCharacters)
//		        System.out.println("Character: " + gc.toString() + ", Y Value: " + gc.getHitbox().y);

		return sortedCharacters;
	}

}
