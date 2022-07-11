package main;

public class Counter {
	
	public int actionLockCounter; // entity can not do a specific action until counter counts to certain number
	public int invincibleCounter; // the number of frames that the entity is invincible
	public int mpRecoverCounter;	// when this counter counts to a specified number, MP is recovered
	public int movingCounter; // the number of frames that the entity moves
	public int standCounter; // the number of frames that the entity stands still
	public int frameCounter; // the number of frames passed
	public int shotAvailableCounter;
	public int dashAvailableCounter;
	
	public Counter() {
		actionLockCounter = 0;
		invincibleCounter = 0;
		mpRecoverCounter = 0;
		movingCounter = 0;
		standCounter = 0;
		frameCounter = 0;
		shotAvailableCounter = 0;
		dashAvailableCounter = 0;
	}
}
