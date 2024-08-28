package com.pingpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Rectangle;
import com.pingpong.game.Input.RectangleInputProcessor;
import com.pingpong.game.Screen.GameScreen;
import com.pingpong.game.Screen.MainTitleScreen;
import com.pingpong.game.Widget.MainTitleMenuButton;

public class GameHandler extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private MainTitleScreen mainTitleScreen;
    private OrthographicCamera camera;
    private GameScreen gameScreen;
    private Music music;
    private Stage mainTitleStage;
    private RectangleInputProcessor rectangleInputProcessor;
    private InputMultiplexer inputMultiplexer;
    private MainTitleMenuButton mainTitleMenuButton;
    private TextureAtlas atlas;
    private Sprite rectangleSprite;
    private Sprite ballSprite;
    private Sprite backgroundSprite;

    public void create() {

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
        this.gameScreen = new GameScreen(this);
        this.mainTitleScreen = new MainTitleScreen(this);
        this.music = Gdx.audio.newMusic(Gdx.files.internal("mainMusic.wav"));
        this.mainTitleStage = new Stage(new ScreenViewport());
        this.rectangleInputProcessor = new RectangleInputProcessor(this,
                this.getGameScreen().getRectangle());
        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(this.getMainTitleStage());
        this.inputMultiplexer.addProcessor(this.rectangleInputProcessor);
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.mainTitleMenuButton = new MainTitleMenuButton(this);
        this.atlas = new TextureAtlas(Gdx.files.internal("ping_pong.atlas"));
        this.rectangleSprite = this.atlas.createSprite("paddleRed");
        this.ballSprite = this.atlas.createSprite("ballBlue");
        this.backgroundSprite = this.atlas.createSprite("background");
        this.setScreen(mainTitleScreen);
    }

    public MainTitleScreen getMainTitleScreen() {
        return this.mainTitleScreen;
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public Stage getMainTitleStage() {
        return this.mainTitleStage;
    }

    public MainTitleMenuButton getMainTitleMenuButton() {
        return this.mainTitleMenuButton;
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public void batchBegin() {
        this.batch.begin();
    }

    public void batchEnd() {
        this.batch.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public void musicPlay() {
        this.music.play();
    }

    public void musicPause() {
        this.music.pause();
    }

    public void setMusicLooping(boolean b) {
        this.music.setLooping(b);
    }

    public void musicDispose() {
        this.music.dispose();
    }

    public void cameraUpdate() {
        this.camera.update();
    }

    public void setProjectionMatrixCombined() {
        this.batch.setProjectionMatrix(this.getCamera().combined);
    }

    public void batchDrawRectangle(Rectangle rectangle) {
        this.batch.draw(this.rectangleSprite, rectangle.getX(), rectangle.getY());
    }

    public void batchDrawBall(Ball ball) {
        this.batch.draw(this.ballSprite, ball.getX(), ball.getY());
    }

    public void batchDrawBackground() {
        this.batch.draw(this.backgroundSprite, 0, 0);
    }

    public void stageDraw() {
        this.mainTitleStage.draw();
    }

    public void stageDispose() {
        this.mainTitleStage.dispose();
    }

    public void createMainTitleButtons() {
        this.mainTitleMenuButton.create();
    }
}
