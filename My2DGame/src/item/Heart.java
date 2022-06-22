package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity{
	
	public Heart(GamePanel gp) {
		super(gp);
		name = "Heart";
	}
	
	public void setImage() {
		try {
			this.entityGraphic.image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			this.entityGraphic.image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			this.entityGraphic.image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
