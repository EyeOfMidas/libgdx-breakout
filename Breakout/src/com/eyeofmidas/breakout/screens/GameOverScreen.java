package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
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

	public GameOverScreen(final BreakoutGame game) {
		this.game = game;
		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/button-click.ogg"));
		shapeRenderer = new ShapeRenderer();
		playAgainStage = new PanelStage();

		playAgainButton = new DrawnTextButton("PLAY AGAIN?");
		playAgainButton.setSize(250, 60);

		BitmapFont headerFont = new BitmapFont(Gdx.files.internal("data/fonts/opensans-48-extrabold-gray.fnt"), false);
		BitmapFont scoreLabelFont = new BitmapFont(Gdx.files.internal("data/fonts/opensans-24-lightgray.fnt"), false);
		BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("data/fonts/opensans-60-gray.fnt"), false);

		headerLabel = new Label("CONGRATULATIONS!", new LabelStyle(headerFont, new Color(0.3f, 0.3f, 0.3f, 1)));
		scoreLabel =  new Label("YOUR\nSCORE", new LabelStyle(scoreLabelFont, new Color(0.7f, 0.7f, 0.7f, 1)));
		scoreLabel.setAlignment(Align.right | Align.bottom);
		scoreAmountLabel = new Label("1,000", new LabelStyle(scoreFont, new Color(0.3f, 0.3f, 0.3f, 1)));
		
		TextureAtlas iconAtlas = new TextureAtlas(Gdx.files.internal("data/category-icons.atlas"));
		Image icon = new Image(iconAtlas.createSprite("icons-focus-active"));

		table = new Table();
		table.add(headerLabel).colspan(2).padTop(60);
		table.row();
		table.add(scoreLabel).align(Align.right).padRight(5);
		table.add(scoreAmountLabel).align(Align.left).padLeft(5);
		table.row();
		table.add(icon).colspan(2);
		table.row();
		table.add(playAgainButton).colspan(2).spaceTop(60);
		table.setFillParent(true);
//		table.debug();

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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		playAgainStage.act(Gdx.graphics.getDeltaTime());
		playAgainStage.draw();
//		Table.drawDebug(playAgainStage);
	}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fit.apply(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, width, height);
		int viewportX = (int) (width - size.x) / 2;
		int viewportY = (int) (height - size.y) / 2;
		int viewportWidth = (int) size.x;
		int viewportHeight = (int) size.y;
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		playAgainStage.setViewport(BreakoutGame.WIDTH, BreakoutGame.HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);

	}

	@Override
	public void show() {
		playAgainStage.addActor(game.header);
		Gdx.input.setInputProcessor(playAgainStage);

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
		shapeRenderer.dispose();
		playAgainStage.dispose();
		// TODO Auto-generated method stub

	}

}
