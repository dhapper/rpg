package entity;

import gamestates.Overworld;
import location.ExitZone;
import main.Game;

public class Ladder extends EnvironmentObject{

	private Overworld overworld;
	private int id;

	public Ladder(float x, float y, int index, int id) {
		super(x, y, index);
		// TODO Auto-generated constructor stub
		
		this.id = id;
	}

	@Override
	public void interact() {
		
		overworld.getTransition().startTransition(() -> {
		
			switch (this.id) {
			case 0:
				overworld.initMap(1, 3 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
				
				break;
			case 1:
				overworld.initMap(1, 14 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
				
				break;
			}
			
		});
	}

	public void loadOverworld(Overworld overworld) {
		this.overworld = overworld;

	}


}
