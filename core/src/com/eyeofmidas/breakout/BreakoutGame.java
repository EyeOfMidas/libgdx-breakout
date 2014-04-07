package com.eyeofmidas.breakout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.eyeofmidas.breakout.screens.BreakoutScreen;
import com.eyeofmidas.breakout.screens.GameOverScreen;
import com.eyeofmidas.breakout.screens.LoadingScreen;
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
	private AssetManager manager;
	private LoadingScreen loadingScreen;

	@Override
	public void create() {
		manager = new AssetManager();
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	public void finishedLoading() {
		header = new Header();
		mainMenuScreen = new MainMenuScreen(this);
		breakoutScreen = new BreakoutScreen(this);
		gameOverScreen = new GameOverScreen(this);
		pauseScreen = new PauseScreen(this);
		setScreen(mainMenuScreen);
		// setScreen(gameOverScreen);
	}

	@Override
	public void dispose() {
		manager.dispose();
		loadingScreen.dispose();
		mainMenuScreen.dispose();
		breakoutScreen.dispose();
		gameOverScreen.dispose();
		pauseScreen.dispose();
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

	public AssetManager getAssetManager() {
		return manager;
	}
}
