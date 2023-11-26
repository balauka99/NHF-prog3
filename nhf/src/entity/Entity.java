package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import loaders.SheetLoader;

@SuppressWarnings("serial")
public class Entity implements Serializable{
	protected int x, y, speed;
	protected int maxHealt = 101;
	protected int healt = 101;
	protected boolean dead = false;
	
	public String move_dir = "down";	// Move direction, amerre éppen néz a karakter, vagyis melyik kép van betöltve
	
	public int sheetCnt = 0;
	public int sheetNum = 1;
	
	public Rectangle hitbox;	//Az a terület ami, ha belép egy tile-ra, akkor nem engedi tovább a játékost
	public int hitboxDefaultX, hitboxDefaultY;
	
	public Rectangle view_distance;	// Látó távolság is egy rectangle, ha beleesik a játékos ebbe a "hitboxba" akkor megtámadja
	public int viewDDefaultX, viewDDefaultY;
	
	public boolean collisionOn = false;
	
	protected transient SheetLoader sheet;	// Skinek vagyis sprite sheet-ek, ezekbe lesznek a sheet-ek
	protected transient BufferedImage skin;
	
	protected Weapon weapon;
	
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
	public void resetViewDistanceDefault() {
		view_distance.x = viewDDefaultX;
		view_distance.y = viewDDefaultY;
	}
	public void damaged(int damage) {
		if(healt > 0) {
			healt -= damage;
		}
		if(healt < 0) this.die();
	}
	protected void die() {
		healt = 0;
		dead = true;
	}
	public boolean isDead() {
		return dead;
	}
	public void draw(Graphics2D grap2) {
		System.out.println("Entity drawed");
	}
	public void attackWithWeapon() {
		weapon.attack(x, y, move_dir);
	}
	public int getWeaponDamage() {
		return weapon.getDamage();
	}
	public void moveToPoint(int x, int y) {
		// Menjen az x koordinátájával közelebb
		if(x < this.x) {
			 this.x -= speed;
			 move_dir = "left";
		}
		else if(x > this.x) {
			this.x += speed;
			move_dir = "right";
		}
		// Menjen az y koordinátájával közelebb
		if(y < this.y) this.y -= speed;
		else if(y > this.y) this.y += speed;
	}
	public void reLoad() {}
}