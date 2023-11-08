package gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ospanel.OsPanel;

public class GamePanel extends OsPanel implements Runnable{
	
	KeyHandler keyH = new KeyHandler();	// Gomb nyomásokat lekezeli
	Thread mainT;	// A main szálla a játéknak
	
	// Játékos alap beállása
	private int playerX = 100;
	private int playerY = 100;
	private int playerSp = 4;
	
	private int fps = 60;
	
	public GamePanel() {
		super();
		this.addKeyListener(keyH);
		this.setFocusable(true);	// Fokuszál a key inputra
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
		//System.out.println("Olvasok");
		if(keyH.wP == true) {
			playerY -= playerSp;
		}
		else if(keyH.sP == true) {
			playerY += playerSp;
		}
		else if(keyH.aP == true) {
			playerX -= playerSp;
		}
		else if(keyH.dP == true) {
			playerX += playerSp;
		}
	}
	
	public void paintComponent(Graphics grap) {
		//System.out.println("Game loop running");
		
		super.paintComponent(grap);
		
		Graphics2D grap2 = (Graphics2D) grap;
		
		grap2.setColor(Color.white);
		
		grap2.fillRect(playerX, playerY, tileSize, tileSize);
		
		grap2.dispose();	// Megsporolunk egy kis memóriát
	}
}
