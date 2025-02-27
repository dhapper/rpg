package entity;

import gamestates.Overworld;

public class Ladder extends EnvironmentObject{

	public Overworld overworld;

	public Ladder(float x, float y, int index) {
		super(x, y, index);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interact() {
		overworld.initMap(1);
	}

	public void loadOverworld(Overworld overworld) {
		this.overworld = overworld;

	}


}
