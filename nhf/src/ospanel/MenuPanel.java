package ospanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameplay.UtilityTools;

/**
 * Egy menü Panel, ami igazából csak beállítja a hátterét, a MainMenu állítja be az előre definiált állását, gombjait stb...
 */
@SuppressWarnings("serial")
public class MenuPanel extends OsPanel{
	private BufferedImage backGround;
	public MenuPanel() {
		super();
		UtilityTools uT = new UtilityTools();
		try {
			backGround = uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/menu/menu_backG.png")), 960, 720);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grap2 = (Graphics2D) g;
        grap2.drawImage(backGround, 0, 0, null);
    }
}
