package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	
	Rectangle eventRect;	// solid area of the tile that has the event
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 20;
		eventRect.y = 20;
		eventRect.width = 8;
		eventRect.height = 8;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
		
		if (hit(26, 16, "right") == true) {
			damagePit(gp.dialogueState);
		}
		
		if (hit(23, 7, "up") == true) {
			healingPool(gp.dialogueState);
		}
		
		if (hit(40, 7, "any") == true) {
			teleportPortal(gp.dialogueState);
		}
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fell into a pit!";
		gp.player.life--;
	}
	
	public void healingPool(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You are drinking the water.\nYour life has been recovered!";
		gp.player.life = gp.player.maxLife;
	}
	
	public void teleportPortal(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize * 23;
		gp.player.worldY = gp.tileSize * 21;
	}
	
	public boolean hit(int eventCol, int eventRow, String requireDirection) {
		boolean hit = false;
		
		// PLAYER POSITION
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		// EVENT TILE POSITION
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;
		
		if (gp.player.solidArea.intersects(eventRect)) {
			// event occurs only when player has certain direction or any direction
			if (gp.player.direction.contentEquals(requireDirection) || requireDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		// RESET
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
}
