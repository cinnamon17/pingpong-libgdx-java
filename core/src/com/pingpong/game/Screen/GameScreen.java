package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;

public class GameScreen implements Screen {
    private final GameHandler game;

    public GameScreen(final GameHandler game) {
        this.game = game;
    }

    @Override
    public void show() {

        Gdx.app.log("GameScreen.java", "show");
        game.setMusic();
        game.setMultiplayerActorsVisible();
    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log("GameScreen.java", "resize method");
        Gdx.app.log("GameScreen.java", "width: " + width + " Height: " + height);
    }

    @Override
    public void resume() {

        Gdx.app.log("GameScreen.java", "resume method");
        game.musicPlay();
    }

    @Override
    public void dispose() {
        game.musicDispose();
        game.stageDispose();
    }

    @Override
    public void hide() {
        game.musicPause();
        game.hideMultiplayerActors();
        Gdx.app.log("GameScreen.java", "hide");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen.java", "pause");
        game.hideMultiplayerActors();
    }

    @Override
    public void render(float delta) {

        game.updateCamera();
        game.updateActors();
        game.updateMultiplayerCommunication();
        game.batchDrawMultiplayerActors();
        game.ballCheckCollision();

    }
}
