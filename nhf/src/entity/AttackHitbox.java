package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import gameplay.UtilityTools;

@SuppressWarnings("serial")
public class AttackHitbox extends Entity implements Serializable{
	private String weapon_type;
	private transient UtilityTools uT = new UtilityTools();
	private transient BufferedImage skin;
	private int range;
	
	public AttackHitbox(String weapon_type, String move_dir) {
		this.weapon_type = weapon_type;
		this.setupAttackType(move_dir);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
	}
	
	public void setupAttackType(String move_dir) {
		switch(weapon_type) {
		case "ground_attack":
			range = 144;
			hitbox = new Rectangle(0, 0, range, range);
			try {
				setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/" + weapon_type + ".png")), range, range));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "eyeball_attack":
			range = 48;
			hitbox = new Rectangle(0, 0, range, range);
			try {
				setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/eyeball_attack.png")), range, range));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "wave_attack":
			range = 144;
			switch(move_dir) {
			case "right":
				hitboxDefaultX = 48+24;
				hitboxDefaultY = 0;
				try {
					setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/" + weapon_type + ".png")), range, range));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "left":
				hitboxDefaultX = -48-24;
				hitboxDefaultY = 0;
				try {
					setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/" + weapon_type + "_left.png")), range, range));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "up":
				hitboxDefaultX = 0;
				hitboxDefaultY = -48-24;
				try {
					setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/" + weapon_type + "_up.png")), range, range));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "down":
				hitboxDefaultX = 0;
				hitboxDefaultY = 48+24;
				try {
					setSkin(uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/attacks/" + weapon_type + "_down.png")), range, range));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY, range, range);
			break;
		}
	}
	
	public int getRange() {
		return range;
	}

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
	public String getWeaponType() {
		return weapon_type;
	}
	public void reLoad() {
		uT = new UtilityTools();
		this.setupAttackType(this.weapon_type);
	}
}
