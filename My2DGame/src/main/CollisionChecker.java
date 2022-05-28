package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	// ENTITY TO TILE
	public void checkTile(Entity entity) {
		
		// find out the position of entity's solid area
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		// find out the row and column the solid area in
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1 = 0, tileNum2 = 0;		// store index of up to 2 tiles that entity can collide
		
		// simulate entity's movement, find out where it is after the next move has been done
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			break;
		}
		
		// if either tile is unpassable, turn collision to ON.
		if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			entity.collisionOn = true;
		}
	}
	
	// ENTITY TO OBJECT
	public int checkObject(Entity entity, boolean player) {
		int index = 999;	// index of the object that entity collided
		
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				// get entity solid area's position in world map
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// get object solid area's position in world map
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				// simulate entity's movement, find out next position
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				// see if there is collision
				if (entity.solidArea.intersects(gp.obj[i].solidArea)) {		// check if 2 solid areas collide or not
					if (gp.obj[i].collision == true) {		// check object collision
						entity.collisionOn = true;
					}
					
					if (player == true) {	// check if it is player, for pick up feature,...
						index = i;
					}
				}
				
				// reset solid area's x and y back to default value
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	// PLAYER TO OTHER ENTITIES
	public int checkEntity(Entity player, Entity[] target) {
		int index = 999;	// index of the entity that player collided
		
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				// get player solid area's position in world map
				player.solidArea.x = player.worldX + player.solidArea.x;
				player.solidArea.y = player.worldY + player.solidArea.y;
				
				// get entity solid area's position in world map
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				// simulate player's movement, find out next position
				switch (player.direction) {
				case "up":
					player.solidArea.y -= player.speed;
					break;
				case "down":
					player.solidArea.y += player.speed;
					break;
				case "left":
					player.solidArea.x -= player.speed;
					break;
				case "right":
					player.solidArea.x += player.speed;
					break;
				}
				
				// see if there is collision
				if (player.solidArea.intersects(target[i].solidArea)) {		// check if 2 solid areas collide or not
					player.collisionOn = true;
					index = i;
				}
				
				// reset solid area's x and y back to default value
				player.solidArea.x = player.solidAreaDefaultX;
				player.solidArea.y = player.solidAreaDefaultY;
				
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	// OTHER ENTITIES TO PLAYER
	public void checkPlayer(Entity entity) {
		// get entity solid area's position in world map
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		// get player solid area's position in world map
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		// simulate entity's movement, find out next position
		switch (entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		
		// see if there is collision
		if (entity.solidArea.intersects(gp.player.solidArea)) {		// check if 2 solid areas collide or not
			entity.collisionOn = true;
		}
		
		// reset solid area's x and y back to default value
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
}
