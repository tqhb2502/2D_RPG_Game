package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	public OBJ_Door(GamePanel gp) {
		
		super(gp);
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
