package com.eyeofmidas.breakout.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eyeofmidas.breakout.BreakoutGame;

public class BallActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private Vector2 acceleration;
	private Vector2 velocity;
	private Vector2 nextPosition;
	private boolean debug = false;

	public BallActor() {
		shapeRenderer = new ShapeRenderer();
		nextPosition = new Vector2();

		acceleration = new Vector2();
		velocity = new Vector2();
		setColor(Color.WHITE);
		setPosition(300, 100);
		setSize(20, 20);
		setAcceleration(0, -10f);
		setVelocity(-300, 800);
	}

	private void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}

	private void setAcceleration(float x, float y) {
		acceleration.x = x;
		acceleration.y = y;
	}

	public Vector2 getNextPosition(float delta) {
		nextPosition.x = getX() + velocity.x * delta;
		nextPosition.y = getY() + velocity.y * delta;
		return nextPosition;

	}

	public void act(float delta) {
		velocity.add(acceleration);
		getNextPosition(delta);

		if (nextPosition.x < 0) {
			nextPosition.x = 0;
			velocity.x *= -1;
		}
		if (nextPosition.x > BreakoutGame.WIDTH - getWidth()) {
			nextPosition.x = BreakoutGame.WIDTH - getWidth();
			velocity.x *= -1;
		}
		if (nextPosition.y < 0) {
			nextPosition.y = 0;
			velocity.y *= -1;
		}
		if (nextPosition.y > BreakoutGame.HEIGHT - getHeight()) {
			nextPosition.y = BreakoutGame.HEIGHT - getHeight();
			velocity.y *= -1;
		}

		setPosition(nextPosition.x, nextPosition.y);
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(getWidth() / 2, getHeight() / 2, getWidth() / 2);
		shapeRenderer.end();
		if (debug) {
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.rect(0, 0, getWidth(), getHeight());
			shapeRenderer.end();
		}

		batch.begin();
	}
}
