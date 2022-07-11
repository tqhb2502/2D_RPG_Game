package main;

public class EventHandler {
	GamePanel gp;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkEvent() {
	
		// map 1
		if (gp.currentMap == gp.map1) {
			
			if (hitEvent(2, 49)) { teleportMap2(11, 1); }
			if (hitEvent(47, 49)) { teleportMap3(34, 1); }
			if (hitEvent(47, 0)) { endGame(); }
		}
		
		// map 2
		else if (gp.currentMap == gp.map2) {
			
			if (hitEvent(11, 0)) { teleportMap1(2, 48); }
		}
		
		// map 3
		else if (gp.currentMap == gp.map3) {
			
			if (hitEvent(34, 0)) { teleportMap1(47, 48); }
		}
	}
	
	public void teleportMap1(int col, int row) {
		gp.currentMap = gp.map1;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
		gp.aSetter.setMonster();
	}
	
	public void teleportMap2(int col, int row) {
		gp.currentMap = gp.map2;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
		gp.aSetter.setMonster();
	}
	
	public void teleportMap3(int col, int row) {
		gp.currentMap = gp.map3;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
		gp.aSetter.setMonster();
	}
	
	public void endGame() {
		gp.stopMusic();
		gp.playSE(4);
		gp.gameState = gp.finishedState;
	}
	
	// check if player hit event tile
	public boolean hitEvent(int col, int row) {
		boolean hit = false;
		
		// PLAYER POSITION
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		// EVENT TILE POSITION
		gp.currentMap.eventRect[col][row].x = col * gp.tileSize + gp.currentMap.eventRect[col][row].x;
		gp.currentMap.eventRect[col][row].y = row * gp.tileSize + gp.currentMap.eventRect[col][row].y;
		
		if (gp.player.solidArea.intersects(gp.currentMap.eventRect[col][row]) == true 
			&& gp.currentMap.eventRect[col][row].eventDone == false) {
			
			hit = true;
		}
		
		// RESET
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		gp.currentMap.eventRect[col][row].x = gp.currentMap.eventRect[col][row].eventRectDefaultX;
		gp.currentMap.eventRect[col][row].y = gp.currentMap.eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
}
