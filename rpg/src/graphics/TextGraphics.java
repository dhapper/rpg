package graphics;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class TextGraphics {
	
	public static void DrawWrappedText(Graphics g, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            // Check if adding the next word exceeds the maxWidth
            if (metrics.stringWidth(currentLine.toString() + word) < maxWidth) {
                currentLine.append(word).append(" "); // Add the word and a space
            } else {
                // Draw the current line and start a new line with the word
                g.drawString(currentLine.toString(), x, y);
                currentLine = new StringBuilder(word + " ");
                y += metrics.getHeight(); // Move to the next line
            }
        }
        
        if (currentLine.length() > 0) {
            g.drawString(currentLine.toString(), x, y);
        }
    }

}
