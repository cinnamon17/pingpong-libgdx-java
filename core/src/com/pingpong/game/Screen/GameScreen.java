package com.pingpong.game.Screen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Dto.Data;

public class GameScreen implements Screen {
    private final GameHandler game;
    private Json json;
    private DataOutputStream serverDataOutputStream, clientDataOutputStream;
    private DataInputStream serverDataInputStream, clientDataInputStream;
    private Data data;

    public GameScreen(final GameHandler game) {
        this.game = game;
    }

    @Override
    public void show() {

        Gdx.app.log("GameScreen.java", "show");
        game.setMusicLooping(true);
        game.musicPlay();
        game.getPaddleActor().setVisible(true);
        game.getPaddleActorEnemy().setVisible(true);
        game.getBallActor().setVisible(true);
        game.getScoreActor().setVisible(true);
        json = new Json();
        serverDataOutputStream = new DataOutputStream(game.getServerOutputStream());
        serverDataInputStream = new DataInputStream(game.getServerInputStream());
        clientDataInputStream = new DataInputStream(game.getClientInputStream());
        clientDataOutputStream = new DataOutputStream(game.getClientOutputStream());
        data = new Data();
    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log("GameScreen.java", "resize method");
        Gdx.app.log("GameScreen.java", "width: " + width + " Height: " + height);
    }

    @Override
    public void resume() {

        Gdx.app.log("GameScreen.java", "resume method");
        game.musicPlay();
    }

    @Override
    public void dispose() {
        game.musicDispose();
        game.stageDispose();
    }

    @Override
    public void hide() {
        game.musicPause();
        game.getPaddleActor().setVisible(false);
        game.getBallActor().setVisible(false);
        game.getScoreActor().setVisible(false);
        Gdx.app.log("GameScreen.java", "hide");
    }

    @Override
    public void pause() {

        Gdx.app.log("GameScreen.java", "pause");

    }

    @Override
    public void render(float delta) {

        game.clearScreen();
        game.cameraUpdate();
        game.setProjectionMatrixCombined();
        game.stageDraw();
        game.ballUpdate();
        game.scoreUpdate();
        game.paddleUpdate();

        try {

            if (game.getIsServer()) {

                data.setServerPaddleX(game.getPaddleActor().getX());
                data.setServerBallY(
                        Gdx.graphics.getHeight() - game.getBallActor().getY() - game.getBallActor().getHeight());
                data.setServerBallX(game.getBallActor().getX());
                data.setScorePlayer(game.getScorePlayer());
                data.setScoreEnemy(game.getScoreEnemy());
                serverDataOutputStream.writeUTF(json.toJson(data));

                Data clientData = json.fromJson(Data.class, serverDataInputStream.readUTF());
                game.getPaddleActorEnemy().setX(clientData.getClientPaddleX());
            } else {

                Data serverData = json.fromJson(Data.class, clientDataInputStream.readUTF());
                game.getPaddleActorEnemy().setX(serverData.getServerPaddleX());
                game.getBallActor().setPosition(serverData.getServerBallX(), serverData.getServerBallY());
                game.setScoreEnemy(serverData.getScoreEnemy());
                game.setScorePlayer(serverData.getScorePlayer());

                data.setClientPaddleX(game.getPaddleActor().getX());
                clientDataOutputStream.writeUTF(json.toJson(data));
            }

        } catch (IOException e) {
            Gdx.app.log("GameScreen.java", "Error sending Data", e);
        }
        game.batchBegin();
        game.batchDrawScore();
        game.batchDrawBackground();
        game.batchDrawPaddle();
        game.batchDrawPaddleEnemy();
        game.batchDrawBall();
        game.batchEnd();

        game.ballCheckCollision();

    }
}
