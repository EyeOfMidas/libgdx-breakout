package com.eyeofmidas.breakout.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.eyeofmidas.breakout.BreakoutGame;

public class BackgroundStage extends Stage {

	private ShapeRenderer shapeRenderer;
	private Color backgroundColor;
	private Color panelColor;

	public BackgroundStage() {
		shapeRenderer = new ShapeRenderer();
		backgroundColor = new Color(0.1137f, 0.16f, 0.145f, 1f);
		panelColor = new Color(0.976f, 0.976f, 0.976f, 1f);
	}

	@Override
	public void draw() {
		shapeRenderer.setProjectionMatrix(this.getSpriteBatch().getProjectionMatrix());
		shapeRenderer.setTransformMatrix(this.getSpriteBatch().getTransformMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(backgroundColor);
		shapeRenderer.rect(0, 0, BreakoutGame.WIDTH, BreakoutGame.HEIGHT);
		shapeRenderer.end();

		super.draw();
	}
}
