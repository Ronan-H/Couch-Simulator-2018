package ronan_hanley.galway_game_jam.nine;

import static org.newdawn.slick.Input.*;

import org.newdawn.slick.InputListener;

public class Input implements InputListener {
	public boolean leftHeld;
	public boolean rightHeld;
	public boolean upHeld;
	public boolean downHeld;
	
	public boolean rotateHeld;
	private boolean rotateHeldLastTick;
	public boolean rotateNewlyPressed;
	
	public void toggle(int keycode, boolean state) {
		switch (keycode) {
		case KEY_W:
			upHeld = state;
			break;
		case KEY_S:
			downHeld = state;
			break;
		case KEY_A:
			leftHeld = state;
			break;
		case KEY_D:
			rightHeld = state;
			break;
		case KEY_UP:
		case KEY_DOWN:
		case KEY_LEFT:
		case KEY_RIGHT:
			rotateHeld = state;
			break;
		}
	}
	
	public void tick() {
		rotateNewlyPressed = rotateHeld && !rotateHeldLastTick;
		
		rotateHeldLastTick = rotateHeld;
	}
	
	@Override
	public void keyPressed(int keycode, char keyChar) {
		toggle(keycode, true);
	}

	@Override
	public void keyReleased(int keycode, char keyChar) {
		toggle(keycode, false);
	}
	
	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {}

	@Override
	public void mouseWheelMoved(int arg0) {}

	@Override
	public void inputEnded() {}

	@Override
	public void inputStarted() {}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {}

	@Override
	public void controllerDownPressed(int arg0) {}

	@Override
	public void controllerDownReleased(int arg0) {}

	@Override
	public void controllerLeftPressed(int arg0) {}

	@Override
	public void controllerLeftReleased(int arg0) {}

	@Override
	public void controllerRightPressed(int arg0) {}

	@Override
	public void controllerRightReleased(int arg0) {}

	@Override
	public void controllerUpPressed(int arg0) {}

	@Override
	public void controllerUpReleased(int arg0) {}
	
	@Override
	public void setInput(org.newdawn.slick.Input arg0) {}
	
}
