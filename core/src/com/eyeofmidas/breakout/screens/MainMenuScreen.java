package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.ui.DrawnTextButton;
import com.eyeofmidas.utils.Console;

public class MainMenuScreen implements Screen {

	private Stage mainMenuStage;

	private Label gameLabel;
	private DrawnTextButton playButton;
	private DrawnTextButton howToPlayButton;
	private Table table;
	private BreakoutGame game;
	private Sound clickSound;

	public MainMenuScreen(final BreakoutGame game) {
		this.game = game;

		clickSound = game.getAssetManager().get("button-click.ogg", Sound.class);

		TextureAtlas iconAtlas = game.getAssetManager().get("category-icons.atlas", TextureAtlas.class);
		Image icon = new Image(iconAtlas.createSprite("icons-focus-active"));

		BitmapFont font = game.getAssetManager().get("fonts/proxima-30-extrabold-white.fnt", BitmapFont.class);

		gameLabel = new Label("Breakout Level 1", new LabelStyle(font, new Color(1, 1, 1, 1)));

		playButton = new DrawnTextButton("START GAME", game.getAssetManager().get("fonts/dosis-30-white.fnt", BitmapFont.class));
		playButton.setSize(250, 60);
		howToPlayButton = new DrawnTextButton("HOW TO PLAY", game.getAssetManager().get("fonts/dosis-30-white.fnt", BitmapFont.class));
		howToPlayButton.setSize(250, 60);

		mainMenuStage = new Stage();
		table = new Table();
		table.add(gameLabel).colspan(2);
		table.row();
		table.add(icon).colspan(2).pad(80);
		table.row();
		table.add(howToPlayButton).spaceRight(40);
		table.add(playButton).spaceLeft(40);
		table.setFillParent(true);
		// table.debug();

		mainMenuStage.addActor(table);

		gameLabel.setAlignment(Align.center | Align.bottom);

		playButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play();
				game.startGame();
			}
		});

		howToPlayButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play();
			}
		});

		mainMenuStage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keyCode) {
				switch (keyCode) {
				case Input.Keys.BACK:
				case Input.Keys.ESCAPE:
					Gdx.app.exit();
					break;
				}
				return false;
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1137f, 0.16f, 0.145f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainMenuStage.act(Gdx.graphics.getDeltaTime());
		mainMenuStage.draw();
		// Table.drawDebug(mainMenuStage);
	}

	@Override
	public void resize(int width, int height) {
		mainMenuStage.getViewport().update(800, 600, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mainMenuStage);
		Gdx.input.setCatchBackKey(false);
		mainMenuStage.addActor(game.header);
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
		clickSound.dispose();

	}

}
