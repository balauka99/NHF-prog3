package gameplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import entity.Entity;
import entity.Player;
import loaders.BestRounds;
import loaders.Score;
import object.Heart;
import object.OsObject;
import ospanel.DeadScreen;
import ospanel.OsPanel;
import tile.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends OsPanel implements Runnable{
	public Map_Status map_state = Map_Status.IN_NEW;
	private KeyHandler keyH = new KeyHandler();	// Gomb nyomásokat lekezeli
	private transient Thread mainT;	// A main szálla a játéknak
	
	private Player gamer;
	public ArrayList<Heart> player_healt = new ArrayList<Heart>();	//TODO mentsd el
	private transient String playerName;
	private transient BestRounds playersScoures;
	
	private PreviousMaps prevMaps = new PreviousMaps();
	public boolean isMapInPrevMaps = false;
	
	private int fps = 60;
	public TileManager tileMg;
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public ArrayList<OsObject> objects = new ArrayList<OsObject>();
	public ArrayList<Entity> enemys = new ArrayList<Entity>();
	public AssetSetter assSetter;
	
	public UserInterface ui = new UserInterface(this);
	
	private Random rndNum = new Random();
	
	private void genNewMap() {
		int mapNum = rndNum.nextInt(4) + 1;
		tileMg = new TileManager(this, "/maps/map" + mapNum + ".txt");
		assSetter = new AssetSetter(this, "/obj_positions/obj_pos" + mapNum + ".txt");
	}
	public GamePanel(String character, String playerName) {
		super();
		if(!playerName.equals("load_game")) {
			this.playerName = playerName;
			playersScoures = BestRounds.loadBests("C:\\\\OwnThings\\\\BME_mernokinfo\\\\3félév\\\\Prog3\\\\NagyHázi\\\\nhf\\\\source\\\\save_file\\\\bestRounds.txt");
			//playersScoures = new BestRounds();
			playersScoures.add(new Score(this.playerName, 0));
		}
		if(!character.equals("load_game")) gamer = new Player(this, keyH, character);
		this.addKeyListener(keyH);
		this.setFocusable(true);	// Fokuszál a key inputra
		this.requestFocusInWindow();
		this.setupHealtPos();
		this.genNewMap();
	}
	
	public void setupGame() {
		assSetter.setObject();
		assSetter.setEnemys();
		gamer.defaultPos();
	}
	
	public void setupHealtPos() {
		// Ballról jobbra a szívek
		for(int i = 0; i < 5; i++) {
			player_healt.add(new Heart());
			player_healt.get(i).y = 14*tileSize;
			player_healt.get(i).x = (15+i)*tileSize;
		}
	}
	
	public void startMainT() {
		mainT = new Thread(this);
		mainT.start();
	}
	
	public void resetObjects() {
		objects = new ArrayList<OsObject>();
	}
	
	public void resetEnemys() {
		enemys = new ArrayList<Entity>();
	}
	
	public void saveMap() {
		if(!isMapInPrevMaps) {
			prevMaps.addPrevMap(tileMg, objects, enemys, assSetter, gamer.getX(), gamer.getY(), gamer.move_dir, tileSize);
			prevMaps.setupCurrentPrev();
		}
	}
	
	public int getPlayerPoint() {
		return gamer.getPoint();
	}
	
	public void enterNewMap() {
		saveMap();
		this.saveGameData();
		this.genNewMap();
		objects = new ArrayList<OsObject>();
		enemys = new ArrayList<Entity>();
		isMapInPrevMaps = false;
		map_state = Map_Status.IN_NEW;
		setupGame();
		playersScoures.getCurrent().setPoint(gamer.getPoint());
	}
	
	private void getInformationFromMAP(Map tmp) {
		tileMg = tmp.getTileMg();
		objects = tmp.getObjects();
		enemys = tmp.getEnemys();
		assSetter = tmp.getAssSetter();
		isMapInPrevMaps = tmp.isMapInPrevMaps;
		map_state = tmp.map_state;
		this.reLoadALL();
		assSetter.closeAllDoor();
	}
	
	public void loadPreviousMap() {
		saveMap();
		Map tmp = prevMaps.getPrevious();
		if(tmp == null) return;
		this.getInformationFromMAP(tmp);
		gamer.setX(tmp.getPlayerLastX());
		gamer.setY(tmp.getPlayerLastY());
	}
	
	public void enterPreviousMapFromPreviousMap() {
		Map tmp = prevMaps.getNextPrevMap();
		if(tmp == null) {
			enterNewMap();
			return;
		}
		this.getInformationFromMAP(tmp);
		gamer.defaultPos();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;	// Ez egy másodperc nano sec-ben nézve és elosztva az fps-el, ennyi időnként fissítsuk a képernyöt
		double nextDrawTime = System.nanoTime() + drawInterval;	// nano sec-ben számolva mikor kell kövinenk frissíteni a képet
		long timer = 0;
		int drawCount = 0;
		
		// A game loop addig megy ameddig tart a main szállunk
		while(gamer.getHealt() > 0) {
			
			try {
				updatePanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			repaint();
			drawCount++;
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();	// maga a parancsok végrehajtása közben is eltelt idő, ha maradt azzal dolgozunk
				remainingTime /= 1000000;	// nano sec-et át kell váltani milli sec-re, mert a sleep-nek az kell
				if(remainingTime < 0) {
					remainingTime = 0;	// Ha gond lenne, lagolnánk, akkor ne essen minuszba a sleep time
				}
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
				// Minden másodpercben kiírjuk, hányszor volt meghívva a repaint
	            if (System.nanoTime() - timer >= 1000000000) {
	            	System.out.println("FPS: " + drawCount);
	                drawCount = 0;
	                timer = System.nanoTime();
	            }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updatePanel() throws IOException {
		gamer.update();
		if(gamer.getHealt() == 0) {
			DeadScreen deadS = new DeadScreen(playerName, gamer.getPoint());
			deadS.setMenuWin(menuWin);
			menuWin.addNewCard(deadS, "DeadScreen");
			menuWin.changeCard("DeadScreen");
		}
	}
	
	public boolean isLevelCleared() {
		for(int i = 0; i < enemys.size(); i++) {
			if(!enemys.get(i).isDead()) return false;
		}
		return true;
	}
	public KeyListener getKeyListener() {
		return keyH;
	}
	
	public void paintComponent(Graphics grap) {
		//Itt kell rajzolni minden fel
		super.paintComponent(grap);
		Graphics2D grap2 = (Graphics2D) grap;
		
		//Tileok, legalsó elemek
		tileMg.draw(grap2);	// Előbb ezt kell meghívni, hogy a tileok legyenek hátrébb a layerben
		
		//Objectek
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(grap2, this);
		}
		
		//Entity draw
		for(int i = 0; i < enemys.size(); i++) {
			enemys.get(i).draw(grap2);
		}
		
		//Játékos, neki kell lennie legfent
		for(int i = 0; i < player_healt.size(); i++) {
			player_healt.get(i).draw(grap2, this);
		}
		gamer.draw(grap2);
		
		ui.draw(grap2);
		
		grap2.dispose();	// Megsporolunk egy kis memóriát
	}
	
	private void saveGameData() {
		prevMaps.setSavedPlayer(gamer);
		prevMaps.setPlayer_healt(player_healt);
		PreviousMaps.saveAllPreviousMaps(prevMaps, "C:\\OwnThings\\BME_mernokinfo\\3félév\\Prog3\\NagyHázi\\nhf\\source\\save_file\\last_save.txt", this.gamer);
		playersScoures.getCurrent().setPoint(gamer.getPoint());
		BestRounds.saveBests(playersScoures, "C:\\OwnThings\\BME_mernokinfo\\3félév\\Prog3\\NagyHázi\\nhf\\source\\save_file\\bestRounds.txt");
	}
	
	private void reLoadALL() {
		tileMg.reLoadTileSkins(this);
		assSetter.reLoad(this);
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).reLoad();
		}
		for(int i = 0; i < enemys.size(); i++) {
			enemys.get(i).reLoad();
		}
		this.gamer.reLoad(this, this.keyH);
	}
	public void loadGameData() {
		this.prevMaps = PreviousMaps.loadALlPreviousMaps("C:\\OwnThings\\BME_mernokinfo\\3félév\\Prog3\\NagyHázi\\nhf\\source\\save_file\\last_save.txt");
		this.playersScoures = BestRounds.loadBests("C:\\OwnThings\\BME_mernokinfo\\3félév\\Prog3\\NagyHázi\\nhf\\source\\save_file\\bestRounds.txt");
		this.playerName = playersScoures.getCurrent().getName();
		Map tmp = prevMaps.get(prevMaps.size()-1);
		this.gamer = prevMaps.getSavedPlayer();
		this.player_healt = prevMaps.getPlayer_healt();
		for(int i = 0; i < player_healt.size(); i++) {
			player_healt.get(i).reLoad();
		}
		this.getInformationFromMAP(tmp);
	}
}
