package tile;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage skin;
	private boolean collision = false;	//Ha hamis áttudnak rajta menni az entity osztály tagjai, de ha true akkor nem
	
	public void setSkin(BufferedImage img) {
		skin = img;
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
}
