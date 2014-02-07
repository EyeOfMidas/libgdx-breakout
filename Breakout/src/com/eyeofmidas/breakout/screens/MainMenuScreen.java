package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.stages.BackgroundStage;

public class MainMenuScreen implements Screen {

	private BackgroundStage mainMenuStage;

	private Label gameLabel;
	private TextButton playButton;

	public MainMenuScreen(final BreakoutGame game) {
		Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		mainMenuStage = new BackgroundStage();
		gameLabel = new Label("Breakout", skin);
		playButton = new TextButton("Play", skin);

		mainMenuStage.addActor(gameLabel);
		mainMenuStage.addActor(playButton);

		gameLabel.setAlignment(Align.center | Align.bottom);

		playButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.startGame();
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mainMenuStage.act(Gdx.graphics.getDeltaTime());
		mainMenuStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fit.apply(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, width, height);
		int viewportX = (int) (width - size.x) / 2;
		int viewportY = (int) (height - size.y) / 2;
		int viewportWidth = (int) size.x;
		int viewportHeight = (int) size.y;
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		mainMenuStage.setViewport(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);

		gameLabel.setPosition(BreakoutGame.WIDTH / 2 - gameLabel.getWidth() / 2, BreakoutGame.HEIGHT / 2 - gameLabel.getHeight() / 2 + 50);
		playButton.setPosition(BreakoutGame.WIDTH / 2 - playButton.getWidth() / 2, BreakoutGame.HEIGHT / 2 - playButton.getHeight() / 2);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mainMenuStage);
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		mainMenuStage.dispose();

	}

}
