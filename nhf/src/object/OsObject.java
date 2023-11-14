package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameplay.GamePanel;

public class OsObject {
	public BufferedImage skin;
	public String name;
	public boolean collision = false;
	public int x, y;
	public Rectangle hitbox = new Rectangle(0, 0, 48, 48);	//TODO máshogy A teljes tile benne van a hitbox-ban
	public int hitboxDefaultX = 0;
	public int hitboxDefaultY = 0;
	
	public void draw(Graphics2D grap2, GamePanel gameP) {
		grap2.drawImage(skin, x, y, gameP.tileSize, gameP.tileSize, null);
	}
	public void resetHitboxToDefault() {
		hitbox.x = hitboxDefaultX;
		hitbox.y = hitboxDefaultY;
	}
}
