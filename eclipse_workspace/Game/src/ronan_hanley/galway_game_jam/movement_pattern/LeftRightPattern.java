package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public class LeftRightPattern extends BackAndForthPattern {
	
	public LeftRightPattern(int destX, int destY) {
		super(destX, destY);
	}

	
	@Override
	public void tick() {
		Human h = getHuman();
		
		h.goMaxSpeed();
		
		if (forwardPhase) {
			h.goRight();
			
			if (h.getX() >= destX) {
				forwardPhase = false;
			}
		}
		else {
			h.goLeft();
			
			if (h.getX() <= srcX) {
				forwardPhase = true;
			}
		}
	}
	
}
