package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.GameState;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {


		switch(GameState.state) {
		case OVERWORLD:
			gamePanel.getGame().getOverworld().keyPressed(e);
			break;
		case BATTLE:
			gamePanel.getGame().getBattle().keyPressed(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch(GameState.state) {
		case OVERWORLD:
			gamePanel.getGame().getOverworld().keyReleased(e);
			break;
		case BATTLE:
			gamePanel.getGame().getBattle().keyReleased(e);
			break;
		default:
			break;

		}

	}


}
