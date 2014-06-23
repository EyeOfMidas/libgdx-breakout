package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.eyeofmidas.breakout.BreakoutGame;
import com.eyeofmidas.breakout.stages.PanelStage;
import com.eyeofmidas.breakout.ui.DrawnTextButton;

public class GameOverScreen implements Screen {

	private ShapeRenderer shapeRenderer;
	private PanelStage playAgainStage;
	private BreakoutGame game;
	private DrawnTextButton playAgainButton;
	private Table table;
	private Sound clickSound;
	private Label headerLabel;
	private Label scoreLabel;
	private Label scoreAmountLabel;
	private Label gameNameLabel;
	private Label playingLabel;

	public GameOverScreen(final BreakoutGame game) {
		this.game = game;

		clickSound = game.getAssetManager().get("button-click.ogg", Sound.class);
		shapeRenderer = new ShapeRenderer();
		playAgainStage = new PanelStage();

		playAgainButton = new DrawnTextButton("PLAY AGAIN?", game.getAssetManager().get("fonts/dosis-30-white.fnt", BitmapFont.class));
		playAgainButton.setSize(250, 60);

		BitmapFont headerFont = game.getAssetManager().get("fonts/opensans-48-extrabold-gray.fnt", BitmapFont.class);
		BitmapFont scoreLabelFont = game.getAssetManager().get("fonts/opensans-24-lightgray.fnt", BitmapFont.class);
		BitmapFont scoreFont = game.getAssetManager().get("fonts/opensans-60-gray.fnt", BitmapFont.class);
		BitmapFont labelFont = game.getAssetManager().get("fonts/opensans-24-gray.fnt", BitmapFont.class);

		headerLabel = new Label("CONGRATULATIONS!", new LabelStyle(headerFont, new Color(0.3f, 0.3f, 0.3f, 1)));
		scoreLabel = new Label("YOUR\nSCORE", new LabelStyle(scoreLabelFont, new Color(0.7f, 0.7f, 0.7f, 1)));
		scoreLabel.setAlignment(Align.right | Align.bottom);
		scoreAmountLabel = new Label("1,000", new LabelStyle(scoreFont, new Color(0.3f, 0.3f, 0.3f, 1)));
		playingLabel = new Label("Currently playing:", new LabelStyle(labelFont, new Color(0.3f, 0.3f, 0.3f, 1)));
		gameNameLabel = new Label("Breakout Level 1", new LabelStyle(labelFont, new Color(0.3f, 0.3f, 0.3f, 1)));

		TextureAtlas iconAtlas = game.getAssetManager().get("category-icons.atlas", TextureAtlas.class);
		Image icon = new Image(iconAtlas.createSprite("icons-focus-active"));

		table = new Table();
		table.setFillParent(true);
		table.add(headerLabel).colspan(2).padTop(50);
		table.row();
		table.add(scoreLabel).align(Align.right).padRight(5);
		table.add(scoreAmountLabel).align(Align.left).padLeft(5);
		table.row();
		table.add(playingLabel).colspan(2);
		table.row();
		table.add(icon).colspan(2);
		table.row();
		table.add(gameNameLabel).colspan(2);
		table.row();
		table.add(playAgainButton).colspan(2);

		playAgainStage.addActor(table);

		playAgainButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play();
				game.startGame();
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1137f, 0.16f, 0.145f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playAgainStage.act(Gdx.graphics.getDeltaTime());
		playAgainStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		playAgainStage.getViewport().update(width, height, true);

	}

	@Override
	public void show() {
		playAgainStage.addActor(game.header);
		Gdx.input.setInputProcessor(playAgainStage);

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
		shapeRenderer.dispose();
		playAgainStage.dispose();
	}

}
