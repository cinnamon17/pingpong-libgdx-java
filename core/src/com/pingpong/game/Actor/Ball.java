package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    private float speedX;
    private Vector2 ballVector;
    private float speedY;
    final private float height = 22;
    final private float width = 22;

    public Ball(float x, float y) {

        this.speedX = 300;
        this.speedY = 300;
        this.ballVector = new Vector2(x, y);
    }

    public void update() {
        // Update ball position
        this.ballVector.x += speedX * Gdx.graphics.getDeltaTime();
        this.ballVector.y += speedY * Gdx.graphics.getDeltaTime();

        // Screen boundary collision detection
        if (this.ballVector.x <= 0 || this.ballVector.x >= Gdx.graphics.getWidth() - 20) {
            speedX = -speedX;
        }
        if (this.ballVector.y <= 0 || this.ballVector.y >= Gdx.graphics.getHeight() - 20) {
            speedY = -speedY;
        }
    }

    public void checkColision(Rectangle paddle) {

        if (collidesWith(paddle)) {
            Gdx.app.log("Ball.java", "collision!");

            this.handleHorizontalCollision(paddle);
            // Posibly eliminate this method TODO
            // this.handleVerticalCollision(paddle);

            Gdx.app.log("Ball.java", "xRectangle: " + paddle.getX() + " yRectangle: " + paddle.getY());
            Gdx.app.log("Ball.java",
                    "xball: " + this.ballVector.x + " yball: " + this.ballVector.y + "ballSpeedX: " + this.speedX
                            + " ballSpeedY: " + this.speedY);
        }
    }

    private boolean collidesWith(Rectangle paddle) {

        return paddle.getX() < this.ballVector.x + this.width && paddle.getY() < this.ballVector.y + this.height
                && paddle.getX() + paddle.getWidth() > this.ballVector.x
                && paddle.getY() + paddle.getHeight() > this.ballVector.y;

    }

    public void handleHorizontalCollision(Rectangle paddle) {

        // Handle horizontal collision
        if (this.ballVector.x + this.width > paddle.getX() && this.ballVector.x < paddle.getX() + paddle.getWidth()) {

            Gdx.app.log("Ball.java", "horizontal collision");
            if (this.ballVector.x + this.width - paddle.getX() < 40) {

                Gdx.app.log("Ball.java", "left side");
                speedX = -300;
                speedY = -speedY;
            }

            if (this.ballVector.x + this.width - paddle.getX() > 80) {

                Gdx.app.log("Ball.java", "right side");
                speedX = 300;
                speedY = -speedY;
            }

            if (this.ballVector.x + this.width - paddle.getX() > 40
                    && this.ballVector.x + this.width - paddle.getX() < 80) {

                Gdx.app.log("Ball.java", "center side");
                speedX = 0;
                speedY = -speedY;
            }

            // Adjust ball position to be outside of the paddle to prevent sticking
            if (this.ballVector.y > paddle.getY()) {
                Gdx.app.log("Ball.java", "adjust ball to prevent sticking");
                this.ballVector.y = paddle.getY() + paddle.getHeight();
                Gdx.app.log("Ball.java", "angle: " + this.ballVector.angleDeg());
            }
        }

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
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
