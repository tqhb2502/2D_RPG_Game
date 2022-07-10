package player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import graphic.EntityGraphic;
import main.GamePanel;
import main.KeyHandler;
import projectile.Fireball;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		// INFO
		worldX = gp.tileSize * 22;
		worldY = gp.tileSize * 18;
		speed = 6;
		direction = "down";
		maxHP = 6;
		currentHP = maxHP; // 2 life = 1 heart
		
		// SOLID AREA
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		// PROJECTILE
		projectile = new Fireball(gp);
		projectile.setEntityGraphic(new EntityGraphic(projectile));
		
		// ATTACK AREA
		attackArea.width = 36;
		attackArea.height = 36;
	}
	
	public void setImage() {
		
		try {
			// WALKING
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			this.entityGraphic.left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			this.entityGraphic.right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			this.entityGraphic.right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			// ATTACKING
			this.entityGraphic.attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_1.png"));
			this.entityGraphic.attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_2.png"));
			this.entityGraphic.attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_1.png"));
			this.entityGraphic.attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_2.png"));
			this.entityGraphic.attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_1.png"));
			this.entityGraphic.attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_2.png"));
			this.entityGraphic.attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_1.png"));
			this.entityGraphic.attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		// CHECK HP
		
		
		// INTERACT KEYS
		if (keyH.enterPressed == true) {
			
			// INTERACT WITH NPC
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			boolean interactWithNPC = interactNPC(npcIndex);
			
			// ATTACKING
			if (interactWithNPC == false) {
				attacking = true;
			}
		}
		
		// ATTACKING
		if (attacking == true) {
			attacking();
		}
		
		// MOVEMENT KEYS
		else if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			// DIRECTION
			if (keyH.upPressed == true) {
				direction = "up";	// update direction
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			// COLLISION
			collisionOn = false;	// reset collision status
			// tile
			gp.cChecker.checkTile(this);
			// object
			int objectIndex = gp.cChecker.checkObject(this);
			pickUpObject(objectIndex);
			// npc
			gp.cChecker.checkEntity(this, gp.npc);
			// monster
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			interactMonster(monsterIndex);
			
			// EVENT
			gp.eHandler.checkEvent();
			
			// MOVEMENT
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
			this.entityGraphic.spriteCounter++;
			if (this.entityGraphic.spriteCounter > 10) {
				if (this.entityGraphic.spriteNum == 1) {	// change the image
					this.entityGraphic.spriteNum = 2;
				} else {
					this.entityGraphic.spriteNum = 1;
				}
				
				this.entityGraphic.spriteCounter = 0;		// reset counter
			}
		} else {
			// STAND SPRITE
			// switch to stand still animation when no key is pressed
			this.entityGraphic.standCounter++;
			
			if (this.entityGraphic.standCounter == 20) {
				this.entityGraphic.spriteNum = 1;
				this.entityGraphic.standCounter = 0;
			}
		}
		
		// PROJECTILE
		if (gp.keyH.shotPressed == true && projectile.alive == false && shotAvailableCounter == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			gp.playSE(5);
			shotAvailableCounter = 0;
		}
		
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
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
	
	public void attacking() {
		
		this.entityGraphic.spriteCounter++;
		
		if (this.entityGraphic.spriteCounter <= 5) {
			this.entityGraphic.spriteNum = 1;
		}
		
		if (5 < this.entityGraphic.spriteCounter && this.entityGraphic.spriteCounter <= 25) {
			this.entityGraphic.spriteNum = 2;
			
			// save player's worldX, worldY and solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// adjust player's worldX, worldY to represent attackArea's
			switch (direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			// adjust player's solidArea to represent attackArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// check collision between player's attackArea and monster's solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			
			damageMonster(monsterIndex, 1);
			
			// restore player's worldX, worldY and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if (25 < this.entityGraphic.spriteCounter) {
			this.entityGraphic.spriteNum = 1;
			this.entityGraphic.spriteCounter = 0;
			attacking = false;
		}
	}
	
	// pick up object
	public void pickUpObject(int index) {
		
		if (index != 999) {
			
		}
	}
	
	// interact with NPC
	public boolean interactNPC(int index) {
		
		if (index != 999) {
			
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				// npc speaks
				gp.ui.currentDialogue = "Hello!";
			}
			
			return true;
		} else {
			
			return false;
		}
	}
	
	// interact with monster
	public void interactMonster(int index) {
		
		if (index != 999) {
			
			damagePlayer(gp.monster[index].normalAttack);
		}
	}
	
	// give damage to monster
	public void damageMonster(int index, int attack) {
		
		if (index != 999) {
			
			if (gp.monster[index].invincible == false) {
				
				gp.monster[index].currentHP -= attack;
				gp.monster[index].invincible = true;
				
				if (gp.monster[index].currentHP <= 0) {
					gp.monster[index] = null;
				}
			}
		}
	}
}
