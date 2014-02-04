package com.eyeofmidas.breakout.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.entities.BallEntity;
import com.eyeofmidas.breakout.entities.PaddleEntity;
import com.eyeofmidas.breakout.logics.CollisionEngine;
import com.eyeofmidas.breakout.renderers.BallRenderer;
import com.eyeofmidas.breakout.renderers.PaddleRenderer;

public class BreakoutScreen implements Screen {
	private PerspectiveCamera camera;
	private BreakoutGame game;
	private BallRenderer ballRenderer;
	private PaddleRenderer paddleRenderer;
	private CollisionEngine collisionEngine;
	private Rectangle screenBounds;
	
	private ArrayList<BallEntity> balls = new ArrayList<BallEntity>();
	private ArrayList<PaddleEntity> paddles = new ArrayList<PaddleEntity>();
	
	public BreakoutScreen(BreakoutGame game) {
		this.game = game;
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		screenBounds = new Rectangle(0,0,width, height);
		float fieldOfView = height/width * 90;
		camera = new PerspectiveCamera(fieldOfView, width, height);
		float cameraDistance = (height / 2) / (float)Math.tan((fieldOfView / 2)* MathUtils.degreesToRadians);
		camera.position.set(width/2,height/2, cameraDistance);
		camera.near = 0.1f;
		camera.far = 10000f;
		camera.lookAt(width / 2, height / 2, 0f);
		camera.update();
		
		
		
		ballRenderer = new BallRenderer();
		paddleRenderer = new PaddleRenderer();
		collisionEngine = new CollisionEngine();
		
		BallEntity ball = new BallEntity();
		ball.setVelocity(1f, 11f);
		ball.setAcceleration(0f, -0.1f);
		balls.add(ball);
		ball = new BallEntity();
		ball.setPosition(500f, 400f);
		ball.setAcceleration(0f, -0.1f);
		balls.add(ball);
		
		PaddleInputProcessor paddleInputProcessor = new PaddleInputProcessor();
		Gdx.input.setInputProcessor(paddleInputProcessor);
		
		PaddleEntity paddle = new PaddleEntity();
		paddle.setPosition(3 * width / 5, 1 * height / 10);
		paddles.add(paddle);
	}
	
	public void update(float delta) {
		camera.update();
		for(BallEntity ball : balls) {
			ball.update(delta);
			collisionEngine.constrain(ball, screenBounds);
			
		}
		ballRenderer.updateBalls(balls);
		for(PaddleEntity paddle : paddles) {
			paddle.update(delta);
			collisionEngine.constrain(paddle, screenBounds);
			
		}
		paddleRenderer.updatePaddles(paddles);
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
		paddleRenderer.render(null, camera);
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
