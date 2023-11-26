package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameplay.UtilityTools;
import loaders.SheetLoader;

enum Animation_States {Still, Move, Attack}

public class EyeBall extends Entity implements Runnable{
	private transient Thread animation;
	private Animation_States aniState = Animation_States.Still;
	private int lastX, lastY;
	
	public EyeBall() {
		// Sheet betöltése
		UtilityTools uT = new UtilityTools();
		sheet = new SheetLoader("/entity/eyeball_sheet.png", 6, 1, 16, 16);
		weapon = new Weapon("eyeball_attack", move_dir);
		// Hitbox setup
		hitbox = new Rectangle(0, 0, 48, 48);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		// View distance setupja, meddig lássol el a szem
		view_distance = new Rectangle(-96, -96, 240, 240);	// 5 tile-ig lát el a kis szem
		viewDDefaultX = view_distance.x;
		viewDDefaultY = view_distance.y;
		// Default adatok setup
		healt = 501;	// Azért kell az 1, hogy ne pont kerek szám legyen, különben a damaged metódusban a die metódus sose lesz meghívva
		speed = 1;
		move_dir = "left";
		animationStart();
	}
	
	@Override
	public void draw(Graphics2D grap2) {
		if(weapon.isAttacking()) weapon.draw(grap2);
		grap2.drawImage(skin, getX(), getY(), null); //Az up2-be rakjuk bele a kivágott sheet-et
	}
	
	@Override
	protected void die() {
		super.die();
		skin = sheet.get(5);
	}
	
	public void animationStart() {
		animation = new Thread(this);
		aniState = Animation_States.Still;
		animation.start();
	}
	
	@Override
	public void attackWithWeapon() {
		super.attackWithWeapon();
		skin = sheet.get(4);
		aniState = Animation_States.Attack;
	}
	
	@Override
	public void moveToPoint(int x, int y) {
		if(aniState != Animation_States.Attack) {
			super.moveToPoint(x, y);
			aniState = Animation_States.Move;
		}
	}
	
	@Override
	public void run() {
		int ind = 0;
		int stay_stillCount = 0;
		while(!this.isDead()) {
			// Támadás animáció
			if(aniState == Animation_States.Attack) {
				skin = sheet.get(4);
				if(!weapon.isAttacking()) aniState = Animation_States.Still;
			}

			// Egyhelyben álló animáció
			if(aniState == Animation_States.Still) {
					skin = sheet.get(ind);
					if(ind == 0) ind = 1;
					else ind = 0;
			}
			
			// Mozgásban lévő animáció
			if(aniState == Animation_States.Move) {
				if((lastX == getX() && lastY == getY())) {
					aniState = Animation_States.Still;
					stay_stillCount = 0;
				}
				switch(move_dir) {
				case "left":
					skin = sheet.get(3);
					break;
				case "right":
					skin = sheet.get(2);
					break;
				}
				lastX = getX();
				lastY = getY();
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		skin = sheet.get(5);
	}
	@Override
	public void reLoad() {
		sheet = new SheetLoader("/entity/eyeball_sheet.png", 6, 1, 16, 16);
		this.weapon.getAttackHitbox().reLoad();
		animationStart();
	}
}
