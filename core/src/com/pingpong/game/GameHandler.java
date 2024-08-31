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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Paddle;
import com.pingpong.game.Input.PaddleInputProcessor;
import com.pingpong.game.Screen.GameScreen;
import com.pingpong.game.Screen.MainTitleScreen;
import com.pingpong.game.Actor.MainTitleMenu;

public class GameHandler extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private MainTitleScreen mainTitleScreen;
    private OrthographicCamera camera;
    private GameScreen gameScreen;
    private Music music;
    private Stage stage;
    private PaddleInputProcessor paddleInputProcessor;
    private InputMultiplexer inputMultiplexer;
    private MainTitleMenu mainTitleMenuButton;
    private TextureAtlas atlas;
    private Sprite rectangleSprite;
    private Sprite ballSprite;
    private Sprite backgroundSprite;
    private int scorePlayer;
    private int scoreEnemy;
    private Image paddleSprite;
    private Image background;
    private Image ball;

    public void create() {

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
        this.gameScreen = new GameScreen(this);
        this.mainTitleScreen = new MainTitleScreen(this);
        this.music = Gdx.audio.newMusic(Gdx.files.internal("mainMusic.wav"));
        this.stage = new Stage(new ScreenViewport());
        this.paddleInputProcessor = new PaddleInputProcessor(this,
                this.getGameScreen().getPaddle());
        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(this.getStage());
        this.inputMultiplexer.addProcessor(this.paddleInputProcessor);
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.mainTitleMenuButton = new MainTitleMenu(this);
        this.atlas = new TextureAtlas(Gdx.files.internal("ping_pong.atlas"));
        this.rectangleSprite = this.atlas.createSprite("paddleRed");
        this.ballSprite = this.atlas.createSprite("ballBlue");
        this.backgroundSprite = this.atlas.createSprite("background");
        this.setScreen(mainTitleScreen);
        this.paddleSprite = new Image(this.rectangleSprite);
        this.stage.addActor(paddleSprite);
        this.background = new Image(this.backgroundSprite);
        this.stage.addActor(background);
        this.background.setZIndex(0);
        this.background.setPosition(0, 0);
        this.ball = new Image(this.ballSprite);
        this.ball.setVisible(false);
        this.paddleSprite.setVisible(false);
        this.stage.addActor(this.ball);
    }

    public MainTitleScreen getMainTitleScreen() {
        return this.mainTitleScreen;
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public Stage getStage() {
        return this.stage;
    }

    public MainTitleMenu getMainTitleMenu() {
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

    public void batchDrawPaddle(Paddle rectangle) {
        this.paddleSprite.setPosition(rectangle.getX(), rectangle.getY());
        this.paddleSprite.draw(this.batch, 0);
    }

    public void batchDrawBall(Ball ball) {
        this.ball.setPosition(ball.getX(), ball.getY());
        this.ball.draw(this.batch, 0);
    }

    public void batchDrawBackground() {
        this.background.draw(this.batch, 0);
    }

    public void stageDraw() {
        this.stage.draw();
    }

    public void stageDispose() {
        this.stage.dispose();
    }

    public void createMainTitleButtonsEventListeners() {
        this.mainTitleMenuButton.create();
    }

    public void removeMainTitleButtonsEventListeners() {
        this.mainTitleMenuButton.removeEventListeners();
    }

    public int getScorePlayer() {
        return this.scorePlayer;
    }

    public int getScoreEnemy() {
        return this.scoreEnemy;
    }

    public void setScorePlayer(int score) {
        this.scorePlayer = score;
    }

    public void setScoreEnemy(int score) {
        this.scoreEnemy = score;
    }

    public Image getPaddleActor() {
        return this.paddleSprite;
    }

    public Image getBallActor() {
        return this.ball;
    }
}
