package monster;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Dragon extends Entity{

	public Dragon(GamePanel gp) {
		
		super(gp);
		
		// INFO
		name = "Dragon";
		speed = 1;
		maxHP = 4;
		currentHP = maxHP;
		type = 4;
		
		// SOLID AREA
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void setImage() {
		
		try {
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up2.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up2.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up1.png"));
			this.entityGraphic.left2 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up2.png"));
			this.entityGraphic.right1 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up1.png"));
			this.entityGraphic.right2 = ImageIO.read(getClass().getResourceAsStream("/monster/dr_up2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setAction() {
		
		if (actionLockCounter == 0) {
			
			Random random = new Random();
			int i = random.nextInt(100) + 1;	// get a number between 1 and 100 randomly
			
			if (i <= 25) {
				direction = "up";
			}
			
			if (25 < i && i <= 50) {
				direction = "down";
			}
			
			if (50 < i && i <= 75) {
				direction = "left";
			}
			
			if (75 < i) {
				direction = "right";
			}
			
			actionLockCounter = 120;
		}
		
		actionLockCounter--;
	}
	
	public void update() {
		// DIRECTION
		setAction();
		
		// COLLISION
		collisionOn = false;
		// player
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if (contactPlayer == true) {
			
			if (gp.player.invincible == false) {
				gp.player.currentHP -= 1;
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
		this.entityGraphic.spriteCounter++;
		if (this.entityGraphic.spriteCounter > 10) {
			if (this.entityGraphic.spriteNum == 1) {	// change the image
				this.entityGraphic.spriteNum = 2;
			} else {
				this.entityGraphic.spriteNum = 1;
			}
			
			this.entityGraphic.spriteCounter = 0;		// reset counter
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
