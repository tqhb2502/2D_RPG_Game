package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{	// object key
	public OBJ_Key(GamePanel gp) {
		
		super(gp);
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
