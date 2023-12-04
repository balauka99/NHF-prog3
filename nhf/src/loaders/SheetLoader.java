package loaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import error.OwnError;
import gameplay.UtilityTools;

/**
 * Az osztály egy spirite sheet-et képes feldarabolni és tárolni azok elemeit
 * FONTOS a hívonak kell tudni a spirite sheet adatait, maga az osztály nem képes felismerni hány oszlopa és sora van, meg hogy mekkorák azok
 */
@SuppressWarnings("serial")
public class SheetLoader extends ArrayList<BufferedImage> implements Serializable{
	/**
	 * Maga az eredeti fájl
	 */
	private BufferedImage sheet;
	private int maxcol, maxrow, frameWidth, frameHeight;
	/**
	 * Betölti a megadott filename fájlt egy BufferedImage-be és a megadott adatok szerint szétvágdossa és hozzáadja azokat a listához
	 * @param filename
	 * @param mcol Amennyi oszlop van a sheet-en
	 * @param mrow Amennyi sor van a sheet-en
	 * @param fHeight Egy a sheet-en lévő kép darabkája milyen magas
	 * @param fWidth Egy a sheet-en lévő kép darabkája milyen széles
	 * @throws OwnError, ami tartalmazza a hiba üzenetet
	 */
	public SheetLoader(String filename, int mcol, int mrow, int fHeight, int fWidth) throws OwnError{
		/**
		 * @throws IOException
		 * @throws IllegalArgumentException Ha rossz filenevet kap, ezt fogja visszadobni, mivel a ImageIO.read()-nek a paramétere null lesz
		 * @throws NullPointerException Ha nem egy kép van a fájlban, de a fájl létezik, ezt kapjuk
		 */
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream(filename));
		} catch (IOException e) {
			throw new OwnError("SheetLoader: IOException is found!");
		} catch (IllegalArgumentException e) {
			throw new OwnError("SheetLoader: Bad File name ->" + filename);
		} catch (NullPointerException e) {
			throw new OwnError("SheetLoader: Can't find Image in file ->" + filename);
		}
		maxcol = mcol;
		maxrow = mrow;
		frameHeight = fHeight;
		frameWidth = fWidth;
		makeArray();
	}
	/**
	 * A konstruktorban már betöltött adatokból megcsinálja a tömböt ami tartalmazza a kivágott képeket BufferedImage formában
	 */
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
			else add(uT.scaleImage(sheet.getSubimage(x, y, frameWidth, frameHeight), sizeToScale, sizeToScale));
			
			currentLoad = (currentLoad + 1) % (maxcol * maxrow);
		}
	}
}
