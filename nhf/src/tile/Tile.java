package tile;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Ebben az osztályban van elrátolva egyx tile-nak mimden adata, az OsPanel-ben van definiálva mekkora egy tile
 * 48x48 a rendes mére a scale után, de minden kép 16x16-os méretben lett megrajzolva
 */
@SuppressWarnings("serial")
public class Tile implements Serializable{
	/**
	 * Tile neve, fontos hogy tudjuk amikor betöltjük
	 */
	private String name;
	/**
	 * Kinézete a tile-nak
	 */
	private transient BufferedImage skin;
	/**
	 * Ha hamis áttudnak rajta menni az entity(leginkább a Player-re vonatkozik) osztály tagjai, de ha true akkor nem
	 */
	private boolean collision = false;
	
	public void setSkin(BufferedImage img, String name) {
		skin = img;
		this.name = name;
	}
	public BufferedImage getSkin() {
		return skin;
	}
	public void noCollision() {
		collision = false;
	}
	public void enableCollision() {
		collision = true;
	}
	public boolean isSolid() {
		return collision;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
