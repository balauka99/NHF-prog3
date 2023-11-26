package loaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gameplay.UtilityTools;

public class SheetLoader extends ArrayList<BufferedImage> implements Serializable{
	private BufferedImage sheet;
	private int maxcol, maxrow, frameWidth, frameHeight, currentFrame;
	// Betölti a megadott filename fájlt egy BufferedImage-be és a megadott adatok szerint szétvágdossa és hozzáadja azokat a listához
	public SheetLoader(String filename, int mcol, int mrow, int fHeight, int fWidth) {
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		maxcol = mcol;
		maxrow = mrow;
		frameHeight = fHeight;
		frameWidth = fWidth;
		currentFrame = 0;
		makeArray();
	}
	private void makeArray() {
		UtilityTools uT = new UtilityTools();
		int currentLoad = 0;
		for(int i = 0; i < (maxcol * maxrow); i++) {
			int col = currentLoad % maxcol;
			int row = currentLoad / maxcol;
			
			int x = col * frameWidth;
			int y = row * frameHeight;
			
			int sizeToScale = 48;
			if(frameWidth > sizeToScale || frameHeight > sizeToScale) add(sheet.getSubimage(x, y, frameWidth, frameHeight));	//Kissebre valamiért nem tud scalelni jól a BufferedReader, szétesinek a képek
			else add(uT.scaleImage(sheet.getSubimage(x, y, frameWidth, frameHeight), sizeToScale, sizeToScale));	//TODO átdolgozni
			
			currentLoad = (currentLoad + 1) % (maxcol * maxrow);
		}
	}
	// Visszaadj a sorben következő sheet elemet, a hívonak kell tudnia mi hol van a sheet-en
	public BufferedImage nextSheetImage() {
		BufferedImage tmp = this.get(currentFrame);
		currentFrame++;
		return tmp;
	}
	// Visszaadja a currentFrame változó értékét, ami annak a sheet elemnek az indexe ahol eppen tartunk
	public int currentFrame() {
		return currentFrame;
	}
}
