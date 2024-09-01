package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ball extends Image {

    private float speedX;
    private float speedY;
    private Paddle paddle;

    public Ball(TextureRegion textureRegion) {

        super(textureRegion);
        this.speedX = 300;
        this.speedY = 300;
        this.setPosition(400, 240);
        this.setVisible(false);
    }

    public void update() {
        this.moveBy(speedX * Gdx.graphics.getDeltaTime(), speedY * Gdx.graphics.getDeltaTime());

        if (isAxisYTouched()) {
            speedX = -speedX;
        }
        if (isAxisXTouched()) {
            speedY = -speedY;
        }

        this.resetPosIfTopOrBottomTouched();
    }

    public boolean isAxisYTouched() {
        return this.getX() <= 0 || this.getX() >= Gdx.graphics.getWidth() - this.getWidth();
    }

    public boolean isAxisXTouched() {
        return this.getY() <= 0 || this.getY() >= Gdx.graphics.getHeight() - this.getHeight();
    }

    public void checkColision(Paddle paddle) {

        this.setPaddle(paddle);
        if (isCollided()) {
            this.debugCollision();
            this.handleHorizontalCollision();
        }
    }

    private boolean isCollided() {
        return paddle.getX() < this.getX() + this.getWidth()
                && this.paddle.getY() < this.getY() + this.getHeight()
                && this.paddle.getX() + this.paddle.getWidth() > this.getX()
                && this.paddle.getY() + this.paddle.getHeight() > this.getY();
    }

    public void debugCollision() {
        Gdx.app.log("Ball.java", "collision!");
        Gdx.app.log("Ball.java", "xPaddle: " + this.paddle.getX() + " yPaddle: " + this.paddle.getY());
        Gdx.app.log("Ball.java",
                "xball: " + this.getX() + " yball: " + this.getY() + "ballSpeedX: " + this.speedX
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
        return this.getX() + this.getWidth() - this.paddle.getX() > 80;
    }

    public boolean isBallTouchingLeftSideOfPaddle() {
        return this.getX() + this.getWidth() - paddle.getX() < 40;
    }

    public boolean isBallTouchingCenterOfPaddle() {
        return this.getX() + this.getWidth() - this.paddle.getX() > 40
                && this.getX() + this.getWidth() - this.paddle.getX() < 80;
    }

    public boolean isThereHorizontalCollision() {
        return this.getX() + this.getWidth() > paddle.getX() && this.getX() < paddle.getX() + paddle.getWidth();
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
            speedY = -speedY;
        }
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public void setSpeedX(float speed) {
        this.speedX = speed;
    }

    public void setSpeedY(float speed) {
        this.speedY = speed;
    }

    public boolean isBallTouchingBottomOfScreen() {
        return this.getY() <= 0;
    }

    public boolean isBallTouchingTopOfScreen() {
        return this.getY() + this.getHeight() >= 480;
    }

    public void resetPosIfTopOrBottomTouched() {

        if (this.isBallTouchingTopOfScreen() || this.isBallTouchingBottomOfScreen()) {
            this.setSpeedX(0);
        }
    }
}
