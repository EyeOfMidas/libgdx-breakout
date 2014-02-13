package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Scaling;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.actors.BallActor;
import com.eyeofmidas.breakout.actors.PaddleActor;
import com.eyeofmidas.breakout.stages.BackgroundStage;
import com.eyeofmidas.utils.Console;

public class BreakoutScreen implements Screen {

	private BackgroundStage breakoutStage;
	private BallActor ball;
	private PaddleActor paddle;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private boolean debug = false;
	private boolean[] keys = new boolean[4];

	public BreakoutScreen(final BreakoutGame game) {
		breakoutStage = new BackgroundStage();
		breakoutStage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				paddle.setX(x);
			}

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				paddle.setX(x);
				return true;
			}

			@Override
			public boolean keyDown(InputEvent event, int keyCode) {
				switch (keyCode) {
				case Input.Keys.A:
				case Input.Keys.LEFT:
					keys[0] = true;
					break;
				case Input.Keys.D:
				case Input.Keys.RIGHT:
					keys[1] = true;
					break;
				case Input.Keys.BACK:
				case Input.Keys.ESCAPE:
					game.endGame();
					break;
				}
				return false;
			}

			@Override
			public boolean keyUp(InputEvent event, int keyCode) {
				switch (keyCode) {
				case Input.Keys.A:
				case Input.Keys.LEFT:
					keys[0] = false;
					break;
				case Input.Keys.D:
				case Input.Keys.RIGHT:
					keys[1] = false;
					break;
				}
				return false;
			}
		});
		world = new World(new Vector2(0f, 0f), true);
		debugRenderer = new Box2DDebugRenderer();

		ball = new BallActor(world);
		breakoutStage.addActor(ball);

		paddle = new PaddleActor(world);
		breakoutStage.addActor(paddle);

		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(-1.0f, 60);

		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(1.0f, 60);
		groundBody.createFixture(groundBox, 0.0f);

		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(81f, 60f);

		groundBody = world.createBody(groundBodyDef);

		groundBox = new PolygonShape();
		groundBox.setAsBox(1.0f, 60);
		groundBody.createFixture(groundBox, 0.0f);

		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0f, 61f);

		groundBody = world.createBody(groundBodyDef);

		groundBox = new PolygonShape();
		groundBox.setAsBox(80, 1.0f);
		groundBody.createFixture(groundBox, 0.0f);

		groundBox.dispose();

		this.reset();
	}

	public void reset() {
		ball.reset();
		paddle.reset();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		handlePaddleInput();
		breakoutStage.act(Gdx.graphics.getDeltaTime());
		breakoutStage.draw();

		if (debug) {
			debugRenderer.render(world, breakoutStage.getCamera().combined.scl(10f, 10f, 1f));
		}
		world.step(1 / 45f, 6, 2);
	}

	private void handlePaddleInput() {
		if(keys[0]) {
			Console.log("moving left");
			paddle.moveLeft();
		} else if (keys[1]) {
			Console.log("moving right");
			paddle.moveRight();
		} else {
			paddle.stop();
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fit.apply(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, width, height);
		int viewportX = (int) (width - size.x) / 2;
		int viewportY = (int) (height - size.y) / 2;
		int viewportWidth = (int) size.x;
		int viewportHeight = (int) size.y;
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		breakoutStage.setViewport(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(breakoutStage);
		Gdx.input.setCatchBackKey(true);
		this.reset();
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
		breakoutStage.dispose();
	}

}
