package com.eyeofmidas.breakout.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
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
	private float lastVelocity;

	public PaddleActor(World world) {
		shapeRenderer = new ShapeRenderer();

		setColor(Color.WHITE);
		setSize(100, 20);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(30, 5);
		Body body = world.createBody(bodyDef);
		body.setLinearDamping(0.1f);
		PolygonShape paddleShape = new PolygonShape();
		Vector2[] vertexes = new Vector2[6];
		vertexes[0] = new Vector2(5f, -1f);
		vertexes[1] = new Vector2(5f, 0.8f);
		vertexes[2] = new Vector2(3f, 1f);
		vertexes[3] = new Vector2(-3f, 1f);
		vertexes[4] = new Vector2(-5f, 0.8f);
		vertexes[5] = new Vector2(-5f, -1f);
		paddleShape.set(vertexes);
		fixture = body.createFixture(paddleShape, 0.0f);

		paddleShape.dispose();
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
		fixture.getBody().setTransform(x / 10, getY(), 0);
	}

	public void act(float delta) {

	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() * 10 - getWidth() / 2, getY() * 10 - getHeight() / 2, 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		batch.begin();
	}

	public void reset() {
		fixture.getBody().setTransform(30, 5, 0);
	}

	public void moveLeft() {
		lastVelocity = -50f;
		fixture.getBody().setLinearVelocity(lastVelocity, 0);
	}

	public void moveRight() {
		lastVelocity = 50f;
		fixture.getBody().setLinearVelocity(lastVelocity, 0);
	}

	public void stop() {
		lastVelocity *= 0.9;
		fixture.getBody().setLinearVelocity(lastVelocity, 0);
	}
}
