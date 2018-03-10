package ronan_hanley.galway_game_jam.nine.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import ronan_hanley.galway_game_jam.nine.Input;

public abstract class TransferableState extends BasicGameState {
	private StateBasedGame sbg;
	protected Input input;
	
	public TransferableState(StateBasedGame sbg, Input input) {
		this.sbg = sbg;
		this.input = input;
	}
	
	protected void enterState(int id) {
		sbg.enterState(id);
	}
	
	protected void enterState(int id, Transition leave, Transition enter) {
		sbg.enterState(id, leave, enter);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getInput().addKeyListener(input);
	}

	@Override
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;

	@Override
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;

	@Override
	public abstract int getID();
	
}
