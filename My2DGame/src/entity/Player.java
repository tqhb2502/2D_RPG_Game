package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		// INFO
		setDefaultValue();
		// lấy vị trí chính giữa của cửa sổ game, nhưng vị trí này được dùng làm vị trí trái trên
		// nên khi vẽ player, nên phải trừ đi 1 nửa tile size
		// để player được vẽ ở vị trí chính giữa của cửa sổ game
		screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);	
		screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);
		
		// SOLID AREA
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		// ATTACK AREA
		attackArea.width = 36;
		attackArea.height = 36;
		
		// IMAGE
		getImage();
	}
	
	public void setDefaultValue() {		// default value for player
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 6;
		direction = "down";
		// player status(27/05/2022)
		maxLife = 6;
		life = maxLife; // 2 life = 1 heart
	}
	
	// get the images of player
	public void getImage() {
		try {
			// WALKING
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			// ATTACKING
			attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_1.png"));
			attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_2.png"));
			attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_1.png"));
			attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_2.png"));
			attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_1.png"));
			attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_2.png"));
			attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_1.png"));
			attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
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
			
//			// RESET
//			gp.keyH.enterPressed = false;
			
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
			spriteCounter++;	// everytime a frame passed, increase the counter
			
			if (spriteCounter > 10) {
				
				if (spriteNum == 1) {	// change the image
					spriteNum = 2;
				} else {
					spriteNum = 1;
				}
				
				spriteCounter = 0;		// reset counter
			}
		} else {
			// STAND SPRITE
			// switch to stand still animation when no key is pressed
			standCounter++;
			
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
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
		
		spriteCounter++;
		
		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		
		if (5 < spriteCounter && spriteCounter <= 25) {
			spriteNum = 2;
			
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
			damageMonster(monsterIndex);
			
			// restore player's worldX, worldY and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if (25 < spriteCounter) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		// GET IMAGE
		switch(direction) {
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) { image = up1; }
				if (spriteNum == 2) { image = up2; }
			}
			if (attacking == true) {
				if (spriteNum == 1) { image = attackUp1; }
				if (spriteNum == 2) { image = attackUp2; }
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) { image = down1; }
				if (spriteNum == 2) { image = down2; }
			}
			if (attacking == true) {
				if (spriteNum == 1) { image = attackDown1; }
				if (spriteNum == 2) { image = attackDown2; }
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) { image = left1; }
				if (spriteNum == 2) { image = left2; }
			}
			if (attacking == true) {
				if (spriteNum == 1) { image = attackLeft1; }
				if (spriteNum == 2) { image = attackLeft2; }
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) { image = right1; }
				if (spriteNum == 2) { image = right2; }
			}
			if (attacking == true) {
				if (spriteNum == 1) { image = attackRight1; }
				if (spriteNum == 2) { image = attackRight2; }
			}
			break;
		}
		
		// INVINCIBLE VISUAL EFFECT
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
		}
		
		// DRAW
		if (attacking == false) {
		
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		if (attacking == true) {
			
			switch (direction) {
			case "up":
				g2.drawImage(image, screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize * 2, null);
				break;
			case "down":
				g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize * 2, null);
				break;
			case "left":
				g2.drawImage(image, screenX - gp.tileSize, screenY, gp.tileSize * 2, gp.tileSize, null);
				break;
			case "right":
				g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
				break;
			}
		}
		
		// RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
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
			
			if (invincible == false) {
				life--;
				invincible = true;
			}
		}
	}
	
	// give damage to monster
	public void damageMonster(int index) {
		
		if (index != 999) {
			
			if (gp.monster[index].invincible == false) {
				
				gp.monster[index].life -= 1;
				gp.monster[index].invincible = true;
				
				if (gp.monster[index].life <= 0) {
					gp.monster[index] = null;
				}
			}
		}
	}
}
