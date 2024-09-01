package com.pingpong.game.Screen;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pingpong.game.GameHandler;

public class GameScreen implements Screen {
    private final GameHandler game;

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

                ByteBuffer serverByteBuffer = ByteBuffer.allocate(4);
                serverByteBuffer.putFloat(game.getPaddleActor().getX());

                game.getServerOutputStream().write(serverByteBuffer.array());

                Gdx.app.log("GameScreen.java", "server sent posX: " + game.getPaddleActor().getX());
                byte[] b = new byte[1024];

                game.getServerInputStream().read(b);

                float posX = ByteBuffer.wrap(b).getFloat();

                Gdx.app.log("GameScreen.java", "server received posX: " + posX);
                game.getPaddleActorEnemy().setX(posX);
            } else {

                byte[] b = new byte[1024];
                game.getClientInputStream().read(b);
                float posX = ByteBuffer.wrap(b).getFloat();

                Gdx.app.log("GameScreen.java", "client received posX: " + posX);
                game.getPaddleActorEnemy().setX(posX);
                ByteBuffer clientByteBuffer = ByteBuffer.allocate(4);
                clientByteBuffer.putFloat(game.getPaddleActor().getX());

                game.getClientOutputStream().write(clientByteBuffer.array());
                Gdx.app.log("GameScreen.java", "client sent posX: " + game.getPaddleActor().getX());
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
