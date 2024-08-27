package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;

public class Ball {

    private float x;
    private float speedX;
    private float y;
    private float speedY;
    final private float height = 22;
    final private float width = 22;

    public Ball(float x, float y) {

        this.x = x;
        this.speedX = 300;
        this.y = y;
        this.speedY = 300;
    }

    public void update() {
        // Update ball position
        x += speedX * Gdx.graphics.getDeltaTime();
        y += speedY * Gdx.graphics.getDeltaTime();

        // Screen boundary collision detection
        if (x <= 0 || x >= Gdx.graphics.getWidth() - 20) {
            speedX = -speedX;
        }
        if (y <= 0 || y >= Gdx.graphics.getHeight() - 20) {
            speedY = -speedY;
        }
    }

    public void checkColision(Rectangle paddle) {

        if (collidesWith(paddle)) {
            Gdx.app.log("Ball.java", "collision!");

            // Handle horizontal collision
            if (this.x + this.width > paddle.getX() && this.x < paddle.getX() + paddle.getWidth()) {
                speedY = -speedY;

                // Adjust ball position to be outside of the paddle to prevent sticking
                if (this.y > paddle.getY()) {
                    this.y = paddle.getY() + paddle.getHeight();
                } else {
                    this.y = paddle.getY() - this.height;
                }
            }

            // Handle vertical collision (sides of the paddle)
            if (this.y + this.height > paddle.getY() && this.y < paddle.getY() + paddle.getHeight()) {
                speedX = -speedX;

                // Adjust ball position to be outside of the paddle to prevent sticking
                if (this.x > paddle.getX()) {
                    this.x = paddle.getX() + paddle.getWidth();
                } else {
                    this.x = paddle.getX() - this.width;
                }
            }

            Gdx.app.log("Ball.java", "xRectangle: " + paddle.getX() + " yRectangle: " + paddle.getY());
            Gdx.app.log("Ball.java", "xball: " + this.x + " yball: " + this.y + "ballSpeedX: " + this.speedX
                    + " ballSpeedY: " + this.speedY);
        }
    }

    private boolean collidesWith(Rectangle paddle) {

        return paddle.getX() < this.x + this.width && paddle.getY() < this.y + this.height
                && paddle.getX() + paddle.getWidth() > this.x && paddle.getY() + paddle.getHeight() > this.y;

    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
