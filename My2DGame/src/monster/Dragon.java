package monster;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import graphic.EntityGraphic;
import main.GamePanel;
import projectile.Fireball;
import projectile.Rock;

public class Dragon extends Entity{

	public Dragon(GamePanel gp) {
		
		super(gp);
		
		// INFO
		name = "Dragon";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxHP = 4;
		currentHP = maxHP;
		type = 3;
		normalAttack = 2;
		onPath = false;
		
		// PROJECTILE
		projectile = new Rock(gp);
		projectile.setEntityGraphic(new EntityGraphic(projectile));
		
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
	public void update() {
		super.update();
		int xDistance = Math.abs(worldX - gp.player.worldX)/gp.tileSize;
		int yDistance = Math.abs(worldY - gp.player.worldY)/gp.tileSize;
		int distance = xDistance + yDistance;
		if(distance < 5 && onPath == false) {
			onPath = true;
		}
		if(distance > 20 && onPath ==true) {
			onPath = false;
		}
	}

	public void setAction() {
		
		if(onPath == true) {
			int col = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
			int row = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;;
			searchPath(col, row);
			
		}
		
	else {
		if(counter.actionLockCounter == 0) {
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
			
			counter.actionLockCounter = 120;
		}
		
		counter.actionLockCounter--;
	
	}
		
		int i = new Random().nextInt(100) + 1;
		if ( i>=99 && projectile.alive == false && counter.shotAvailableCounter == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			counter.shotAvailableCounter = 0;
		}
	}
}
