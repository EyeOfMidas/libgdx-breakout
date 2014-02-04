package com.eyeofmidas.breakout.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eyeofmidas.breakout.logics.Collideable;

public class BallEntity implements Collideable {
	private Vector2 position;
	private Vector2 size;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 drag;

	public BallEntity() {
		position = new Vector2(0, 0);
		size = new Vector2(20, 20);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		drag = new Vector2(1f, 1f);
	}

	public void update(float delta) {
		velocity.add(acceleration);
		velocity.scl(drag);
		position.x += velocity.x;
		position.y += velocity.y;

	}

	public Vector2 getNextPosition(float delta) {
		Vector2 newPos = new Vector2();
		newPos.x = position.x + acceleration.x + velocity.x;
		newPos.y = position.y + acceleration.y + velocity.y;
		return newPos;
	}

	public Rectangle getBounds() {
		return new Rectangle(position.x - size.x / 2, position.y - size.y / 2,
				size.x, size.y);
	}

	public Color getColor() {
		return Color.WHITE;
	}

	@Override
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}

	public void setAcceleration(float x, float y) {
		acceleration.x = x;
		acceleration.y = y;
	}

	@Override
	public void setPosition(float x, float y) {
		position.x = x + size.x / 2;
		position.y = y + size.y / 2;
	}

	public void setPosition(Vector2 newPos) {
		setPosition(newPos.x, newPos.y);
	}

	@Override
	public float getX() {
		return position.x - size.x / 2;
	}

	@Override
	public float getY() {
		return position.y - size.y / 2;
	}

	@Override
	public float getWidth() {
		return size.x;
	}

	@Override
	public float getHeight() {
		return size.y;
	}

	@Override
	public Vector2 getVelocity() {
		return velocity;
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}
}
