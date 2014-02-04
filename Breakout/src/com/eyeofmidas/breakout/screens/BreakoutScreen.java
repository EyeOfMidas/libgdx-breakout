package com.eyeofmidas.breakout.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.entities.BallEntity;
import com.eyeofmidas.breakout.logics.CollisionEngine;
import com.eyeofmidas.breakout.renderers.BallRenderer;

public class BreakoutScreen implements Screen {
	private PerspectiveCamera camera;
	private BreakoutGame game;
	private BallRenderer ballRenderer;
	private CollisionEngine collisionEngine;
	private Rectangle screenBounds = new Rectangle(0, 0, 800, 600);
	
	private ArrayList<BallEntity> balls = new ArrayList<BallEntity>();
	
	public BreakoutScreen(BreakoutGame game) {
		this.game = game;
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		Gdx.app.log("SCREEN", width + " x " + height);
		camera = new PerspectiveCamera(45f, width, height);
		camera.position.set(400f,300f,725f);
		camera.near = 0.1f;
		camera.far = 1001f;
		camera.lookAt(400f, 300f, 0f);
		camera.update();
		
		ballRenderer = new BallRenderer();
		collisionEngine = new CollisionEngine();
		
		BallEntity ball = new BallEntity();
		ball.setVelocity(1f, 11f);
		ball.setAcceleration(0f, -0.1f);
		balls.add(ball);
		ball = new BallEntity();
		ball.setPosition(500f, 400f);
		ball.setAcceleration(0f, -0.1f);
		balls.add(ball);
	}
	
	public void update(float delta) {
		camera.update();
		for(BallEntity ball : balls) {
			ball.update(delta);
			collisionEngine.constrain(ball, screenBounds);
			
		}
		ballRenderer.updateBalls(balls);
	}

	@Override
	public void render(float delta) {
		this.update(delta);
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glViewport(0, 0, width, height);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		ballRenderer.render(null, camera);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
