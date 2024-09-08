package com.pingpong.game.Screen;

import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;

/**
 * ServerScreen
 */
public class ServerScreen implements Screen {

    private GameHandler game;

    public ServerScreen(final GameHandler game) {

        this.game = game;
    }

    @Override
    public void show() {

        game.getMultiplayerLabel().setVisible(true);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {
        game.stageDraw();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

        game.getMultiplayerLabel().setVisible(false);
    }

    @Override
    public void dispose() {

    }

}
