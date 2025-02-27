package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import entity.EnvironmentObject;
import entity.GameCharacter;
import entity.Ladder;
import entity.NPC;
import entity.Player;
import gamestates.overworld.Camera;
import gamestates.overworld.OverworldHelper;
import gamestates.overworld.OverworldInputs;
import location.LocationData;
import location.LocationManager;
import main.Game;

public class Overworld extends State implements StateMethods {

	public static boolean SHOW_HITBOXES = false;
	
	private Game game;
	private LocationManager locationManager;
	private ArrayList<int[][]> layers;
	private Camera camera;
	private boolean paused = false;

	private boolean inTextBox = false;
	private NPC activeNPC;
	
	public Overworld(Game game) {
		super(game);
		this.game = game;

		locationManager = new LocationManager(this);

		initMap(0);
	}

	public void initMap(int locationIndex) {
		layers = LocationManager.LoadLocation(locationIndex);
		camera = new Camera(layers.get(0).length, layers.get(0)[0].length);

		locationManager.loadObjects();
		
		locationManager.getCharacterList().clear();
		
		game.setPlayer(new Player(0, 0, Game.TILES_SIZE, Game.TILES_SIZE));
		game.getPlayer().setOverworldVars(this);
		locationManager.getCharacterList().add(game.getPlayer());
		
		LocationData.LoadLocationData(locationIndex, this);

		for(EnvironmentObject object : locationManager.getObjects()) {
			if(object instanceof Ladder)
				((Ladder) object).loadOverworld(this);
		}

	}

	public ArrayList<int[][]> getLayers(){
		return layers;
	}

	private ArrayList<GameCharacter> charactersSortedByY;

	@Override
	public void update() {

		if(paused) {
			for(GameCharacter gc : charactersSortedByY)
				if(gc.getActiveInteraction()) {
					gc.update();
				}
			
			return;	
		}
		
		for(GameCharacter gc : locationManager.getCharacterList())
			gc.update();

		locationManager.update();

		charactersSortedByY = OverworldHelper.orderCharactersByHeight(locationManager.getCharacterList());

		camera.checkCloseToBorder(game.getPlayer());

	}

	@Override
	public void draw(Graphics g) {
		
		locationManager.draw(g, camera.getxLocationOffset(), camera.getyLocationOffset());

		for(GameCharacter gc : charactersSortedByY)
			gc.render(g, camera.getxLocationOffset(), camera.getyLocationOffset());
		
		if(paused)
			for(GameCharacter gc : charactersSortedByY)
				if(gc.getActiveInteraction())
					if(gc.getTextBox() != null)
						gc.getTextBox().draw(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		OverworldInputs.KeyPressed(e, this);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		OverworldInputs.KeyReleased(e, game.getPlayer());

	}
	
	public LocationManager getLocationManager() {
		return locationManager;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public boolean isInTextBox() {
		return inTextBox;
	}
	
	public void setInTextBox(boolean inTextBox) {
		this.inTextBox = inTextBox;
	}
	
	public NPC getActiveNPC() {
		return activeNPC;
	}
	
	public void setActiveNPC(NPC activeNPC) {
		this.activeNPC = activeNPC;
	}

}
