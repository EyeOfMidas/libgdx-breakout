package com.eyeofmidas.breakout.collisions;

public interface HasContactListener {
	public boolean isDying();
	public void contact(HasContactListener other);
}
