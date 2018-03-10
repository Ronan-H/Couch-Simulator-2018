package ronan_hanley.galway_game_jam.nine.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

public abstract class TransferableState extends BasicGameState {
	private StateBasedGame sbg;
	
	public TransferableState(StateBasedGame sbg) {
		this.sbg = sbg;
	}
	
	protected void enterState(int id) {
		sbg.enterState(id);
	}
	
	protected void enterState(int id, Transition leave, Transition enter) {
		sbg.enterState(id, leave, enter);
	}

	@Override
	public abstract void init(GameContainer arg0, StateBasedGame arg1) throws SlickException;

	@Override
	public abstract void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException;

	@Override
	public abstract void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException;

	@Override
	public abstract int getID();
	
}
