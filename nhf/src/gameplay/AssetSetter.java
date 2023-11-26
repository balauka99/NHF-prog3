package gameplay;

import object.OBJ_Barrel;
import object.OBJ_Box;
import object.OBJ_Chest;
import object.OBJ_Door;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Random;

import entity.EyeBall;

public class AssetSetter implements Serializable{
	private transient GamePanel gameP;
	private Random rndNum;
	private String obj_file;
	private boolean obj_map[][];
	private boolean enemys_canbePlaced[][];
	private int canPlace = 0;
	
	public AssetSetter(GamePanel gp, String file) {
		obj_file = file;
		gameP = gp;
		rndNum = new Random();
		obj_map = new boolean[gameP.maxOszlop][gameP.maxSor];
		enemys_canbePlaced = new boolean[gameP.maxOszlop][gameP.maxSor];
		loadObjMap();
	}
	
	// Beállítja az összes ajtót, fix poziciókba, az alsó ajtót kinyitja, aminugye bement a gamer
	private int setDoorsInMap(int ind) {
		if(obj_map[9][0]) {
			gameP.objects.add(new OBJ_Door());
			gameP.objects.get(ind).x = 9*gameP.tileSize;
			gameP.objects.get(ind++).y = 0*gameP.tileSize;
		}
		if(obj_map[9][14]) {
		gameP.objects.add(new OBJ_Door());
		gameP.objects.get(ind).x = 9*gameP.tileSize;
		gameP.objects.get(ind++).y = 14*gameP.tileSize;
		}
		if(obj_map[0][7]) {
		gameP.objects.add(new OBJ_Door());
		gameP.objects.get(ind).x = 0*gameP.tileSize;
		gameP.objects.get(ind++).y = 7*gameP.tileSize;
		}
		if(obj_map[19][7]) {
		gameP.objects.add(new OBJ_Door());
		gameP.objects.get(ind).x = 19*gameP.tileSize;
		gameP.objects.get(ind++).y = 7*gameP.tileSize;
		}
		return ind;
	}
	
	public void closeAllDoor() {
		int ind = 0;
		if(obj_map[9][0]) {
			gameP.objects.get(ind++).reUseObject();
		}
		if(obj_map[9][14]) {
			gameP.objects.get(ind++).reUseObject();
		}
		if(obj_map[0][7]) {
			gameP.objects.get(ind++).reUseObject();
		}
		if(obj_map[19][7]) {
			gameP.objects.get(ind).reUseObject();
		}
	}
	
	// Az összes objectet elhelyezi
	public void setObject() {	//TODO átgondolni
		int ind = 0;
		int spawnFrom = 10;
		boolean chestSpawned = false;
		ind = setDoorsInMap(ind);
		for(int y = 0; y < gameP.maxSor; y++) {
			for(int x = 0; x < gameP.maxOszlop; x++) {
				if((x == 9 && y == 0) || (x == 9 && y == 14) || (x == 0 && y == 7) || (x == 19 && y == 7)) continue;	//Ajtóknak a helyeire ne spawnolodjon semmi
				if(obj_map[x][y]) {
					int whatToSpawn = rndNum.nextInt(spawnFrom);
					switch(whatToSpawn) {
					case 1:
						gameP.objects.add(new OBJ_Box());
						gameP.objects.get(ind).x = x*gameP.tileSize;
						gameP.objects.get(ind++).y = y*gameP.tileSize;
						break;
					case 5:
						gameP.objects.add(new OBJ_Barrel());
						gameP.objects.get(ind).x = x*gameP.tileSize;
						gameP.objects.get(ind++).y = y*gameP.tileSize;
						if(chestSpawned) spawnFrom--;
						break;
					case 9:
						if(!chestSpawned) {
							gameP.objects.add(new OBJ_Chest());
							gameP.objects.get(ind).x = x*gameP.tileSize;
							gameP.objects.get(ind++).y = y*gameP.tileSize;
							chestSpawned = true;
							spawnFrom--;
						}
						break;
					default:
						enemys_canbePlaced[x][y] = true;
						break;
					}
				}
			}
		}
	}
	
	public void setEnemys() {
		int ind = 0;
		int enemyCount = this.canPlace/10;
		int enemik_kozotti_tavolsag = 4;
		for(int y = 0; y < gameP.maxSor; y++) {
			for(int x = 0; x < gameP.maxOszlop; x++) {
				if(enemys_canbePlaced[x][y]) {
					if(enemik_kozotti_tavolsag == 0 && enemyCount > 0) {
						gameP.enemys.add(new EyeBall());
						gameP.enemys.get(ind).setX(x*gameP.tileSize);
						gameP.enemys.get(ind++).setY(y*gameP.tileSize);
						enemik_kozotti_tavolsag = 4;
						enemyCount--;
					}
					else {
						enemik_kozotti_tavolsag--;
					}
				}
			}
		}
	}
	
	public void resetMap() {
		canPlace = 0;
		gameP.resetObjects();
		setObject();
		setEnemys();
	}
	
	public int getCanPlaced() {
		return canPlace;
	}
	
	private void loadObjMap() {
		try {
			InputStream inS = getClass().getResourceAsStream(obj_file);
			BufferedReader bufR = new BufferedReader(new InputStreamReader(inS));
			
			int col = 0;
			int row = 0;
			while(col < gameP.maxOszlop && row < gameP.maxSor) {
				String line = bufR.readLine();
				while(col < gameP.maxOszlop) {
					String inds[] = line.split(" ");
					boolean ind = Boolean.parseBoolean(inds[col]);
					if(ind) canPlace++;
					obj_map[col][row] = ind;
					col++;
				}
				if(col == gameP.maxOszlop) {
					col = 0;
					row++;
				}
			}
			bufR.close();
		}catch(Exception e) {
			
		}
		enemys_canbePlaced = new boolean[gameP.maxOszlop][gameP.maxSor];
	}
	
	public void reLoad(GamePanel gameP) {
		this.gameP = gameP;
	}
}