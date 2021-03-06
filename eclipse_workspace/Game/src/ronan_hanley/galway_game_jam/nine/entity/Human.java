package ronan_hanley.galway_game_jam.nine.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

import ronan_hanley.galway_game_jam.movement_pattern.MovementPattern;
import ronan_hanley.galway_game_jam.nine.entity.furniture.Furniture;
import ronan_hanley.galway_game_jam.nine.level.Level;

public class Human extends MovingEntity {
	public static final double DEFAULT_SPEED = 1;
	public static final int DEFAULT_SIGHT_DISTANCE = 250;
	
	private static Animation staticAnim;
	
	private MovementPattern movementPattern;
	private int sightDistance;
	private double fov;
	
	private Polygon searchArea;
	
	static {
		staticAnim = new Animation();
		try {
			Image img = new Image("res/images/human.png");
			staticAnim.addFrame(img, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Human(int initialX, int initialY, double maxSpeed, MovementPattern movementPattern, int sightDistance, double fovDeg) {
		super(initialX, initialY, maxSpeed, new Animation(new Image[] {staticAnim.getCurrentFrame().copy()}, 1));
		this.movementPattern = movementPattern;
		this.sightDistance = sightDistance;
		fov = Math.toRadians(fovDeg);
		
		movementPattern.setHuman(this);
		
		getAnim().getCurrentFrame().setImageColor((float) random.nextDouble(), (float) random.nextDouble(), (float) random.nextDouble());
		
		genSearchArea();
	}
	
	public Human(int initialX, int initialY, double maxSpeed, MovementPattern movementPattern, int sightDistance) {
		this(initialX, initialY, maxSpeed, movementPattern, sightDistance, 114);
	}
	
	private void genSearchArea() {
		searchArea = new Polygon();
		searchArea.setClosed(true);
		searchArea.setAllowDuplicatePoints(false);
		
		searchArea.setCenterX(0);
		searchArea.setCenterY(0);
		
		searchArea.addPoint(0, 0);
		
		double angIncrement = getFov() / 16;
		for (double ang = -(getFov() /2); ang < getFov() / 2; ang += angIncrement) {
			double newAng = ang % FULL_RAD_ROTATION;
			float destX = (float) (Math.cos(newAng) * getSightDistance());
			float destY = (float) (Math.sin(newAng) * getSightDistance());
			
			searchArea.addPoint(destX, destY);
		}
		
		//g.setLineWidth(3);
		
	}
	
	@Override
	public void tick(Level level) {
		movementPattern.tick();
		super.tick(level);
	}
	
	@Override
	public void render(Graphics g, Camera cam) {
		// draw fov area
		g.setColor(new Color(1f, 1f, 1f, 0.2f));
		
		Polygon transformedShape = (Polygon) searchArea.transform(Transform.createRotateTransform((float) getAngle()));
		
		transformedShape.setCenterX(0);
		transformedShape.setCenterY(0);
		
		transformedShape.setLocation(getX() + getHalfWidth() - cam.getX(), getY() + getHalfHeight() - cam.getY());
		
		g.fill(transformedShape);
		
		super.render(g, cam);
	}
	
	public Human(int initialX, int initialY, MovementPattern movementPattern) {
		this(initialX, initialY, DEFAULT_SPEED, movementPattern, DEFAULT_SIGHT_DISTANCE);
	}
	
	public boolean canSeeFurniture(Furniture f, Level level) {
		// first pass
		final double allowance = 1.5;
		
		double dist = distanceTo(f);
		
		if (dist > sightDistance * allowance) return false;
		
		double angleTo = Math.atan2(f.getY() - getY(), f.getX() - getX());
		
		if (angleTo < 0) {
			angleTo = (2 * Math.PI) + angleTo;
		}
		
		double angleDiff = Math.abs(angleTo - getAngle()) % (2 * Math.PI);
		
		boolean inRange = angleDiff < (fov /2) * allowance;
		
		if (!inRange) {
			return false;
		}
		
		// second pass
		return level.inLineOfSight(this, f);
	}
	
	public int getSightDistance() {
		return sightDistance;
	}
	
	public double getFov() {
		return fov;
	}
	
	public MovementPattern getMovementPattern() {
		return movementPattern;
	}
	
}
