package main;

import java.awt.Color;
import java.awt.Graphics;

import entity.Player;
import gamestates.GameState;
import gamestates.Overworld;

public class Game implements Runnable {

	//private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    // gamestates
    private Overworld overworld;private Player player;

    // specs
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

	// sizes
    public static int TILES_DEFAULT_SIZE = 16;
    public static float SCALE = 4;
    public static int TILES_IN_WIDTH = 16;
    public static int TILES_IN_HEIGHT = 12;
    public static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static int GAME_HEIGHT= TILES_SIZE * TILES_IN_HEIGHT;

    public static boolean DEV_MODE = false;

	public Game() {
		initClasses();
        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
	}


	 private void initClasses() {
        overworld = new Overworld(this);

    }

	private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();

    }

	private void update() {

		switch (GameState.state) {
        case OVERWORLD:
            overworld.update();
            break;
        default:
            break;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(100, 100, 100, 100);

		switch (GameState.state) {
        case OVERWORLD:
            overworld.draw(g);
            break;
        default:
            break;
		}
	}

	@Override
    public void run() {

        // game loop
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("update");
            }

        }
    }

	public Overworld getOverworld() {
		return overworld;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public void windowFocusLost() {
		player.resetMovementBools();

	}
}
