package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import error.OwnError;
import gameplay.GamePanel;
import gameplay.UtilityTools;
import loaders.SheetLoader;

/**
 * Az osztály az Ős Object, itt megvan minden amire szüksége lehet egy dolognak ami a pályán elhelyezkedik
 */
@SuppressWarnings("serial")
public class OsObject implements Serializable{
	protected int defaultTileSize = 48;
	protected transient BufferedImage skin;
	protected transient SheetLoader sheet;
	private String name;
	public boolean collision = false;
	public int x, y;
	public Rectangle hitbox = new Rectangle(0, 0, defaultTileSize, defaultTileSize);	//TODO máshogy A teljes tile benne van a hitbox-ban
	protected int hitboxDefaultX = 0;
	protected int hitboxDefaultY = 0;
	private boolean objectUsed = false;
	protected boolean unBreakAble = false;
	private boolean destroyed = false;
	/**
	 * Beállítja a kinézetét, ami scalel is, arra a képre ami a kapott fájlnév alatt van
	 * @param skinName
	 */
	protected void setupSkin(String skinName) {
		UtilityTools uT = new UtilityTools();
		try {
			skin = uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/objects/" + skinName + ".png")), defaultTileSize, defaultTileSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D grap2, GamePanel gameP) {
		grap2.drawImage(skin, x, y, null);
	}
	public void resetHitboxToDefault() {
		hitbox.x = hitboxDefaultX;
		hitbox.y = hitboxDefaultY;
	}
	/**
	 * Mindig meg kell hívni a super.useObject() metódust, hogy true legyen a használata
	 */
	public void useObject() {
		objectUsed = true;
	}
	public boolean isObjectUsed() {
		return objectUsed;
	}
	public void reUseObject() {
		objectUsed = false;
	}
	public void destroy() {
		if(!unBreakAble) destroyed = true;
		collision = false;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void reLoad() throws OwnError {
		throw new OwnError("OsObject: reLoad is not implemented!");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
