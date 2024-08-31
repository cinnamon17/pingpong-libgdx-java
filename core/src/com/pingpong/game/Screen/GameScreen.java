package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Paddle;
import com.pingpong.game.Actor.Score;

public class GameScreen implements Screen {
    private final GameHandler game;
    private Paddle rectangle;
    private Ball ball;
    private Score score;

    public GameScreen(final GameHandler game) {

        this.game = game;
        this.rectangle = new Paddle(0, 0);
        this.ball = new Ball(400, 200);
        this.ball.setPaddle(this.rectangle);
    }

    @Override
    public void show() {

        this.score = new Score(game);
        Gdx.app.log("GameScreen.java", "show");
        game.setMusicLooping(true);
        game.musicPlay();
        this.score.show();
        game.getPaddleActor().setVisible(true);
        game.getBallActor().setVisible(true);
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
        this.score.hide();
        game.musicPause();
        game.getPaddleActor().setVisible(false);
        game.getBallActor().setVisible(false);
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
        ball.update();
        rectangle.update();

        game.batchBegin();
        game.batchDrawBackground();
        game.batchDrawPaddle(rectangle);
        game.batchDrawBall(ball);
        game.batchEnd();

        ball.checkColision();

    }

    public Paddle getPaddle() {
        return this.rectangle;
    }
}
