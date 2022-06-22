package graphic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

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
	public EntityGraphic(GamePanel gp) {
		this.gp = gp;
	}
	
	// Set entity to draw
	public void setEntity(Entity entity) {
		this.entity = entity;
		this.entity.getImage();
	}
	
	public void draw(Graphics2D g2) {
		// only draw entity within screen's range around player
		if (entity.worldX > gp.player.worldX - gp.player.screenX - gp.tileSize
				&& entity.worldX < gp.player.worldX + gp.player.screenX + gp.tileSize
				&& entity.worldY > gp.player.worldY - gp.player.screenY - gp.tileSize
				&& entity.worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
				
			switch(entity.direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				break;
			}
			
			// position on screen where we draw entity
			screenX = entity.worldX - gp.player.worldX + gp.player.screenX;
			screenY = entity.worldY - gp.player.worldY + gp.player.screenY;
			
			// INVINCIBLE VISUAL EFFECT
			if (entity.invincible == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
			// RESET ALPHA
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		}
	}
}
