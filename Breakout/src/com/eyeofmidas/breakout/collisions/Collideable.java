package com.eyeofmidas.breakout.collisions;

public interface Collideable {
	public boolean isDying();
	public void contact(Collideable other);
}
