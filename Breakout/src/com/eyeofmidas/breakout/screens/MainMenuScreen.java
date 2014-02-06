package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.eyeofmidas.breakout.BreakoutGame;

public class MainMenuScreen implements Screen {

	private final BreakoutGame game;

	private Stage mainMenuStage;
	
	private Label gameLable;
	private TextButton playButton;

	public MainMenuScreen(final BreakoutGame game) {
		this.game = game;
		
		Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
		
		mainMenuStage = new Stage();
		gameLable = new Label("Breakout", skin);
		playButton = new TextButton("Play", skin);
		
		mainMenuStage.addActor(gameLable);
		mainMenuStage.addActor(playButton);
		
		gameLable.setAlignment(Align.center | Align.bottom);
				
		playButton.addListener(new InputListener() {
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		    	dispose();
		        game.startGame();
		    }
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mainMenuStage.act(Gdx.graphics.getDeltaTime());
		mainMenuStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mainMenuStage.setViewport(width, height, true);
		gameLable.setPosition(width / 2 - gameLable.getWidth() / 2, height / 2 - gameLable.getHeight() / 2 + 50);
		playButton.setPosition(width / 2 - playButton.getWidth() / 2, height / 2 - playButton.getHeight() / 2);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mainMenuStage);
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
