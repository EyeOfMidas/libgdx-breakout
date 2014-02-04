package com.eyeofmidas.breakout.screens;

import com.badlogic.gdx.InputProcessor;
import com.eyeofmidas.breakout.entities.PaddleEntity;

public class PaddleInputProcessor implements InputProcessor {

	
	private PaddleEntity paddle;

	public void registerPaddle(PaddleEntity paddle) {
		this.paddle = paddle;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		paddle.setCenterPosition(x, y);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		paddle.setCenterPosition(x, y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		paddle.setCenterPosition(screenX, screenY);
		return false;
	}
}
