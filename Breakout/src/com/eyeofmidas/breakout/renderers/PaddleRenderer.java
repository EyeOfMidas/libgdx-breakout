package com.eyeofmidas.breakout.renderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.eyeofmidas.breakout.entities.PaddleEntity;

public class PaddleRenderer {
	private final ShapeRenderer renderer = new ShapeRenderer();
	private ArrayList<PaddleEntity> paddles = new ArrayList<PaddleEntity>();

	public void render(SpriteBatch batch, Camera cam) {
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.Filled);

		for (PaddleEntity paddle : this.paddles) {
			this.renderer.setColor(paddle.getColor());
			Rectangle paddleBounds = paddle.getBounds();
			this.renderer.rect(paddleBounds.x, paddleBounds.y,
					paddleBounds.width, paddleBounds.height);
		}
		this.renderer.end();
	}

	public void updatePaddles(ArrayList<PaddleEntity> updatedList) {
		paddles = updatedList;
	}
}
