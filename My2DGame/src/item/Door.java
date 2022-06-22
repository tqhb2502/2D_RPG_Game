package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Door extends Entity{
	
	public Door(GamePanel gp) {
		super(gp);
		name = "Door";
		collision = true;
	}
	
	public void setImage() {
		try {
			this.entityGraphic.image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
