package gameplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
/**
 * Az osztály eltárol egy felhasználói felületnek a szükséges információit, fontos, hogy a játékos mindig tudja mennyi az élet ereje és a pontja
 */
@SuppressWarnings("serial")
public class UserInterface implements Serializable{
	private GamePanel gameP;
	private Font font;
	private BufferedImage ui_point;
	
	public UserInterface(GamePanel gameP) {
		this.gameP = gameP;
		
		font = new Font("Arial", Font.PLAIN, 40);
		UtilityTools uT = new UtilityTools();
		try {
			ui_point = uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/objects/ui_point.png")), 96, 48);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D grap2) {
		grap2.setFont(font);
		grap2.setColor(Color.cyan);
		grap2.drawImage(ui_point, gameP.tileSize, 0, null);
		grap2.drawString("" + gameP.getPlayerPoint(), 2*gameP.tileSize+13, gameP.tileSize-10);
		if(gameP.isLevelCleared()) grap2.drawString("Room is cleared!", 13*gameP.tileSize+13, gameP.tileSize-10);
	}
}
