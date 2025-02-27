package entity;

import graphics.TextBox;
import main.Game;

public class NPC extends GameCharacter {

    private float originalX;
    private String[] fullText = {
    		"Victory will never come to those who aren't prepared to face defeat",
    		"In the vibrant world of Pokémon, trainers embark on epic adventures filled with friendship, discovery, and competition. Each Pokémon has unique abilities and characteristics, fostering a diverse ecosystem where battles, contests, and training sessions bring out the best in these extraordinary creatures. From Route 1 to the peaks of Mt. Silver, trainers encounter wild Pokémon and engage in Gym battles, striving to become Champions. The bond between trainers and their Pokémon often evolves into deep friendships that highlight themes of loyalty and perseverance. Throughout their journey, trainers learn invaluable lessons about courage and the importance of believing in oneself.",
    		"Prepare for battle, plebeian",
    		};

    public NPC(float x, float y, int width, int height, int spriteRowIndex) {
        defaultInit(x, y, width, height, spriteRowIndex);
        originalX = x;
        left = true;
        right = false;
    }

    @Override
    public void update() {
        if (activeInteraction) {
            textBox.updateText();
            return;
        }

        updateDir();
        move();
        updateAnimation();
    }

    private void updateDir() {
        if (x < originalX - Game.TILES_SIZE * 5) {
            left = false;
            right = true;
        } else if (x > originalX) {
            left = true;
            right = false;
        }   
    }
    
    @Override
    public void interact() {
        overworld.setPaused(true);
        activeInteraction = true;
        textBox = new TextBox(fullText);
        overworld.setInTextBox(true);
        overworld.setActiveNPC(this);
        
    }
    
}