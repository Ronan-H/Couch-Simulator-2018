package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public abstract class BackAndForthPattern extends MovementPattern {
	protected int srcX, srcY;
	protected int destX, destY;
	protected boolean forwardPhase;
	
	public BackAndForthPattern(int destX, int destY) {
		this.destX = destX;
		this.destY = destY;
		
		forwardPhase = true;
	}
	
	@Override
	public abstract void tick();
	
	public void setHuman(Human human) {
		super.setHuman(human);
		
		srcX = human.getX();
		srcY = human.getY();
	}
	
	public void goToDest() {
		getHuman().setX(destX);
		getHuman().setY(destY);
	}
	
}
