package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	private int x, y, speed;
	
	public BufferedImage down1, down2, up1, up2, right1, right2, left1, left2;	// Skinek vagyis sprite sheet-ek, ezekbe lesznek a sheet-ek
	public String move_dir;	// Move direction, amerre éppen néz a karakter, vagyis melyik kép van betöltve
	
	public int sheetCnt = 0;
	public int sheetNum = 1;
	
	public Rectangle hitbox;	//Az a terület ami, ha belép egy tile-ra, akkor nem engedi tovább a játékost
	public int hitboxDefaultX, hitboxDefaultY;
	public boolean collisionOn = false;
	
	//Geterek és seterek
	public int getX() { return x; }
	public void setX(int newx) { x = newx; }
	public int getY() { return y; }
	public void setY(int newy) { y = newy; }
	public int getSpeed() { return speed; }
	public void setSpeed(int newspeed) { speed = newspeed; }
	public void resetHitboxToDefault() {
		hitbox.x = hitboxDefaultX;
		hitbox.y = hitboxDefaultY;
	}
}
