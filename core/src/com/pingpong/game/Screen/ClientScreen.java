package com.pingpong.game.Screen;

import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;

/**
 * ClientScreen
 */
public class ClientScreen implements Screen {
    private GameHandler game;

    public ClientScreen(final GameHandler game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.getWindowInput().setVisible(true);
    }

    @Override
    public void hide() {

        game.getWindowInput().setVisible(false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        game.stageDraw();
    }
}
