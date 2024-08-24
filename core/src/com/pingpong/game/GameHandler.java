package com.pingpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pingpong.game.Screen.MainTitleScreen;

public class GameHandler extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainTitleScreen(this));
    }

    public void render() {

        super.render();
    }

    public void dispose() {

        batch.dispose();
        font.dispose();
    }
}
