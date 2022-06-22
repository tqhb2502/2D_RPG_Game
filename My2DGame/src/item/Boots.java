package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Boots extends Entity{
	
	public Boots(GamePanel gp) {
		super(gp);
		name = "Boots";
	}
	
	public void setImage() {
		try {
			this.entityGraphic.image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
