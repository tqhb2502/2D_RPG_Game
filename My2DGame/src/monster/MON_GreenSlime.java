package monster;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity{

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		// INFO
		name = "Green Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		// SOLID AREA
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		// IMAGE
		getImage();
	}
	
	public void getImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
