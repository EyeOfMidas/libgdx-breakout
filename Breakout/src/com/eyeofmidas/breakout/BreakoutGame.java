package com.eyeofmidas.breakout;

import com.badlogic.gdx.Game;
import com.eyeofmidas.breakout.screens.BreakoutScreen;
import com.eyeofmidas.breakout.screens.MainMenuScreen;

public class BreakoutGame extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private MainMenuScreen mainMenuScreen;
	private BreakoutScreen breakoutScreen;

	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		breakoutScreen = new BreakoutScreen(this);
		setScreen(mainMenuScreen);
	}

	public void startGame() {
		setScreen(breakoutScreen);
	}

	public void endGame() {
		setScreen(mainMenuScreen);
	}
}
