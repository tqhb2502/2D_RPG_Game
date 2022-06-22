package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Chest extends Entity{
	
	public Chest(GamePanel gp) {
		super(gp);
		name = "Chest";
	}
	
	public void setImage() {
		try {
			this.entityGraphic.image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
