package com.eyeofmidas.breakout.logics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionEngine {
	public boolean collides(Rectangle obj1, Rectangle obj2) {
		return obj1.overlaps(obj2);
	}
	
	public void constrain(Collideable obj1, Rectangle area) {
		Vector2 position = obj1.getPosition();
		Vector2 velocity = obj1.getVelocity();
		if(obj1.getX() < area.getX()) {
			obj1.setPosition(area.getX(), position.y);
			obj1.setVelocity(-1 * velocity.x, velocity.y);
		} else if(position.x + obj1.getWidth() > area.getWidth() + area.getX()) {
			obj1.setPosition(area.getWidth() + area.getX() - obj1.getWidth(), position.y);
			obj1.setVelocity(-1 * velocity.x, velocity.y);
		}
		if(obj1.getY() < area.getY()) {
			obj1.setPosition(position.x, area.getY());
			obj1.setVelocity(velocity.x, -1 * velocity.y);
		} else if(position.y + obj1.getHeight() > area.getHeight() + area.getY()) {
			obj1.setPosition(position.x, area.getHeight() + area.getY() - obj1.getHeight());
			obj1.setVelocity(velocity.x, -1 * velocity.y);
		}
	}
}
