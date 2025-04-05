package com.cinnamon.pingpong.Dto;

/**
 * Data
 */
public class Data {

    private float serverPaddleX;
    private float clientPaddleX;
    private float serverBallX;
    private float serverBallY;
    private float clientBallX;
    private float clientBallY;
    private int scoreEnemy;
    private int scorePlayer;
    private boolean clientPaddleIsRightMoved;
    private boolean clientPaddleIsLeftMoved;

    public float getClientBallX() {
        return clientBallX;
    }

    public float getClientBallY() {
        return clientBallY;
    }

    public float getServerBallX() {
        return serverBallX;
    }

    public float getServerBallY() {
        return serverBallY;
    }

    public float getClientPaddleX() {
        return clientPaddleX;
    }

    public float getServerPaddleX() {
        return serverPaddleX;
    }

    public int getScorePlayer() {
        return scorePlayer;
    }

    public int getScoreEnemy() {
        return scoreEnemy;
    }

    public boolean getClientPaddleIsRightMoved() {
        return this.clientPaddleIsRightMoved;
    }

    public boolean getClientPaddleIsLeftMoved() {
        return this.clientPaddleIsLeftMoved;
    }

    public void setClientBallX(float clientBallX) {
        this.clientBallX = clientBallX;
    }

    public void setClientBallY(float clientBallY) {
        this.clientBallY = clientBallY;
    }

    public void setServerBallX(float serverBallX) {
        this.serverBallX = serverBallX;
    }

    public void setServerBallY(float serverBallY) {
        this.serverBallY = serverBallY;
    }

    public void setClientPaddleX(float clientPaddleX) {
        this.clientPaddleX = clientPaddleX;
    }

    public void setServerPaddleX(float serverPaddleX) {
        this.serverPaddleX = serverPaddleX;
    }

    public void setScoreEnemy(int scoreEnemy) {
        this.scoreEnemy = scoreEnemy;
    }

    public void setScorePlayer(int scorePlayer) {
        this.scorePlayer = scorePlayer;
    }

    public void setClientPaddleIsLeftMoved(boolean clientPaddleIsLeftMoved) {
        this.clientPaddleIsLeftMoved = clientPaddleIsLeftMoved;
    }

    public void setClientPaddleIsRightMoved(boolean clientPaddleIsRightMoved) {
        this.clientPaddleIsRightMoved = clientPaddleIsRightMoved;
    }
}
