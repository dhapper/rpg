package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.GameState;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{

private GamePanel gamePanel;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		switch(GameState.state) {
		case OVERWORLD:
			gamePanel.getGame().getOverworld().mouseMoved(e);
			break;
		case BATTLE:
			gamePanel.getGame().getBattle().mouseMoved(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(GameState.state) {
		case OVERWORLD:
			gamePanel.getGame().getOverworld().mousePressed(e);
			break;
		case BATTLE:
			gamePanel.getGame().getBattle().mousePressed(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
