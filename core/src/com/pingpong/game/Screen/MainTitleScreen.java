package com.pingpong.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pingpong.game.GameHandler;

public class MainTitleScreen implements Screen {

    final GameHandler game;
    OrthographicCamera camera;

    public MainTitleScreen(final GameHandler game) {

        this.game = game;

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "hello to my first game developed with java", 10, 15);
        game.font.draw(game.batch, "Click or tap to start the game", 20, 45);
        game.batch.end();


        if (Gdx.input.isTouched()) {

            game.setScreen(new GameScreen(game));

        }

    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {


    }
    @Override
    public void resume() {

    }
}
