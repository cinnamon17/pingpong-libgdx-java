package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Rectangle;

public class GameScreen implements Screen {
    private final GameHandler game;
    private Rectangle rectangle;
    private Ball ball;

    public GameScreen(final GameHandler game) {

        this.game = game;
        this.rectangle = new Rectangle(0, 0);
        this.ball = new Ball(400, 200);
    }

    @Override
    public void show() {

        Gdx.app.log("GameScreen.java", "show");
        game.setMusicLooping(true);
        game.musicPlay();
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
    }

    @Override
    public void hide() {
        game.musicPause();
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
        ball.update();
        rectangle.update();

        game.batchBegin();
        game.batchDrawBackground();
        game.batchDrawRectangle(rectangle);
        game.batchDrawBall(ball);
        game.batchEnd();

        ball.checkColision(rectangle);

    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
