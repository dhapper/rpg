package graphics;

import main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextBox {
	
	private int currentCharIndex = 0;
    private long lastUpdateTime;
    private static final long CHAR_DISPLAY_INTERVAL = 25;
    private String[] fullText;
    private boolean textComplete = false;
    private int index = 0;
    
    // draw vars
    private int xOffset = Game.TILES_SIZE/2;
    private int yOffset = Game.GAME_HEIGHT / 2;
    private int width = Game.GAME_WIDTH - xOffset*2;
    private int height = Game.GAME_HEIGHT - yOffset - xOffset;
    private int outlineOffset = Game.TILES_SIZE/4;
    private int outlineThickness = (int) (1 * Game.SCALE);
    private int innerBoxOffset = outlineOffset + outlineThickness;
    private Font font;
    
    public TextBox(String[] fullText) {
    	this.fullText = fullText;
    	
    	currentCharIndex = 0; // Reset the index for new interactions
        lastUpdateTime = System.currentTimeMillis(); // Initialize the timer
        
        font = new Font("Ariel", Font.PLAIN, 24);
    }
    
    public void updateText() {
    	long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= CHAR_DISPLAY_INTERVAL) {
            if (currentCharIndex < fullText[index].length()) {
                currentCharIndex++;
                lastUpdateTime = currentTime;
            }else {
            	textComplete = true;
            }
        }
    }
    
    public boolean isTextComplete() {
    	return textComplete;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xOffset, yOffset, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(xOffset + outlineOffset, yOffset + outlineOffset, width - outlineOffset*2, height - outlineOffset*2);
        g.setColor(Color.BLACK);
        g.fillRect(xOffset + innerBoxOffset, yOffset + innerBoxOffset, width - innerBoxOffset*2, height - innerBoxOffset*2);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        TextGraphics.DrawWrappedText(g, fullText[index].substring(0, currentCharIndex),
        		Game.TILES_SIZE,
        		Game.GAME_HEIGHT / 2 + Game.TILES_SIZE,
        		Game.GAME_WIDTH - Game.TILES_SIZE*2);
    }
    
    public void completeTextDisplay() {
    	currentCharIndex = fullText[index].length();
    }
    
    public void startNextText() {
    	textComplete = false;
    	currentCharIndex = 0;
    	lastUpdateTime = System.currentTimeMillis();
    	index++;
    }
    
    public boolean isMoreText() {
    	return index < fullText.length - 1;
    }

}
