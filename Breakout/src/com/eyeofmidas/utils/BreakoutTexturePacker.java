package com.eyeofmidas.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class BreakoutTexturePacker {
	public static void main(String[] args) throws Exception {
		TexturePacker.process("/home/jgilman/git/breakout-libgdx/Breakout-android/assets/data/icons",
				"/home/jgilman/git/breakout-libgdx/Breakout-android/assets/data",
				"category-icons");
	}
}
