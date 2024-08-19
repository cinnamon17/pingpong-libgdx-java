package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        this.rectangle = new Rectangle(0, 0, 0, 0);
        this.ball = new Ball(400, 5, 200, 5);
        camera.setToOrtho(false, 800, 480);
    }
    @Override
    public void resize(int width, int height) {

        System.out.println("resize");
    }
    @Override
    public void resume() {

        System.out.println("resume");
        music.play();
    }
    @Override
    public void dispose() {

        music.dispose();

    }
    @Override
    public void hide() {

        System.out.println("hide");
    }
    @Override
    public void pause() {

        System.out.println("pause");

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ball.update();
        rectangle.update();

        game.batch.begin();
        rectangle.draw(game.batch);
        ball.draw(game.batch);
        game.batch.end();

        music.setLooping(true);
        music.play();

        ball.checkColision(rectangle, rectangle.getX(), rectangle.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            rectangle.setX(rectangle.getX() - 5);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

            rectangle.setY(rectangle.getY() + 5);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            rectangle.setX(rectangle.getX() + 5);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            rectangle.setY(rectangle.getY() - 5);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.game.setScreen(new MainTitleScreen(this.game));
        }

    }
    @Override
    public void show() {

        System.out.println("show");
    }
}
