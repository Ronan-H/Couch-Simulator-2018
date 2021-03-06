package ronan_hanley.galway_game_jam.nine.state;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.galway_game_jam.movement_pattern.BackAndForthPattern;
import ronan_hanley.galway_game_jam.movement_pattern.LeftRightPattern;
import ronan_hanley.galway_game_jam.movement_pattern.SpinAroundPattern;
import ronan_hanley.galway_game_jam.movement_pattern.StaticPattern;
import ronan_hanley.galway_game_jam.movement_pattern.UpDownPattern;
import ronan_hanley.galway_game_jam.nine.Game;
import ronan_hanley.galway_game_jam.nine.Input;
import ronan_hanley.galway_game_jam.nine.entity.Camera;
import ronan_hanley.galway_game_jam.nine.entity.Human;
import ronan_hanley.galway_game_jam.nine.entity.furniture.Couch;
import ronan_hanley.galway_game_jam.nine.entity.furniture.Furniture;
import ronan_hanley.galway_game_jam.nine.level.Level;

public class PlayingState extends TransferableState {
	private List<Furniture> furniture;
	private List<Human> humans;
	private Furniture player;
	private Level currentLevel;
	private Camera camera;
	
	private static final int spawnX = 100, spawnY = 1450;
	//private static final int spawnX = 805, spawnY = 174; // ezpz spawn
	
	private Music playerMovingMusic;
	private boolean movingMusicPlaying;
	
	private Sound caughtSound;
	
	private long ticks = 0;
	
	public PlayingState(StateBasedGame sbg, Input input) {
		super(sbg, input);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		
		currentLevel = new Level(1);
		
		furniture = new ArrayList<Furniture>();
		humans = new ArrayList<Human>();
		player = new Couch(spawnX, spawnY);
		camera = new Camera(0, currentLevel.getHeight() - Game.screenHeight, player);
		
		humans.add(new Human(445, 1095, new UpDownPattern(445, 835)));
		humans.add(new Human(950, 1460, Human.DEFAULT_SPEED * 2, new UpDownPattern(950, 457), Human.DEFAULT_SIGHT_DISTANCE));
		humans.add(new Human(567, 494, new SpinAroundPattern()));
		humans.add(new Human(330, 434, new StaticPattern((2 * Math.PI * 0.375))));
		humans.add(new Human(62, 158, Human.DEFAULT_SPEED / 2d, new LeftRightPattern(422, 158), Human.DEFAULT_SIGHT_DISTANCE / 2));
		humans.add(new Human(550, 350, Human.DEFAULT_SPEED, new UpDownPattern(500, 60), Human.DEFAULT_SIGHT_DISTANCE / 3));
		humans.add(new Human(730, 350, Human.DEFAULT_SPEED, new UpDownPattern(730, 60), Human.DEFAULT_SIGHT_DISTANCE / 3));
		
		((BackAndForthPattern)humans.get(humans.size() - 1).getMovementPattern()).goToDest();
		
		// Galway game jam room
		int[] chairRowLocs = {66, 155, 214, 307};
		
		for (int i = 0; i < chairRowLocs.length; ++i) {
			for (int j = 0; j < 6; ++j) {
				humans.add(new Human(
					867 + (j * 50),
					chairRowLocs[i],
					Human.DEFAULT_SPEED,
					new StaticPattern((i % 2 == 0 ? (2 * Math.PI) * 0.25 : (2 * Math.PI* 0.75))),
					30));
			}
		}
		
		humans.add(new Human(1308, 260, new SpinAroundPattern(SpinAroundPattern.DEFAULT_ANGVEL * 4)));
		
		final int longSightDist = 1000;
		humans.add(new Human(1637, 833, 0,
				new SpinAroundPattern(-(SpinAroundPattern.DEFAULT_ANGVEL)), longSightDist, 10));
		humans.add(new Human(1741, 863, 0,
				new SpinAroundPattern(-(SpinAroundPattern.DEFAULT_ANGVEL)), longSightDist, 10));
		
		playerMovingMusic = new Music("res/music/PlayerMoving.ogg");
		playerMovingMusic.play();
		playerMovingMusic.pause();
		
		caughtSound = new Sound("res/sound/Caught.ogg");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		input.tick();
		
		if (player != null) {
			// process input
			int xMag = 0;
			int yMag = 0;
			
			if (input.upHeld) yMag -= 1;
			if (input.downHeld) yMag += 1;
			if (input.leftHeld) xMag -= 1;
			if (input.rightHeld) xMag += 1;
			
			if (!(xMag == 0 && yMag == 0)) {
				player.setAngle(Math.atan2(yMag, xMag));
				player.goMaxSpeed();
			}
			else {
				player.stop();
			}
			
			if (input.rotateNewlyPressed) {
				player.tryRotate(currentLevel, true);
			}
			
			player.tick(currentLevel);
			
			if (player.isMoving()) {
				if (!movingMusicPlaying) {
					playerMovingMusic.resume();
					movingMusicPlaying = true;
				}
				
				if (movingMusicPlaying && !playerMovingMusic.playing()) {
					playerMovingMusic.play();
				}
			}
			else {
				if (movingMusicPlaying) {
					playerMovingMusic.pause();
					movingMusicPlaying = false;
				}
			}
			
			// check for win
			if (player.getX() >= 1933 && player.getX() <= 2258
			 && player.getY() >= 29 && player.getY() <= 112) {
				this.enterState(1);
			}
		}
		
		for (Furniture f : furniture) {
			f.tick(currentLevel);
		}
		
		for (Human h : humans) {
			h.tick(currentLevel);
			
			if (ticks % 5 == 0) {
				if (h.canSeeFurniture(player, currentLevel)) {
					onPlayerSpotted();
				}
			}
		}
		
		camera.tick();
		
		++ticks;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		currentLevel.render(g, camera);
		
		for (Furniture f : furniture) {
			f.render(g, camera);
		}
		
		for (Human h : humans) {
			h.render(g, camera);
		}
		
		if (player != null) player.render(g, camera);
	}
	
	public void onPlayerSpotted() {
		caughtSound.play();
		resetPlayerPosition();
	}
	
	public void resetPlayerPosition() {
		if (player != null) {
			player.setX(spawnX);
			player.setY(spawnY);
			
			player.resetRotation(currentLevel);
		}
	}
	
	@Override
	public int getID() {
		return 0;
	}

}
