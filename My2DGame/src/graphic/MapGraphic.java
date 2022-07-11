package graphic;

import java.awt.Color;
import java.awt.Graphics2D;

import map.Map;

public class MapGraphic {

	Map map;
	public boolean drawPath = false;
	
	public MapGraphic(Map map) {
		this.map = map;
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldRow < map.gp.maxWorldRow) {
			int tileNum = map.data[worldCol][worldRow];
			
			// get the x and y of the tile
			int worldX = worldCol * map.gp.tileSize;
			int worldY = worldRow * map.gp.tileSize;
			
			// only draw tiles within screen's range around player
			if (worldX > map.gp.player.worldX - map.gp.player.entityGraphic.screenX - map.gp.tileSize
				&& worldX < map.gp.player.worldX + map.gp.player.entityGraphic.screenX + map.gp.tileSize
				&& worldY > map.gp.player.worldY - map.gp.player.entityGraphic.screenY - map.gp.tileSize
				&& worldY < map.gp.player.worldY + map.gp.player.entityGraphic.screenY + map.gp.tileSize) {
				
				int screenX = worldX - map.gp.player.worldX + map.gp.player.entityGraphic.screenX;
				int screenY = worldY - map.gp.player.worldY + map.gp.player.entityGraphic.screenY;
				
				g2.drawImage(map.tile[tileNum].image, screenX, screenY, map.gp.tileSize, map.gp.tileSize, null);
			}
			
			worldCol++;
			
			if (worldCol == map.gp.maxWorldCol) {
				worldCol = 0;
				
				worldRow++;
			}
		}
		if(drawPath == true) {
			g2.setColor(new Color(255,0,0,70));
			for(int i = 0; i < map.gp.pFinder.pathList.size(); i++ ) {
				int worldX = map.gp.pFinder.pathList.get(i).col * map.gp.tileSize;
				int worldY = map.gp.pFinder.pathList.get(i).row * map.gp.tileSize;
				int screenX = worldX - map.gp.player.worldX + map.gp.player.entityGraphic.screenX;
				int screenY = worldY - map.gp.player.worldY + map.gp.player.entityGraphic.screenY;
				g2.fillRect(screenX, screenY, map.gp.tileSize, map.gp.tileSize	);
			}
		}
	}
		
}
