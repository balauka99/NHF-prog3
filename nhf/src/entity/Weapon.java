package entity;

import java.awt.Graphics2D;
import java.io.Serializable;

public class Weapon implements Runnable, Serializable{
	private AttackHitbox attack;
	private boolean attacking = false;
	private transient Thread attackTime;
	private int damage;
	private String move_dir;
	
	public Weapon(String weapon_type, String move_dir) {
		this.move_dir = move_dir;
		attack = new AttackHitbox(weapon_type, this.move_dir);
		switch(weapon_type) {
		case "ground_attack":
			damage = 10;
			break;
		case "eyeball_attack":
			damage = 2;
			break;
		case "wave_attack":
			damage = 25;
			break;
		}
		
	}
	
	public Entity attack(int x, int y, String move_dir) {
		attack.setX(x);
		attack.setY(y);
		this.move_dir = move_dir;
		attackTime = new Thread(this);
		attackTime.start();
		return attack;
	}
	
	public void draw(Graphics2D grap2) {
		grap2.drawImage(attack.getSkin(), attack.getX()+attack.hitboxDefaultX, attack.getY()+attack.hitboxDefaultY, null);
	}
	
	public boolean isAttacking() {
		return attacking;
	}

	@Override
	public void run() {
		try {
			attacking = true;
			switch(attack.getWeaponType()) {
			case "ground_attack":
				Thread.sleep(500);
				attacking = false;
				break;
			case "wave_attack":
				attack.setupAttackType(move_dir);
				Thread.sleep(500);
				attacking = false;
				break;
			case "eyeball_attack":
				Thread.sleep(2000);
				attacking = false;
				break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stopAttacking() {
		attacking = false;
	}
	public AttackHitbox getAttackHitbox() {
		return attack;
	}
	public int getDamage() {
		return damage;
	}
}
