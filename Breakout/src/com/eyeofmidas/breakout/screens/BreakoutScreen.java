package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.actors.BallActor;
import com.eyeofmidas.breakout.actors.BrickActor;
import com.eyeofmidas.breakout.actors.PaddleActor;
import com.eyeofmidas.breakout.collisions.BreakoutContactListener;
import com.eyeofmidas.breakout.collisions.HasContactListener;
import com.eyeofmidas.breakout.collisions.Wall;
import com.eyeofmidas.breakout.stages.BackgroundStage;

public class BreakoutScreen implements Screen {

	private BackgroundStage breakoutStage;
	private BallActor ball;
	private PaddleActor paddle;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private boolean debug = false;
	private boolean[] keys = new boolean[4];
	private BrickActor brick;
	private BreakoutContactListener contactListener;

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
		contactListener = new BreakoutContactListener();
		world.setContactListener(contactListener);
		debugRenderer = new Box2DDebugRenderer();

		ball = new BallActor(world);
		breakoutStage.addActor(ball);

		paddle = new PaddleActor(world);
		breakoutStage.addActor(paddle);

		brick = new BrickActor(world);
		brick.setPosition(5, 40);
		breakoutStage.addActor(brick);

		Wall leftWall = new Wall(world);
		leftWall.setPosition(-1.0f, 60f);
		leftWall.setSize(1.0f, 60f);
		leftWall.create();

		Wall rightWall = new Wall(world);
		rightWall.setPosition(81f, 60f);
		rightWall.setSize(1.0f, 60f);
		rightWall.create();

		Wall ceiling = new Wall(world);
		ceiling.setPosition(0f, 61f);
		ceiling.setSize(80f, 1.0f);
		ceiling.create();

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
		deleteDeadBodies();
	}

	private void deleteDeadBodies() {
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		Body node = bodies.pop();
		while (bodies.size > 0) {
			Body oBj = node;
			node = bodies.pop();
			if (((HasContactListener) oBj.getUserData()).isDying()) {
				removeBodySafely(oBj);
			}
		}
	}

	private void removeBodySafely(Body body) {
		final Array<JointEdge> list = body.getJointList();
		while (list.size > 0) {
			world.destroyJoint(list.get(0).joint);
		}
		world.destroyBody(body);
	}

	private void handlePaddleInput() {
		if (keys[0]) {
			paddle.moveLeft();
		} else if (keys[1]) {
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
