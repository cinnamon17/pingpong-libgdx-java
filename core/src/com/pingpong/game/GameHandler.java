package com.pingpong.game;

import java.io.InputStream;
import java.io.OutputStream;

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
import com.pingpong.game.Actor.Score;
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
    private Sprite paddleSprite;
    private Sprite paddleEnemySprite;
    private Sprite ballSprite;
    private Sprite backgroundSprite;
    private int scorePlayer = 0;
    private int scoreEnemy = 0;
    private Paddle paddle;
    private Paddle paddleEnemy;
    private Image background;
    private Ball ball;
    private Score score;
    private InputStream serverInputStream;
    private OutputStream serverOutputStream;
    private InputStream clientInputStream;
    private OutputStream clientOutputStream;
    private boolean isServer;

    public void create() {

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.atlas = new TextureAtlas(Gdx.files.internal("ping_pong.atlas"));
        this.paddleSprite = this.atlas.createSprite("paddleRed");
        this.paddleEnemySprite = this.atlas.createSprite("paddleBlu");
        this.paddle = new Paddle(this.paddleSprite, 0, 0);
        this.paddleEnemy = new Paddle(this.paddleEnemySprite, 400, 456);
        this.ballSprite = this.atlas.createSprite("ballBlue");
        this.backgroundSprite = this.atlas.createSprite("background");
        this.camera.setToOrtho(false, 800, 480);
        this.gameScreen = new GameScreen(this);
        this.mainTitleScreen = new MainTitleScreen(this);
        this.music = Gdx.audio.newMusic(Gdx.files.internal("mainMusic.wav"));
        this.stage = new Stage(new ScreenViewport());
        this.paddleInputProcessor = new PaddleInputProcessor(this, this.paddle);
        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(this.getStage());
        this.inputMultiplexer.addProcessor(this.paddleInputProcessor);
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.mainTitleMenuButton = new MainTitleMenu(this);
        this.stage.addActor(paddle);
        this.stage.addActor(paddleEnemy);
        this.background = new Image(this.backgroundSprite);
        this.stage.addActor(background);
        this.background.setZIndex(0);
        this.background.setPosition(0, 0);
        this.ball = new Ball(this.ballSprite);
        this.score = new Score(this);
        this.stage.addActor(this.ball);
        this.stage.addActor(this.score);
        this.setScreen(mainTitleScreen);
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

    public void batchDrawPaddle() {
        this.paddle.draw(this.batch, 0);
    }

    public void batchDrawPaddleEnemy() {
        this.paddleEnemy.draw(this.batch, 0);
    }

    public void batchDrawBall() {
        this.ball.draw(this.batch, 0);
    }

    public void batchDrawBackground() {
        this.background.draw(this.batch, 0);
    }

    public void batchDrawScore() {
        this.score.draw(this.batch, 0);
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

    public Paddle getPaddleActor() {
        return this.paddle;
    }

    public Paddle getPaddleActorEnemy() {
        return this.paddleEnemy;
    }

    public Ball getBallActor() {
        return this.ball;
    }

    public Score getScoreActor() {
        return this.score;
    }

    public void ballUpdate() {
        this.ball.update();
    }

    public void paddleUpdate() {
        this.paddle.update();
    }

    public void ballCheckCollision() {
        this.ball.checkColision(this.paddle);
    }

    public void scoreUpdate() {

        if (this.ball.isBallTouchingTopOfScreen()) {
            this.scorePlayer++;
            this.ball.setPosition(400, 260);
        }

        if (this.ball.isBallTouchingBottomOfScreen()) {
            this.scoreEnemy++;
            this.ball.setPosition(400, 260);
        }
        this.score.update();
    }

    public void setServerOutputStream(OutputStream outputStream) {
        this.serverOutputStream = outputStream;
    }

    public void setServerInputStream(InputStream inputStream) {
        this.serverInputStream = inputStream;
    }

    public void setClientOutputStream(OutputStream outputStream) {
        this.clientOutputStream = outputStream;
    }

    public void setClientInputStream(InputStream inputStream) {
        this.clientInputStream = inputStream;
    }

    public OutputStream getServerOutputStream() {
        return this.serverOutputStream;
    }

    public InputStream getServerInputStream() {
        return this.serverInputStream;
    }

    public OutputStream getClientOutputStream() {
        return this.clientOutputStream;
    }

    public InputStream getClientInputStream() {
        return this.clientInputStream;
    }

    public boolean getIsServer() {
        return this.isServer;
    }

    public void setIsServer(boolean b) {
        this.isServer = b;
    }
}
