package com.eyeofmidas.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eyeofmidas.breakout.logics.Collideable;

public class PaddleEntity implements Collideable {

	private Vector2 size;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 drag;

	public PaddleEntity() {
		position = new Vector2(0, 0);
		size = new Vector2(100, 20);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		drag = new Vector2(0.90f, 0.90f);
	}
	
	@Override
	public float getX() {
		return position.x;
	}

	@Override
	public float getY() {
		return position.y;
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
	public void setPosition(Vector2 newPosition) {
		position = newPosition;

	}

	@Override
	public Vector2 getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;

	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public Color getColor() {
		return Color.WHITE;
	}

	public Rectangle getBounds() {
		return new Rectangle(position.x, position.y, size.x, size.y);
	}

	public void update(float delta) {
		acceleration.x = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
			if(acceleration.x > 0) {
				velocity.x = 0;
			}
			acceleration.x = -1.5f;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
			if(acceleration.x < 0) {
				velocity.x = 0;
			}
			acceleration.x = 1.5f;
		}
		
		velocity.add(acceleration);
		velocity.scl(drag);
		position.x += velocity.x;
		position.y += velocity.y;
	}
}
