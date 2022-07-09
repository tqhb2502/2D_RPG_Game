package map;

import java.io.IOException;

import javax.imageio.ImageIO;

import graphic.MapGraphic;
import main.GamePanel;

public class Map {
	
	public GamePanel gp;
	public MapGraphic mapGraphic;
	public MapLoader mapLoader;
	public Tile tile[];
	
	public int data[][];
	
	public Map(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[7];
		data = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/portal.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMapGraphic(MapGraphic mapGraphic) {
		this.mapGraphic = mapGraphic;
	}
	
	public void setMapLoader(MapLoader mapLoader) {
		this.mapLoader = mapLoader;
	}
}
