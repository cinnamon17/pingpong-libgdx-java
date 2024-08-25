package com.pingpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pingpong.game.Screen.MainTitleScreen;

public class GameHandler extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public MainTitleScreen mainTitleScreen;

    public void create() {

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.mainTitleScreen = new MainTitleScreen(this);
        this.setScreen(mainTitleScreen);
    }

    public MainTitleScreen getMainTitleScreen() {
        return this.mainTitleScreen;
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
