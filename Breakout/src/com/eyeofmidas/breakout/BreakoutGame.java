package com.eyeofmidas.breakout;

import com.badlogic.gdx.Game;
import com.eyeofmidas.breakout.screens.BreakoutScreen;
import com.eyeofmidas.breakout.screens.GameOverScreen;
import com.eyeofmidas.breakout.screens.MainMenuScreen;
import com.eyeofmidas.breakout.screens.PauseScreen;
import com.eyeofmidas.breakout.ui.Header;

public class BreakoutGame extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private MainMenuScreen mainMenuScreen;
	private BreakoutScreen breakoutScreen;
	public Header header;
	private GameOverScreen gameOverScreen;
	private PauseScreen pauseScreen;

	@Override
	public void create() {
		header = new Header();

		mainMenuScreen = new MainMenuScreen(this);
		breakoutScreen = new BreakoutScreen(this);
		gameOverScreen = new GameOverScreen(this);
		pauseScreen = new PauseScreen(this);
		setScreen(mainMenuScreen);
	}

	public void startGame() {
		breakoutScreen.reset();
		setScreen(breakoutScreen);
	}

	public void endGame() {
		setScreen(gameOverScreen);
	}

	public void pauseGame() {
		// setScreen(pauseScreen);
		setScreen(mainMenuScreen);

	}
}
