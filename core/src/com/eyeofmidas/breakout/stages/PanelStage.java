package com.eyeofmidas.breakout.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.eyeofmidas.breakout.BreakoutGame;

public class PanelStage extends Stage {

	private ShapeRenderer shapeRenderer;
	private Color backgroundColor;
	private Color panelColor;

	public PanelStage() {
		shapeRenderer = new ShapeRenderer();
		backgroundColor = new Color(0.1137f, 0.16f, 0.145f, 1f);
		panelColor = new Color(0.976f, 0.976f, 0.976f, 1f);
	}

	@Override
	public void draw() {
		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(panelColor);
		shapeRenderer.rect(100 * BreakoutGame.scale.x, 100 * BreakoutGame.scale.y, 600 * BreakoutGame.scale.x, 400 * BreakoutGame.scale.y);
		shapeRenderer.setColor(backgroundColor);
		shapeRenderer.rect(108 * BreakoutGame.scale.x, 108 * BreakoutGame.scale.y, 584 * BreakoutGame.scale.x, 384 * BreakoutGame.scale.y);
		shapeRenderer.setColor(panelColor);
		shapeRenderer.rect(112 * BreakoutGame.scale.x, 112 * BreakoutGame.scale.y, 576 * BreakoutGame.scale.x, 376 * BreakoutGame.scale.y);

		shapeRenderer.setColor(0.9f, 0.9f, 0.9f, 1);
		shapeRenderer.rect(150 * BreakoutGame.scale.x, 320 * BreakoutGame.scale.y, 500 * BreakoutGame.scale.x, 4 * BreakoutGame.scale.y);
		shapeRenderer.rect(150 * BreakoutGame.scale.x, 400 * BreakoutGame.scale.y, 500 * BreakoutGame.scale.x, 4 * BreakoutGame.scale.y);
		
		shapeRenderer.end();

		super.draw();
	}
}
