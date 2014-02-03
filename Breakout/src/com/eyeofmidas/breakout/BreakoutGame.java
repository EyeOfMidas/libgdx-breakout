package com.eyeofmidas.breakout;

import com.badlogic.gdx.Game;
import com.eyeofmidas.breakout.screens.BreakoutScreen;
import com.eyeofmidas.breakout.screens.MainMenuScreen;

public class BreakoutGame extends Game {
	
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
