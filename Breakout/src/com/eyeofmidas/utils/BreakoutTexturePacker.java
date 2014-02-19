package com.eyeofmidas.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class BreakoutTexturePacker {
	public static void main(String[] args) throws Exception {
		TexturePacker.process("../Breakout-android/assets/data/icons",
				"../Breakout-android/assets/data",
				"category-icons");
	}
}
