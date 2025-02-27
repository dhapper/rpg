package main;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {
		this.game = game;

		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void updateGame() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.render(g);
	}

	public Game getGame() {
		return game;
	}
}
