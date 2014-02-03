package com.eyeofmidas.breakout.renderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.eyeofmidas.breakout.entities.BallEntity;

public class BallRenderer {
	private final ShapeRenderer renderer = new ShapeRenderer();
	private ArrayList<BallEntity> balls = new ArrayList<BallEntity>();
	
	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.Filled);

		for(BallEntity ball : this.balls) {
			this.renderer.setColor(ball.getColor());
			Rectangle ballBounds = ball.getBounds();
			this.renderer.rect(ballBounds.x,
					ballBounds.y,
					ballBounds.width,
					ballBounds.height);	
		}
		this.renderer.end();
	}
}
