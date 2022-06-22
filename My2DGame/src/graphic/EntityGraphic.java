package graphic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import player.Player;

public class EntityGraphic {
	GamePanel gp;
	Entity entity;
	
	public int screenX, screenY;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;	// store entity's walking image
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2,
		attackRight1, attackRight2;		// store entity's attacking image
	public BufferedImage image, image2, image3;		// store image to be displayed
	
	public int spriteNum = 1;	// the index of current displayed image
	public int spriteCounter = 0;	// the number of frames that the entity moves
	public int standCounter = 0;	// the number of frames that the entity stands still
	
	// CONSTRUCTOR
	public EntityGraphic(GamePanel gp, Entity entity) {
		this.gp = gp;
		this.entity = entity;
		
		if (entity instanceof Player) {
			screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);	
			screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);
		}
	}
	
	public void draw(Graphics2D g2) {
		
		// only draw entity within screen's range around player
		if (entity.worldX > gp.player.worldX - gp.player.entityGraphic.screenX - gp.tileSize
				&& entity.worldX < gp.player.worldX + gp.player.entityGraphic.screenX + gp.tileSize
				&& entity.worldY > gp.player.worldY - gp.player.entityGraphic.screenY - gp.tileSize
				&& entity.worldY < gp.player.worldY + gp.player.entityGraphic.screenY + gp.tileSize) {
				
			// GET IMAGE
			switch(entity.direction) {
			case "up":
				if (entity.attacking == false) {
					if (spriteNum == 1) { image = up1; }
					if (spriteNum == 2) { image = up2; }
				}
				if (entity.attacking == true) {
					if (spriteNum == 1) { image = attackUp1; }
					if (spriteNum == 2) { image = attackUp2; }
				}
				break;
			case "down":
				if (entity.attacking == false) {
					if (spriteNum == 1) { image = down1; }
					if (spriteNum == 2) { image = down2; }
				}
				if (entity.attacking == true) {
					if (spriteNum == 1) { image = attackDown1; }
					if (spriteNum == 2) { image = attackDown2; }
				}
				break;
			case "left":
				if (entity.attacking == false) {
					if (spriteNum == 1) { image = left1; }
					if (spriteNum == 2) { image = left2; }
				}
				if (entity.attacking == true) {
					if (spriteNum == 1) { image = attackLeft1; }
					if (spriteNum == 2) { image = attackLeft2; }
				}
				break;
			case "right":
				if (entity.attacking == false) {
					if (spriteNum == 1) { image = right1; }
					if (spriteNum == 2) { image = right2; }
				}
				if (entity.attacking == true) {
					if (spriteNum == 1) { image = attackRight1; }
					if (spriteNum == 2) { image = attackRight2; }
				}
				break;
			}
			
			// POSITION ON SCREEN
			screenX = entity.worldX - gp.player.worldX + gp.player.entityGraphic.screenX;
			screenY = entity.worldY - gp.player.worldY + gp.player.entityGraphic.screenY;
			
			// INVINCIBLE VISUAL EFFECT
			if (entity.invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
			}
			
			// DRAW
			if (entity.attacking == false) {
				
				g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			} else {
				
				switch (entity.direction) {
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
	}
}
