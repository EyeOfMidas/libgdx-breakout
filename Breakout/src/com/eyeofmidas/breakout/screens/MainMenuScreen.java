package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.utils.AlignedBitmapFont;
import com.eyeofmidas.utils.AlignedBitmapFont.FontAlign;

public class MainMenuScreen implements Screen {

	private BreakoutGame game;
	private SpriteBatch batch;
	private AlignedBitmapFont font;

	private OrthographicCamera camera;

	public MainMenuScreen(BreakoutGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new AlignedBitmapFont();
		font.setScale(2);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "Welcome to Breakout!", width / 2, height / 2 + height / 16, FontAlign.CENTER);
		font.draw(batch, "Tap anywhere to begin!", width / 2, height / 2, FontAlign.CENTER);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.startGame();
			dispose();
		}
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
		batch.dispose();
		font.dispose();

	}

}
