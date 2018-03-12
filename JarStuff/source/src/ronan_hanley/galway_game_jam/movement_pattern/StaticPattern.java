package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public class StaticPattern extends MovementPattern {
	private double angle;
	
	public StaticPattern(double angle) {
		this.angle = angle;
	}
	
	public void tick() {}
	
	@Override
	public void setHuman(Human human) {
		super.setHuman(human);
		human.setAngle(angle);
	}
	
}
