package projectile;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Fireball extends Projectile {

	public Fireball(GamePanel gp) {
		
		super(gp);
		
		name = "Fireball";
		speed = 5;
		maxHP = 60;
		currentHP = maxHP;
		projectileAttack = 2;
		mpCost = 1;
		alive = false;
	}

	public void setImage() {
		
		try {
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_up_1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_up_2.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_down_1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_down_2.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_left_1.png"));
			this.entityGraphic.left2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_left_2.png"));
			this.entityGraphic.right1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_right_1.png"));
			this.entityGraphic.right2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_right_2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
