package com.eyeofmidas.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AlignedBitmapFont extends BitmapFont {
	public static enum FontAlign {
		LEFT, RIGHT, CENTER, JUSTIFY
	};

	public void draw(SpriteBatch batch, CharSequence text, float x, float y,
			FontAlign alignment) {
		float xPos = x;
		float yPos = y;
		switch (alignment) {
		case LEFT:
			break;
		case CENTER:
			xPos -= this.getBounds(text).width / 2;
			break;
		case JUSTIFY:
			break;
		case RIGHT:
			xPos -= this.getBounds(text).width;
			break;
		default:
			break;
		}
		super.draw(batch, text, xPos, yPos);
	}
}
