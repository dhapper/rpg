package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

	protected float x = Game.TILES_SIZE, y = Game.TILES_SIZE;
	protected int width = Game.TILES_SIZE, height = Game.TILES_SIZE;
	protected Rectangle2D.Float hitbox = new Rectangle2D.Float(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
	protected int xDrawOffset = 0;
	protected int yDrawOffset = 0;
	protected int hitboxWidth = Game.TILES_SIZE;
	protected int hitboxHeight = Game.TILES_SIZE;

	protected void drawHitbox(Graphics g, int xOffset, int yOffset, Rectangle2D.Float hitbox) {
		g.setColor(Color.red);
		g.drawRect((int) hitbox.x - xOffset, (int) hitbox.y - yOffset, (int) hitbox.width, (int) hitbox.height);
	}

	public Rectangle2D.Float getHitbox(){
		return hitbox;
	}

}
