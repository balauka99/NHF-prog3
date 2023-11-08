package ospanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class OsPanel extends JPanel{
	// Megjenelítés segítő argumentek
	// Kisebb kockákra bontom fel a képernyőt, amik a Tile-ok
	final int orgTileSize = 16;	//16x16, ennyi egy kis kocka eredeti felbontása
	final int scale = 3;
	protected final int tileSize = orgTileSize * scale; //48x48, egy kis kocka az ablakban ekkora
	final int maxOszlop = 20;
	final int maxSor = 15;
	final int screenWidth = tileSize * maxOszlop;	//960
	final int screenHeight = tileSize * maxSor;		//720
	
	public OsPanel() {
		//super(new GridBagLayout());	//Ha kéne így meglehet oldani
		setPreferredSize(new Dimension(960, 720));
		setBackground(Color.black);
		setDoubleBuffered(true);
	}
}
