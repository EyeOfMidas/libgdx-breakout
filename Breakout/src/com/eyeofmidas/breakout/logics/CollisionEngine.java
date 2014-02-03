package com.eyeofmidas.breakout.logics;

import com.badlogic.gdx.math.Rectangle;

public class CollisionEngine {
	public boolean collides(Rectangle obj1, Rectangle obj2) {
		return obj1.overlaps(obj2);
	}
	
	public Rectangle constrain(Rectangle obj1, Rectangle area) {
		Rectangle newPosition = new Rectangle();
		newPosition.x = obj1.getX();
		newPosition.y = obj1.getY();
		newPosition.width = obj1.getWidth();
		newPosition.height = obj1.getHeight();
		
		if(obj1.getX() < area.getX()) {
			newPosition.setX(area.getX());
		}
		if(obj1.getX() + obj1.getWidth() > area.getX() + area.getWidth()) {
			newPosition.setX(area.getX() - obj1.getWidth());
		}
		
		if(obj1.getY() < area.getY()) {
			newPosition.setY(area.y);
		}
		if(obj1.getY() + obj1.getHeight() > area.getY() + area.getHeight()) {
			newPosition.setY(area.getY() - obj1.getHeight());
		}
		
		return newPosition;
		
	}
}
