package utilz;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entity.EnvironmentObject;
import entity.GameCharacter;
import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(GameCharacter gameCharacter, float xSpeed, float ySpeed, ArrayList<int[][]> mapData, ArrayList<EnvironmentObject> objects, ArrayList<GameCharacter> characterList) {

		if(IsMapBorder(gameCharacter, (int) gameCharacter.getHitbox().x, (int) gameCharacter.getHitbox().y, (int) xSpeed,(int) ySpeed, mapData))
			return false;

		if(!IsSolid(gameCharacter, gameCharacter.getHitbox().x, gameCharacter.getHitbox().y, xSpeed, ySpeed, mapData, objects, characterList))
			if(!IsSolid(gameCharacter, gameCharacter.getHitbox().x + gameCharacter.getHitbox().width, gameCharacter.getHitbox().y, xSpeed, ySpeed, mapData, objects, characterList))
				if(!IsSolid(gameCharacter, gameCharacter.getHitbox().x, gameCharacter.getHitbox().y + gameCharacter.getHitbox().height, xSpeed, ySpeed, mapData, objects, characterList))
					if(!IsSolid(gameCharacter, gameCharacter.getHitbox().x + gameCharacter.getHitbox().width, (gameCharacter.getHitbox().y + gameCharacter.getHitbox().height), xSpeed, ySpeed, mapData, objects, characterList))
						return true;

		return false;
	}

	public static boolean IsSolid(GameCharacter gameCharacter, float x, float y, float xSpeed, float ySpeed, ArrayList<int[][]> mapData, ArrayList<EnvironmentObject> objects, ArrayList<GameCharacter> characterList) {
		int futureX = (int) (x + xSpeed);
		int futureY = (int) (y + ySpeed);

		float xIndex = futureX / Game.TILES_SIZE;
		float yIndex = futureY / Game.TILES_SIZE;

		for(int layer : Constants.MapConstants.SOLID_LAYERS) {


			// edge check fix, rest is in move()
			if(xIndex >= mapData.get(layer).length) {
				gameCharacter.fixPos(1, 0);
				return true;
			}

			if(yIndex >= mapData.get(layer)[0].length) {
				gameCharacter.fixPos(0, 1);
				return true;
			}


		    int value = mapData.get(layer)[(int) xIndex][(int) yIndex];

		    // Check if we found a solid value
		    if(value != -1) {
		        boolean isWalkable = false; // Assume it's not walkable
		        for(int overlapLayer : Constants.MapConstants.WALKABLE_OVERLAP_LAYERS) {
		            int overlapValue = mapData.get(overlapLayer)[(int) xIndex][(int) yIndex];
		            if(overlapValue != -1) {
		                isWalkable = true; // Found a walkable value
		                break; // No need to check further, we found walkable
		            }
		        }

		        if (!isWalkable) {
		            return true; // If not walkable, return solid
		        }
		    }
		}

		for(EnvironmentObject object : objects) {
			if(object.getHitbox().contains(futureX, futureY))
				return true;
		}

		for(GameCharacter gc : characterList) {
		    if(gc != gameCharacter) {
		        Rectangle2D.Float futureHitbox = new Rectangle2D.Float(
		            gameCharacter.getHitbox().x + xSpeed,
		            gameCharacter.getHitbox().y + ySpeed,
		            gameCharacter.getHitbox().width,
		            gameCharacter.getHitbox().height
		        );

		        if(futureHitbox.intersects(gc.getHitbox())) {
		            return true;
		        }
		    }
		}



		return false;
	}

	public static boolean IsMapBorder(GameCharacter gameCharacter, int x, int y, int xSpeed, int ySpeed, ArrayList<int[][]> mapData) {
		int maxWidth = mapData.get(0).length * Game.TILES_SIZE;
		int maxHeight = mapData.get(0)[0].length * Game.TILES_SIZE;

		int futureX = x + xSpeed;
		int futureY = y + ySpeed;

		if(futureX < 0 || futureX + gameCharacter.getHitbox().width >= maxWidth)
			return true;

		if(futureY < 0 || futureY + gameCharacter.getHitbox().height >= maxHeight)
			return true;

		return false;
	}


}
