package gamestates.overworld;

import java.awt.event.KeyEvent;

import entity.Player;
import gamestates.Overworld;
import location.LocationManager;

public class OverworldInputs {

	public static void KeyPressed(KeyEvent e, Overworld overworld) {

		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			overworld.getGame().getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			overworld.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			overworld.getGame().getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			overworld.getGame().getPlayer().setRight(true);
			break;

		case KeyEvent.VK_H:
			Overworld.SHOW_HITBOXES = !Overworld.SHOW_HITBOXES;
			break;
			
//		case KeyEvent.VK_P:
//			overworld.setPaused(!overworld.getPaused());
//			break;
			
		case KeyEvent.VK_ENTER:
			if(!overworld.isPaused())
				OverworldHelper.interact(overworld.getGame().getPlayer(), overworld.getLocationManager().getObjects(), overworld.getLocationManager().getCharacterList());
			else if(overworld.isInTextBox()) {
				if(overworld.getActiveNPC().getTextBox().isTextComplete()) {
					if(overworld.getActiveNPC().getTextBox().isMoreText()) {
						overworld.getActiveNPC().getTextBox().startNextText();
					}else {
						overworld.getActiveNPC().setActiveInteraction(false);
						overworld.setPaused(false);
					}
				}else {
					overworld.getActiveNPC().getTextBox().completeTextDisplay();
				}
			}
		}

	}

	public static void KeyReleased(KeyEvent e, Player player) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		}
	}

}
