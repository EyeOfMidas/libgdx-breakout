package com.eyeofmidas.breakout.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BallEntity {
	private Vector2 position;
	private Vector2 size;
	private Vector2 velocity;
	private Vector2 acceleration;
	public BallEntity() {
		position.x = 50;
		position.y = 50;
		size.x = 50;
		size.y = 50;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 0;
	}
	
	public void update(float delta) {
		velocity.add(acceleration);
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
		return new Rectangle(position.x, position.y, size.x, size.y);
	}

	public Color getColor() {
		return Color.WHITE;
	}
}
