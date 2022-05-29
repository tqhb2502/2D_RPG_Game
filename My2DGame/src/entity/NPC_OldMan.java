package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		// INFO
		speed = 1;
		direction = "down";
		
		// SOLID AREA
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		// IMAGE
		getImage();
	}
	
	public void getImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
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
}
