package com.eyeofmidas.breakout.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eyeofmidas.breakout.BreakoutGame;

public class Header extends Actor {
	private ShapeRenderer shapeRenderer;

	public Header(float width, float height) {
		this.setPosition(0, height - 50 * BreakoutGame.scale.y);
		this.setSize(width, 50 * BreakoutGame.scale.y);
		this.setColor(new Color(67 / 255f, 182 / 255f, 207 / 255f, 1f));

		shapeRenderer = new ShapeRenderer();
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(getColor());
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		batch.begin();
	}
}
