package com.eyeofmidas.breakout.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class DrawnTextButton extends Button {

	private ShapeRenderer shapeRenderer;
	public BitmapFont font;
	public Color fontColor, downFontColor, overFontColor, checkedFontColor, checkedOverFontColor, disabledFontColor;
	private CharSequence buttonLabel;
	private Label label;
	private Color highlightColor;

	public DrawnTextButton(String string) {
		shapeRenderer = new ShapeRenderer();
		highlightColor = new Color();
		FileHandle fontFile = Gdx.files.internal("data/fonts/dosis-30-white.fnt");
		font = new BitmapFont(fontFile, false);
		setColor(67 / 255f, 182 / 255f, 207 / 255f, 1f);
		setHighlightColor(84 / 255f, 199 / 255f, 224 / 255f, 1f);
		setStyle(new ButtonStyle());

		label = new Label(buttonLabel, new LabelStyle(font, new Color(1, 1, 1, 1)));
		setText(string);
		label.setAlignment(Align.center);
		add(label);
	}

	private void setHighlightColor(float r, float g, float b, float a) {
		highlightColor.set(r, g, b, a);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);
		shapeRenderer.begin(ShapeType.Filled);
		if (isOver()) {
			shapeRenderer.setColor(getHighlightColor());
		} else {
			shapeRenderer.setColor(getColor());
		}
		shapeRenderer.rect(0, 0, getPrefWidth(), getPrefHeight());
		shapeRenderer.end();

		batch.begin();
		super.draw(batch, parentAlpha);
	}

	private Color getHighlightColor() {
		return highlightColor;
	}

	@Override
	public float getPrefWidth() {
		return getWidth();
	}

	@Override
	public float getPrefHeight() {
		return getHeight();
	}

	public void setText(CharSequence text) {
		label.setText(text);
	}
}
