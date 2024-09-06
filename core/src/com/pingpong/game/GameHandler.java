package com.pingpong.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pingpong.game.Actor.Ball;
import com.pingpong.game.Actor.Paddle;
import com.pingpong.game.Actor.Score;
import com.pingpong.game.Dto.Data;
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
    private boolean isServer;
    private DataOutputStream serverDataOutputStream, clientDataOutputStream;
    private DataInputStream serverDataInputStream, clientDataInputStream;
    private Json json;
    private Data data;

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
        this.json = new Json();
        this.data = new Data();
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

    public void setPaddleVisible() {
        this.paddle.setVisible(true);
    }

    public void setPaddleEnemyVisible() {
        this.paddleEnemy.setVisible(true);
    }

    public void setBallVisible() {
        this.ball.setVisible(true);
    }

    public void setScoreVisible() {
        this.score.setVisible(true);
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

    public void setPaddleNoVisible() {
        this.paddle.setVisible(false);
    }

    public void setPaddleEnemyNoVisible() {
        this.paddleEnemy.setVisible(false);
    }

    public void setBallNoVisible() {
        this.ball.setVisible(false);
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
        this.ball.checkColision(this.paddleEnemy);
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

    public void setServerDataOutputStream(OutputStream outputStream) {
        this.serverDataOutputStream = new DataOutputStream(outputStream);
    }

    public void setServerDataInputStream(InputStream inputStream) {
        this.serverDataInputStream = new DataInputStream(inputStream);
    }

    public void setClientDataOutputStream(OutputStream outputStream) {
        this.clientDataOutputStream = new DataOutputStream(outputStream);
    }

    public void setClientDataInputStream(InputStream inputStream) {
        this.clientDataInputStream = new DataInputStream(inputStream);
    }

    public DataInputStream getServerDataInputStream() {
        return serverDataInputStream;
    }

    public DataOutputStream getServerDataOutputStream() {
        return serverDataOutputStream;
    }

    public DataInputStream getClientDataInputStream() {
        return clientDataInputStream;
    }

    public DataOutputStream getClientDataOutputStream() {
        return clientDataOutputStream;
    }

    public boolean getIsServer() {
        return this.isServer;
    }

    public void setIsServer(boolean b) {
        this.isServer = b;
    }

    public void updateMultiplayerCommunication() {
        try {
            if (this.getIsServer()) {
                this.handleServerSideCommunication();
            } else {
                this.handleClientSideCommunication();
            }
        } catch (IOException e) {
            this.logError(e);
        }
    }

    private void handleServerSideCommunication() throws IOException {
        updateServerData();
        sendServerDataToClient();
        receiveClientDataAndUpdatePaddle();
    }

    private void handleClientSideCommunication() throws IOException {
        receiveServerDataAndUpdateClient();
        updateClientData();
        sendClientDataToServer();
    }

    private void updateServerData() {
        data.setServerPaddleX(this.paddle.getX());
        data.setServerBallX(this.ball.getX());
        data.setServerBallY(calculateServerBallY());
        data.setScorePlayer(this.getScorePlayer());
        data.setScoreEnemy(this.getScoreEnemy());
    }

    private float calculateServerBallY() {
        return Gdx.graphics.getHeight() - this.getBallActor().getY() - this.getBallActor().getHeight();
    }

    private void sendServerDataToClient() throws IOException {
        this.getServerDataOutputStream().writeUTF(json.toJson(data));
    }

    private void receiveClientDataAndUpdatePaddle() throws IOException {
        Data clientData = json.fromJson(Data.class, this.getServerDataInputStream().readUTF());
        this.getPaddleActorEnemy().setX(clientData.getClientPaddleX());
    }

    private void receiveServerDataAndUpdateClient() throws IOException {
        Data serverData = json.fromJson(Data.class, this.getClientDataInputStream().readUTF());
        this.getPaddleActorEnemy().setX(serverData.getServerPaddleX());
        this.getBallActor().setPosition(serverData.getServerBallX(), serverData.getServerBallY());
        this.setScoreEnemy(serverData.getScoreEnemy());
        this.setScorePlayer(serverData.getScorePlayer());
    }

    private void updateClientData() {
        data.setClientPaddleX(this.getPaddleActor().getX());
    }

    private void sendClientDataToServer() throws IOException {
        this.getClientDataOutputStream().writeUTF(json.toJson(data));
    }

    private void logError(IOException e) {
        Gdx.app.log("GameScreen.java", "Error sending Data", e);
    }
}
