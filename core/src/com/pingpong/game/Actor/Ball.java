package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    private float speedX;
    private Vector2 ballVector;
    private float speedY;
    private Rectangle paddle;
    final private float HEIGHT = 22;
    final private float WIDTH = 22;

    public Ball(float x, float y) {

        this.speedX = 300;
        this.speedY = 300;
        this.ballVector = new Vector2(x, y);
    }

    public void update() {
        this.ballVector.x += speedX * Gdx.graphics.getDeltaTime();
        this.ballVector.y += speedY * Gdx.graphics.getDeltaTime();

        if (isAxisYTouched()) {
            speedX = -speedX;
        }
        if (isAxisXTouched()) {
            speedY = -speedY;
        }
    }

    public boolean isAxisYTouched() {
        return this.ballVector.x <= 0 || this.ballVector.x >= Gdx.graphics.getWidth() - this.WIDTH;
    }

    public boolean isAxisXTouched() {
        return this.ballVector.y <= 0 || this.ballVector.y >= Gdx.graphics.getHeight() - this.HEIGHT;
    }

    public void checkColision() {

        if (isCollided()) {
            this.debugCollision();
            this.handleHorizontalCollision();
        }
    }

    private boolean isCollided() {
        return this.paddle.getX() < this.ballVector.x + this.WIDTH
                && this.paddle.getY() < this.ballVector.y + this.HEIGHT
                && this.paddle.getX() + this.paddle.getWidth() > this.ballVector.x
                && this.paddle.getY() + this.paddle.getHeight() > this.ballVector.y;
    }

    public void debugCollision() {
        Gdx.app.log("Ball.java", "collision!");
        Gdx.app.log("Ball.java", "xRectangle: " + this.paddle.getX() + " yRectangle: " + this.paddle.getY());
        Gdx.app.log("Ball.java",
                "xball: " + this.ballVector.x + " yball: " + this.ballVector.y + "ballSpeedX: " + this.speedX
                        + " ballSpeedY: " + this.speedY);
    }

    public void handleHorizontalCollision() {

        if (isThereHorizontalCollision()) {
            handleTouchedSidesOfPaddle();
        }
    }

    public void handleTouchedSidesOfPaddle() {
        if (isBallTouchingLeftSideOfPaddle()) {

            speedX = -300;
            speedY = -speedY;
        }

        if (isBallTouchingRightSideOfPaddle()) {

            speedX = 300;
            speedY = -speedY;
        }

        if (isBallTouchingCenterOfPaddle()) {
            handleInertiaMovements();
        }
    }

    public boolean isBallTouchingRightSideOfPaddle() {
        return this.ballVector.x + this.WIDTH - paddle.getX() > 80;
    }

    public boolean isBallTouchingLeftSideOfPaddle() {
        return this.ballVector.x + this.WIDTH - paddle.getX() < 40;
    }

    public boolean isBallTouchingCenterOfPaddle() {
        return this.ballVector.x + this.WIDTH - this.paddle.getX() > 40
                && this.ballVector.x + this.WIDTH - this.paddle.getX() < 80;
    }

    public boolean isThereHorizontalCollision() {
        return this.ballVector.x + this.WIDTH > paddle.getX() && this.ballVector.x < paddle.getX() + paddle.getWidth();
    }

    public void handleInertiaMovements() {

        if (this.paddle.isRightMoved()) {
            speedX = 100;
            speedY = -speedY;
        }

        else if (this.paddle.isLeftMoved()) {
            speedX = -100;
            speedY = -speedY;
        } else {
            speedX = 10;
            speedY = -speedY + 50;
        }
    }

    public void setPaddle(Rectangle paddle) {
        this.paddle = paddle;
    }

    public float getX() {
        return this.ballVector.x;
    }

    public float getY() {
        return this.ballVector.y;
    }

    public void setX(int x) {
        this.ballVector.x = x;
    }

    public void setY(int y) {
        this.ballVector.y = y;
    }

    public float getWidth() {
        return this.WIDTH;
    }

    public float getHeight() {
        return this.HEIGHT;
    }
}
