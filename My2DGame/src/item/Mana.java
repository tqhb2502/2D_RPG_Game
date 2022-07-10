package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Mana extends Entity{

	public Mana(GamePanel gp) {
		super(gp);
		name = "Mana";
		
	}
	public void setImage() {
		try {
			this.entityGraphic.image = ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_full.png"));
			this.entityGraphic.image2 = ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_blank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
