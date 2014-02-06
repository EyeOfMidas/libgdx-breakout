package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.actors.BallActor;
import com.eyeofmidas.breakout.actors.PaddleActor;

public class BreakoutScreen implements Screen {

	private Stage breakoutStage;
	private BallActor ball;
	private PaddleActor paddle;
	private World world;
	private Box2DDebugRenderer debugRenderer;

	public BreakoutScreen(final BreakoutGame game) {
		breakoutStage = new Stage();
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
				case Input.Keys.BACK:
				case Input.Keys.ESCAPE:
					game.endGame();
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
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		breakoutStage.act(Gdx.graphics.getDeltaTime());
		breakoutStage.draw();
		
		debugRenderer.render(world, breakoutStage.getCamera().combined);
				
		world.step(1/45f, 6, 2);
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
