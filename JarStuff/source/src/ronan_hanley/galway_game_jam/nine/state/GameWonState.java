package ronan_hanley.galway_game_jam.nine.state;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.galway_game_jam.nine.Game;
import ronan_hanley.galway_game_jam.nine.Input;

public class GameWonState extends TransferableState {

	public GameWonState(StateBasedGame sbg, Input input) {
		super(sbg, input);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Font font = new Font("Times New Roman", Font.BOLD, 60);
		TrueTypeFont ttf = new TrueTypeFont(font, true);
		
		g.setBackground(Color.white);
		
		g.setColor(Color.black);
		g.setFont(ttf);
		String winText = "YOU WIN!";
		g.drawString(winText, Game.screenWidth / 2 - (ttf.getWidth(winText) /2), Game.screenHeight /2 - (ttf.getHeight(winText) /2));
		
		//String freeCouchText = "You are a free couch now!";
		//g.drawString(freeCouchText, Game.screenWidth / 2 - (ttf.getWidth(freeCouchText) /2), Game.screenHeight /2 - ((ttf.getHeight(freeCouchText) /2)) * 2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	@Override
	public int getID() {
		return 1;
	}

}
