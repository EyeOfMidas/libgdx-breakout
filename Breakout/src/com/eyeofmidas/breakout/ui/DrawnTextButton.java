package com.eyeofmidas.breakout.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DrawnTextButton extends Actor {

	private ShapeRenderer shapeRenderer;
	public BitmapFont font;
	/** Optional. */
	public Color fontColor, downFontColor, overFontColor, checkedFontColor, checkedOverFontColor, disabledFontColor;
	private CharSequence buttonLabel;
	private TextBounds fontBounds;

	public DrawnTextButton() {
		shapeRenderer = new ShapeRenderer();
		FileHandle fontFile = Gdx.files.internal("data/fonts/freesans-32-white.fnt");
		font = new BitmapFont(fontFile, false);
		setColor(new Color(0.5f,0.5f,0.5f,1));
		buttonLabel = "button text";
		fontBounds = font.getBounds(buttonLabel);
		setSize(fontBounds.width, fontBounds.height);
		setPosition(400, 300);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() - getWidth() / 2, getY() - getHeight() / 2, 0); 
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(getColor());
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		batch.begin();
		font.draw(batch, buttonLabel, getX() - getWidth() / 2 - 3, getY() + (3 * getHeight() / 4));
	}
}
