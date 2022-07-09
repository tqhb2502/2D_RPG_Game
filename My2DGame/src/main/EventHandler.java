package main;

public class EventHandler {
	GamePanel gp;
	
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;	// the x, y coordinates of the player when he hits a event tile
	boolean canTouchEvent = true;	// determine event can be triggered or not
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		for (int col = 0; col < gp.maxWorldCol; col++) {
			
			for (int row = 0; row < gp.maxWorldRow; row++) {
				
				eventRect[col][row] = new EventRect();
				eventRect[col][row].x = 20;
				eventRect[col][row].y = 20;
				eventRect[col][row].width = 8;
				eventRect[col][row].height = 8;
				eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
				eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			}
		}
	}
	
	public void checkEvent() {
		// if player is 1-tile-size away from the previous event tile,
		// this event and other events can be triggered
		if (canTouchEvent == false) {
			
			int xDistance = Math.abs(previousEventX - gp.player.worldX);
			int yDistance = Math.abs(previousEventY - gp.player.worldY);
			int distance = Math.max(xDistance, yDistance);
			if (distance > gp.tileSize) {
				canTouchEvent = true;
			}
		}
		
		if (canTouchEvent == true) {
		
			if (hit(26, 16, "right") == true) { damagePit(26, 16, gp.dialogueState); }
			if (hit(23, 7, "up") == true) { healingPool(23, 7, gp.dialogueState); }
			if (hit(40, 7, "any") == true) { teleportPortal(40, 7, gp.dialogueState); }
		}
	}
	
	public void damagePit(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fell into a pit!";
		gp.player.life--;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You are drinking the water.\nYour life has been recovered!";
		gp.player.life = gp.player.maxLife;
		canTouchEvent = false;
	}
	
	public void teleportPortal(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize * 23;
		gp.player.worldY = gp.tileSize * 21;
	}
	
	// check if player hit event tile
	public boolean hit(int col, int row, String requireDirection) {
		boolean hit = false;
		
		// PLAYER POSITION
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		// EVENT TILE POSITION
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		
		if (gp.player.solidArea.intersects(eventRect[col][row]) == true
				&& eventRect[col][row].eventDone == false) {
			
			// event occurs only when player has certain direction or any direction
			if (gp.player.direction.contentEquals(requireDirection) || requireDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		// RESET
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
}
