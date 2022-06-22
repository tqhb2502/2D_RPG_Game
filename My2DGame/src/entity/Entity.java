package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {	// parent class for every entity in the game
	public GamePanel gp;
	
	public int worldX, worldY;	// position of entity in world map
	public String name;
	public int type;	// 0 = player, 1 = object, 2 = NPC, 3 = monster
	public int speed;
	public String direction = "none";	// the direction of entity
	public boolean attacking = false;
	
	public int actionLockCounter = 0;	// entity can not do a specific action until counter counts to certain number
	
	public boolean invincible = false;	// can receive damage from monsters or not
	public int invincibleCounter = 0;	// amount of invincible time
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);	// the solid area of entity, if it hit a tile that is not passable, entity can not move
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;	// store default value of solidArea, because we may change the x and y of solidArea later
	public boolean collisionOn = false;		// check if entity is in collision or not
	public boolean collision = false;	// this entity can be pass through or not
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);	// damage area when attacking
    
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
		// DIRECTION
		setAction();
		
		// COLLISION
		collisionOn = false;
		// tile
		gp.cChecker.checkTile(this);
		// object
		gp.cChecker.checkObject(this);
		// npc
		gp.cChecker.checkEntity(this, gp.npc);
		// monster
		gp.cChecker.checkEntity(this, gp.monster);
		// player
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if (this.type == 3 && contactPlayer == true) {
			
			if (gp.player.invincible == false) {
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
		// MOVEMENT
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
		
		// INVINCIBLE TIME
		if (invincible == true) {
			
			invincibleCounter++;
			
			if (invincibleCounter == 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
}
