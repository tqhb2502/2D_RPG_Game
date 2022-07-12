package npc;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OldMan extends Entity {

	public OldMan(GamePanel gp) {

		super(gp);

		// INFO
		defaultSpeed = 2;
		speed = defaultSpeed;
		onPath = false;

		// SOLID AREA
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void setImage() {

		try {
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
			this.entityGraphic.left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
			this.entityGraphic.right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
			this.entityGraphic.right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAction() {
		
		int xDistance = Math.abs(worldX - defaultWorldX) / gp.tileSize;
		int yDistance = Math.abs(worldY - defaultWorldY) / gp.tileSize;
		if (xDistance > 2 || yDistance > 2) {

			// return to default position
			int col = defaultWorldX / gp.tileSize;
			int row = defaultWorldY / gp.tileSize;
			//System.out.println(col + "," + row);
			searchPath(col, row);
		} else {
			
			if (counter.actionLockCounter == 0) {
				
				Random random = new Random();
				int i = random.nextInt(100) + 1; // get a number between 1 and 100 randomly

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

				counter.actionLockCounter = 120;
			}

			counter.actionLockCounter--;
		}
	}
}
