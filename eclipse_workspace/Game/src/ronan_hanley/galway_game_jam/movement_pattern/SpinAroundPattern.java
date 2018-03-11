package ronan_hanley.galway_game_jam.movement_pattern;

public class SpinAroundPattern extends MovementPattern {
	public static final double DEFAULT_ANGVEL = 0.01d;
	
	private double angVel;
	
	public SpinAroundPattern() {
		this(DEFAULT_ANGVEL);
	}
	
	public SpinAroundPattern(double angVel) {
		this.angVel = angVel;
	}
	
	public void tick() {
		getHuman().changeAngle(angVel);
	}
	
}
