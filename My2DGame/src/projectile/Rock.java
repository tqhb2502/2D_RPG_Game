package projectile;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Rock extends Projectile {

	public Rock(GamePanel gp) {
		
		super(gp);
		
		name = "Rock";
		speed = 5;
		maxHP = 60;
		currentHP = maxHP;
		projectileAttack = 1;
		mpCost = 1;
		alive = false;
	}

	public void setImage() {
		
		try {
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.left2 =  ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.right1 =  ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
			this.entityGraphic.right2 =  ImageIO.read(getClass().getResourceAsStream("/projectile/rock_down_1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
