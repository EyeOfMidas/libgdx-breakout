package com.eyeofmidas.breakout.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eyeofmidas.breakout.BreakoutGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Breakout";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new BreakoutGame(), config);
	}
}
