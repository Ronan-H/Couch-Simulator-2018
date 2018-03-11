package ronan_hanley.galway_game_jam.nine.entity;

import java.util.Random;

public abstract class Entity {
	private double x, y;
	protected Random random;
	
	public Entity(int initialX, int initialY) {
		setX(initialX);
		setY(initialY);
		
		random = new Random();
	}
	
	public int getX() {
		return (int) Math.round(x);
	}
	
	public int getY() {
		return (int) Math.round(y);
	}
	
	public double getXExact() {
		return x;
	}
	
	public double getYExact() {
		return y;
	}
	
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public void changeX(double change) {
		setX(getXExact() + change);
	}
	
	public void changeY(double change) {
		setY(getYExact() + change);
	}
	
	public double distanceTo(Entity otherEntity) {
		return Math.sqrt(Math.pow(otherEntity.getX() - getX(), 2) + Math.pow(otherEntity.getY() - getY(), 2));
	}
	
}
