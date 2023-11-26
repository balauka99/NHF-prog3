package gameplay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import object.Heart;
import object.OsObject;
import tile.TileManager;

class Map implements Serializable{
	private TileManager tileMg;
	private ArrayList<OsObject> objects;
	private ArrayList<Entity> enemys;
	private AssetSetter assSetter;
	private int playerX, playerY;
	public boolean isMapInPrevMaps = true;
	public Map_Status map_state = Map_Status.IN_PREVIOUS;
	public Map(TileManager tileMg, ArrayList<OsObject> objects, ArrayList<Entity> enemys, AssetSetter assS, int playerX, int playerY, String move_dir, int tileSize) {
		this.tileMg = tileMg;
		this.objects = objects;
		this.setEnemys(enemys);
		this.assSetter = assS;
		this.playerX = playerX;
		this.playerY = playerY;
		switch(move_dir) {
		case "up":
			this.playerY += tileSize;
			break;
		case "left":
			this.playerX += tileSize;
			break;
		case "right":
			this.playerX -= tileSize;
			break;
		case "down":
			this.playerY -= tileSize;
			break;
		}
	}
	public TileManager getTileMg() {
		return tileMg;
	}
	public void setTileMg(TileManager tileMg) {
		this.tileMg = tileMg;
	}
	public ArrayList<OsObject> getObjects() {
		return objects;
	}
	public void setObjects(ArrayList<OsObject> objects) {
		this.objects = objects;
	}
	public int getPlayerLastX() {
		return playerX;
	}
	public void setPlayerLastX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerLastY() {
		return playerY;
	}
	public void setPlayerLastY(int playerY) {
		this.playerY = playerY;
	}
	public AssetSetter getAssSetter() {
		return assSetter;
	}
	public void setAssSetter(AssetSetter assSetter) {
		this.assSetter = assSetter;
	}
	public ArrayList<Entity> getEnemys() {
		return enemys;
	}
	public void setEnemys(ArrayList<Entity> enemys) {
		this.enemys = enemys;
	}
}

public class PreviousMaps extends ArrayList<Map> implements Serializable{
	private Player savedPlayer;
	private ArrayList<Heart> player_healt;
	private int currentPrev = 0;
	public void addPrevMap(TileManager tileMg, ArrayList<OsObject> objects, ArrayList<Entity> enemys, AssetSetter assS, int playerX, int playerY, String move_dir, int tileSize) {
		this.add(new Map(tileMg, objects, enemys, assS, playerX, playerY, move_dir, tileSize));
	}
	
	public void setupCurrentPrev() {
		currentPrev = this.size() - 1;
	}
	
	// Visszaadja az előzö Map adatait, de ha nincs akkor null pointert ad vissza
	public Map getPrevious() {
		if(this.size() == 0) return null;
		if(currentPrev != 0) currentPrev--;
		return this.get(currentPrev);
	}
	
	// Visszaadja a következő Map adatait, de ha nincs akkor null pointert ad vissza
	public Map getNextPrevMap() {
		if(this.size() < 1 || currentPrev == this.size() -1) return null;
		//if(++currentPrev == this.size()) this.get(currentPrev).map_state = Map_Status.IN_NEW;
		return this.get(++currentPrev);
	}
	
	public int getCurrentPrev() {
		return currentPrev;
	}
	
	public static void saveAllPreviousMaps(PreviousMaps prevMaps, String fName, Player gamer) {
        try (ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream(fName))) {
            saver.writeObject(prevMaps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static PreviousMaps loadALlPreviousMaps(String fName) {
        try (ObjectInputStream loader = new ObjectInputStream(new FileInputStream(fName))) {
            Object maps = loader.readObject();
            if (maps instanceof PreviousMaps) {
                return (PreviousMaps) maps;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

	public Player getSavedPlayer() {
		return savedPlayer;
	}

	public void setSavedPlayer(Player savedPlayer) {
		this.savedPlayer = savedPlayer;
	}

	public ArrayList<Heart> getPlayer_healt() {
		return player_healt;
	}

	public void setPlayer_healt(ArrayList<Heart> player_healt) {
		this.player_healt = player_healt;
	}
}
