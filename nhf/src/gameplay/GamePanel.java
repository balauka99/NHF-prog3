package gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;
import object.OsObject;
import ospanel.OsPanel;
import tile.TileManager;

public class GamePanel extends OsPanel implements Runnable{
	
	KeyHandler keyH = new KeyHandler();	// Gomb nyomásokat lekezeli
	Thread mainT;	// A main szálla a játéknak
	Player gamer = new Player(this, keyH);
	
	private int fps = 60;
	public TileManager tileMg = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public OsObject objects[] = new OsObject[10];	//TODO átalakítani
	public AssetSetter assSetter = new AssetSetter(this);
	
	public GamePanel() {
		super();
		this.addKeyListener(keyH);
		this.setFocusable(true);	// Fokuszál a key inputra
	}
	
	public void setupGame() {
		assSetter.setObject();
	}
	
	public void startMainT() {
		mainT = new Thread(this);
		mainT.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;	// Ez egy másodperc nano sec-ben nézve és elosztva az fps-el, ennyi időnként fissítsuk a képernyöt
		double nextDrawTime = System.nanoTime() + drawInterval;	// nano sec-ben számolva mikor kell kövinenk frissíteni a képet
		
		// A game loop addig megy ameddig tart a main szállunk
		while(mainT != null) {
			
			updatePanel();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();	// maga a parancsok végrehajtása közben is eltelt idő, ha maradt azzal dolgozunk
				remainingTime /= 1000000;	// nano sec-et át kell váltani milli sec-re, mert a sleep-nek az kell
				if(remainingTime < 0) {
					remainingTime = 0;	// Ha gond lenne, lagolnánk, akkor ne essen minuszba a sleep time
				}
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updatePanel() {
		gamer.update();
	}
	
	public void paintComponent(Graphics grap) {
		super.paintComponent(grap);
		Graphics2D grap2 = (Graphics2D) grap;
		
		//Tileok, legalsó elemek
		tileMg.draw(grap2);	// Előbb ezt kell meghívni, hogy a tilok legyenek hátrébb a layerekben
		
		//Objectek
		for(int i = 0; i < objects.length; i++) {
			if(objects[i] != null) {
				objects[i].draw(grap2, this);
			}
		}
		
		//Játékos, neki kell lennie legfent
		gamer.draw(grap2);
		
		grap2.dispose();	// Megsporolunk egy kis memóriát
	}
}
