package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;

public class MainTitleScreen implements Screen {

	final GameHandler game;

	public MainTitleScreen(final GameHandler game) {
		this.game = game;
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("MainTitleScreen.java", "resize method");
	}

	@Override
	public void pause() {
		Gdx.app.log("MainTitleScreen.java", "pause method");
	}

	@Override
	public void show() {

		this.game.createMainTitleButtons();
		Gdx.app.log("MainTitleScreen.java", "show method");
	}

	@Override
	public void render(float delta) {
		this.game.clearScreen();
		this.game.stageDraw();
	}

	@Override
	public void hide() {
		Gdx.app.log("MainTitleScreen.java", "hide method");
	}

	@Override
	public void dispose() {
		Gdx.app.log("MainTitleScreen.java", "dispose method");
		this.game.stageDispose();
	}

	@Override
	public void resume() {
		Gdx.app.log("MainTitleScreen.java", "resume method");
	}
}
