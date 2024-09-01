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
        game.setMusicLooping(true);
        game.musicPlay();
        game.getPaddleActor().setVisible(true);
        game.getBallActor().setVisible(true);
        game.getScoreActor().setVisible(true);
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
        game.getPaddleActor().setVisible(false);
        game.getBallActor().setVisible(false);
        game.getScoreActor().setVisible(false);
        Gdx.app.log("GameScreen.java", "hide");
    }

    @Override
    public void pause() {

        Gdx.app.log("GameScreen.java", "pause");

    }

    @Override
    public void render(float delta) {

        game.clearScreen();
        game.cameraUpdate();
        game.setProjectionMatrixCombined();
        game.stageDraw();
        game.ballUpdate();
        game.scoreUpdate();
        game.paddleUpdate();

        game.batchBegin();
        game.batchDrawScore();
        game.batchDrawBackground();
        game.batchDrawPaddle();
        game.batchDrawBall();
        game.batchEnd();

        game.ballCheckCollision();

    }
}
