package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import gameplay.UtilityTools;

@SuppressWarnings("serial")
/**
 * Egy fegyvernek a hitbox-át tárolja és a kinézetét, mivel akkor a kinézete mint a hitboxa
 * Mivel a BufferImagek-et nem lehet Serializálni azt transient-nek kell állítani
 */
public class AttackHitbox extends Entity implements Serializable{
	private String weapon_type;
	private transient UtilityTools uT = new UtilityTools();
	private int range;
	/**
	 * Konstruktor
	 * @param weapon_type - a fegyver neve, ground_attack stb... szükség van rá, tudjuk mi a kinézete és hogyan állítsuk be a hitbox-ot
	 * @param move_dir - ha kell a fegyver tipushoz, mint a wave_attack-hoz, meg kel helyesen adni
	 */
	public AttackHitbox(String weapon_type, String move_dir) {
		this.weapon_type = weapon_type;
		this.setupAttackType(move_dir);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
	}
	/**
	 * A fegyvertipustól függve beállítja a fegyver hitbox-át, kinézetet és range-ét
	 * @param move_dir - milyen irányba néz a fegyver, up, left stb...
	 */
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
	/**
	 * Visszaadja a fegyver range-ét, fontos lehet mekkora a hatótávja
	 * @return int range
	 */
	public int getRange() {
		return range;
	}
	/**
	 * Visszaadja a fegyver kinézetét
	 * @return BufferedImage skin
	 */
	public BufferedImage getSkin() {
		return skin;
	}
	/**
	 * A paraméterként kapott BufferedImage-ét beállítja a fegyver kinézetének
	 * @param skin - egy BufferedImage
	 */
	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	/**
	 * Visszaadja a fegyver tipusát, ami fontos lehet, milyen tipusú a támadás
	 * @return String weapon_type
	 */
	public String getWeaponType() {
		return weapon_type;
	}
	/**
	 * A Serializálás után kell meghívni ez a metódust, mert a BufferedImage-t nem lehet Serializálni
	 * ezért a fegyver tipusától, amit lehet Serializálni, visszaálítjuk azt
	 */
	public void reLoad() {
		uT = new UtilityTools();
		this.setupAttackType(this.weapon_type);
	}
}
