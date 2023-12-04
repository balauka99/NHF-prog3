package ospanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import menu.MainMenu;

/**
 * Az ős panel ami tartalmazza az alap információkat, mint például a tileok nagysága, és eltárolja azt az ablakot ahol meg van jelenítve
 */
@SuppressWarnings("serial")
public class OsPanel extends JPanel{
	/**
	 * Megjenelítés segítő argumentek
	 * Kisebb kockákra bontom fel a képernyőt, amik a Tile-ok
	 */
	final int orgTileSize = 16;	//16x16, ennyi egy kis kocka eredeti felbontása
	final int scale = 3;
	public final int tileSize = orgTileSize * scale; //48x48, egy kis kocka az ablakban ekkora
	public final int maxOszlop = 20;
	public final int maxSor = 15;
	public final int screenWidth = tileSize * maxOszlop;	//960
	public final int screenHeight = tileSize * maxSor;		//720
	protected MainMenu menuWin;
	protected Font defFont = new Font("Century Gothic", Font.BOLD, 24);
	/**
	 * Az Ős Panel beállítja az alapértelmezett adatokat
	 */
	public OsPanel() {
		super();
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		setDoubleBuffered(true);
	}
	/**
	 * Van hogy szükséges az az ablak ahol éppen van a Panel
	 * @param menuWin MainMenu amiben éppen van
	 */
	public void setMenuWin(MainMenu menuWin) {
		this.menuWin = menuWin;
	}
}
