package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import gameplay.GamePanel;

public class TileManager {
	private GamePanel gameP;
	public Tile[] tiles;
	public int map[][];	// 2 Dimenziós tömbben tároljuk el a fájlból betöltött mapot
	
	public TileManager(GamePanel gp) {
		gameP = gp;
		//TODO átalakítani
		tiles = new Tile[10];	//Ennyi tileunk lehet max, flore, lava, wall stb...
		map = new int[gameP.maxOszlop][gameP.maxSor];	// Létrehozzuk a mátrixot, és akkorára álítjuk amekkora a game panel tile felosztás
		setTilesImage();
		loadMap("/maps/map1.txt");
	}
	public void loadMap(String fileP) {
		try {
			InputStream inS = getClass().getResourceAsStream(fileP);
			BufferedReader bufR = new BufferedReader(new InputStreamReader(inS));
			
			int col = 0;
			int row = 0;
			while(col < gameP.maxOszlop && row < gameP.maxSor) {
				String line = bufR.readLine();
				while(col < gameP.maxOszlop) {
					String inds[] = line.split(" ");
					int ind = Integer.parseInt(inds[col]);
					map[col][row] = ind;
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
	}
	public void setTilesImage() {
		try {
			tiles[0] = new Tile();
			tiles[0].setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/flore.png")));
			
			tiles[1] = new Tile();
			tiles[1].setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
			tiles[1].enableCollision();
			
			tiles[2] = new Tile();
			tiles[2].setSkin(ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png")));
			
			tiles[3] = new Tile();
			tiles[3].setSkin(ImageIO.read(getClass().getResourceAsStream("/objects/door.png")));
			tiles[3].enableCollision();
			
			tiles[4] = new Tile();
			tiles[4].setSkin(ImageIO.read(getClass().getResourceAsStream("/objects/doorLeft.png")));
			tiles[4].enableCollision();
			
			tiles[5] = new Tile();
			tiles[5].setSkin(ImageIO.read(getClass().getResourceAsStream("/objects/doorRight.png")));
			tiles[5].enableCollision();
			
			tiles[6] = new Tile();
			tiles[6].setSkin(ImageIO.read(getClass().getResourceAsStream("/objects/chest.png")));
			tiles[6].enableCollision();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D grap2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col <gameP.maxOszlop && row < gameP.maxSor) {
			int tileInd = map[col][row];
			grap2.drawImage(tiles[tileInd].getSkin(), x, y, gameP.tileSize, gameP.tileSize, null);
			col++;
			x += gameP.tileSize;
			
			if(col == gameP.maxOszlop) {
				col = 0;
				x = 0;
				row++;
				y += gameP.tileSize;
			}
		}
	}
}
