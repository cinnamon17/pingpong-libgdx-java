package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Rectangle;

public class GameScreen implements Screen {
    public final GameHandler game;
    private Music music;
    private OrthographicCamera camera;
    private Rectangle rectangle;
    private Ball ball;

    public GameScreen(final GameHandler game) {

        this.game = game;
        this.music = Gdx.audio.newMusic(Gdx.files.internal("mainMusic.wav"));
        this.camera = new OrthographicCamera();
        this.rectangle = new Rectangle(0, 0);
        this.ball = new Ball(400, 5, 200, 5);
        this.camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log("GameScreen.java", "resize method");
    }

    @Override
    public void resume() {

        Gdx.app.log("GameScreen.java", "resume method");
        music.play();
    }

    @Override
    public void dispose() {
        music.dispose();
    }

    @Override
    public void hide() {

        music.pause();
        Gdx.app.log("GameScreen.java", "hide");
    }

    @Override
    public void pause() {

        Gdx.app.log("GameScreen.java", "pause");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ball.update();

        game.batch.begin();
        rectangle.draw(game.batch);
        ball.draw(game.batch);
        game.batch.end();

        music.setLooping(true);
        music.play();

        rectangle.update();
        ball.checkColision(rectangle, rectangle.getX(), rectangle.getY());

    }

    @Override
    public void show() {

        Gdx.app.log("GameScreen.java", "show");
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
