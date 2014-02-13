package com.eyeofmidas.breakout.collisions;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall implements HasContactListener{
	private BodyDef bodyDef;
	private Body body;
	private PolygonShape rectangle;
	private World world;

	public Wall(World world) {
		this.world = world;
	}
	
	public void setPosition(float x, float y) {
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y);
		body = world.createBody(bodyDef);
	}
	
	public void setSize(float width, float height) {
		rectangle = new PolygonShape();
		rectangle.setAsBox(width, height);
	}
	
	public void create() {
		body.createFixture(rectangle, 0.0f);
		body.setUserData(this);
		rectangle.dispose();
	}

	@Override
	public void contact(HasContactListener other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDying() {
		return false;
	}
}
