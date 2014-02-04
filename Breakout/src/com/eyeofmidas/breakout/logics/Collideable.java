package com.eyeofmidas.breakout.logics;

import com.badlogic.gdx.math.Vector2;

public interface Collideable {
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
	public void setPosition(Vector2 newPosition);
	public Vector2 getVelocity();
	public void setVelocity(float x, float y);
	public Vector2 getPosition();
	public void setPosition(float x, float y);
}
