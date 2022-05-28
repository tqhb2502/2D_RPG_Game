package entity;

import java.awt.Rectangle;
import java.io.IOException;

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
		solidArea.x = 9;
		solidArea.y = 21;
		solidArea.width = 27;
		solidArea.height = 21;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		// IMAGE
		getImage();
	}
	
	public void setDefaultValue() {		// default value for player
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 22;
		speed = 6;
		direction = "down";
		// player status(27/05/2022)
		maxLife = 6;
		life = maxLife; // 2 life = 1 heart
	}
	
	// get the images of player
	public void getImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		// update only when a key is pressed
		if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
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
			
			collisionOn = false;	// reset collision status
			
			// TILE COLLISION
			gp.cChecker.checkTile(this);
			
			// OBJECT COLLISION
			int objectIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objectIndex);
			
			// NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
			
//			// RESET
//			gp.keyH.enterPressed = false;
			
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
			// switch to stand still animation when no key is pressed
			standCounter++;
			
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
	}
	
	// handle pick up action
	public void pickUpObject(int index) {
		
		if (index != 999) {
			
		}
	}
	
	// interact with NPC
	public void interactNPC(int index) {
		
		if (index != 999) {
			System.out.println("Hiting NPC!");
		}
	}
}
