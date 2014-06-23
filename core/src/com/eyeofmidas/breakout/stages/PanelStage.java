package com.eyeofmidas.breakout.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
		shapeRenderer.rect(100, 100, 600, 400);
		shapeRenderer.setColor(backgroundColor);
		shapeRenderer.rect(108, 108, 584, 384);
		shapeRenderer.setColor(panelColor);
		shapeRenderer.rect(112, 112, 576, 376);
		shapeRenderer.end();

		super.draw();
	}
}
