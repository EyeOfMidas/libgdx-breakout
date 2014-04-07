package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.eyeofmidas.breakout.BreakoutGame;

public class LoadingScreen implements Screen {

	private BreakoutGame game;
	private ShapeRenderer shapeRenderer;
	private Stage stage;
	private float percent;
	private int loadingBarMaxWidth;
	private int loadingBarCenterY;
	private int loadingBarXOffset;

	public LoadingScreen(BreakoutGame game) {
		this.game = game;
		shapeRenderer = new ShapeRenderer();
		stage = new Stage();
		percent = 0f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (percent == 1.0f) {
			game.finishedLoading();
		}
		if (game.getAssetManager().update()) {
			percent = 1.0f;
		}
		shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1f, 1f, 1f, 1f);
		shapeRenderer.rect(loadingBarXOffset - 5, loadingBarCenterY - 5, loadingBarMaxWidth + 10, 60);
		shapeRenderer.setColor(0f, 0f, 0f, 1f);
		shapeRenderer.rect(loadingBarXOffset - 3, loadingBarCenterY - 3, loadingBarMaxWidth + 6, 56);

		shapeRenderer.setColor(1f, 1f, 1f, 1f);
		percent = Interpolation.linear.apply(percent, game.getAssetManager().getProgress(), 0.1f);
		shapeRenderer.rect(loadingBarXOffset, loadingBarCenterY, loadingBarMaxWidth * percent, 50);
		shapeRenderer.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		loadingBarMaxWidth = width / 2;
		loadingBarXOffset = width / 4;
		loadingBarCenterY = (height / 2) - 25;
	}

	@Override
	public void show() {

		// TODO: loading screen assets go here
		game.getAssetManager().finishLoading();

		// Game assets to load go here
		game.getAssetManager().load("category-icons.atlas", TextureAtlas.class);
		game.getAssetManager().load("fonts/opensans-48-extrabold-gray.fnt", BitmapFont.class);
		game.getAssetManager().load("fonts/opensans-24-lightgray.fnt", BitmapFont.class);
		game.getAssetManager().load("fonts/proxima-30-extrabold-white.fnt", BitmapFont.class);
		game.getAssetManager().load("fonts/opensans-60-gray.fnt", BitmapFont.class);
		game.getAssetManager().load("fonts/opensans-24-gray.fnt", BitmapFont.class);
		game.getAssetManager().load("fonts/dosis-30-white.fnt", BitmapFont.class);
		game.getAssetManager().load("button-click.ogg", Sound.class);
		game.getAssetManager().load("incorrect.ogg", Sound.class);
		game.getAssetManager().load("correct.ogg", Sound.class);
		game.getAssetManager().load("step.ogg", Sound.class);
		game.getAssetManager().load("shake.ogg", Sound.class);
		game.getAssetManager().load("click.ogg", Sound.class);
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
		stage.dispose();
		shapeRenderer.dispose();

	}

}
