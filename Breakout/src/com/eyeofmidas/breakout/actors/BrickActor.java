package com.eyeofmidas.breakout.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import com.eyeofmidas.breakout.collisions.Collideable;

public class BrickActor extends Actor implements Collideable {
	private ShapeRenderer shapeRenderer;
	private Fixture fixture;
	private boolean isDying = false;
	private Sound hitSound;

	public BrickActor(World world) {
		shapeRenderer = new ShapeRenderer();
		hitSound = Gdx.audio.newSound(Gdx.files.internal("data/shake.ogg"));
		setColor(Color.WHITE);
		setSize(50, 20);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(10, 5);
		Body body = world.createBody(bodyDef);
		PolygonShape brickShape = new PolygonShape();
		brickShape.setAsBox(2.5f, 1f);
		body.setUserData(this);
		fixture = body.createFixture(brickShape, 0.0f);

		brickShape.dispose();
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() * 10 - getWidth() / 2, getY() * 10 - getHeight() / 2, 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(getColor());
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		batch.begin();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		fixture.getBody().setTransform(x, y, 0);
	}

	@Override
	public void contact(Collideable other) {
		isDying = true;
		this.remove();
	}

	@Override
	public boolean isDying() {
		return isDying;
	}

	@Override
	public void playHitSound() {
		hitSound.play();
	}
}
