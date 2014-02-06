package com.eyeofmidas.breakout.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PaddleActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private Fixture fixture;

	public PaddleActor(World world) {
		shapeRenderer = new ShapeRenderer();

		setColor(Color.WHITE);
		setSize(100, 20);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(300, 50);
		Body body = world.createBody(bodyDef);
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(50, 10);
		fixture = body.createFixture(rectangle, 0.0f);

		rectangle.dispose();
	}

	@Override
	public float getX() {
		return fixture.getBody().getPosition().x;
	}

	@Override
	public float getY() {
		return fixture.getBody().getPosition().y;
	}

	@Override
	public void setX(float x) {
		fixture.getBody().setTransform(x, getY(), 0);
	}

	public void act(float delta) {

	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() - getWidth() / 2, getY() - getHeight() / 2, 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		batch.begin();
	}
}
