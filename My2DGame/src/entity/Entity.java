package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {	// parent class for every entity in the game
	GamePanel gp;
	
	public int worldX, worldY;	// position of entity in world map
	public int speed;
	public String direction = "none";	// the direction of entity
	public String name;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;	// used to store entity's image
	public BufferedImage image, image2, image3;		// store image to be displayed
	
	public int spriteNum = 1;	// the index of current displayed image
	public int spriteCounter = 0;	// the number of frames that the entity moves
	public int standCounter = 0;	// the number of frames that the entity stands still
	public int actionLockCounter = 0;	// entity can not do a specific action until counter counts to certain number
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);	// the solid area of entity, if it hit a tile that is not passable, entity can not move
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;	// store default value of solidArea, because we may change the x and y of solidArea later
	public boolean collisionOn = false;		// check if entity is in collision or not
	public boolean collision = false;	// this entity can be pass through or not
    
	// entity status
	public int maxLife;
	public int life;
	
	// CONSTRUCTOR
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		// to be overridden
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		
		// TILE COLLISION
		gp.cChecker.checkTile(this);
		
		// OBJECT COLLISION
		gp.cChecker.checkObject(this, false);
		
		// PLAYER COLLISION
		gp.cChecker.checkPlayer(this);
		
		// if collisionOn is false, player can move
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;	// update position
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		
		// SWITCH SPRITE
		spriteCounter++;	// everytime a frame passed, increase the counter
		
		if (spriteCounter > 10) {
			if (spriteNum == 1) {	// change the image
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}
			
			spriteCounter = 0;		// reset counter
		}
	}
	
	public void draw(Graphics2D g2) {
		// only draw entity within screen's range around player
		if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize
				&& worldX < gp.player.worldX + gp.player.screenX + gp.tileSize
				&& worldY > gp.player.worldY - gp.player.screenY - gp.tileSize
				&& worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
				
			switch(direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				break;
			}
			
			// position on screen where we draw entity
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
