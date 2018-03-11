package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public abstract class MovementPattern {
	public static final int STATIC = 0;
	public static final int UP_DOWN = 1;
	public static final int LEFT_RIGHT = 2;
	public static final int SPIN_AROUND = 3;
	
	private Human human;
	
	public abstract void tick();
	
	public void setHuman(Human human) {
		this.human = human;
	}
	
	public Human getHuman() {
		return human;
	}
	
}
