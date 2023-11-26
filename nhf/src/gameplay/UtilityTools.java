package gameplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class UtilityTools implements Serializable{
	// Arra kell nekünk, hogy ne akkor scaleljen a program képeket amikor kirajzolja azokat, hanem amikor lekreálja azokat
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D tmpGrap2 = scaledImage.createGraphics();
		tmpGrap2.drawImage(original, 0, 0, width, height, null);
		tmpGrap2.dispose();
		
		return scaledImage;
	}
}
