package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Ball {

    private int x;
    private float speedX;
    private int y;
    private float speedY;
    private Texture texture;

    public Ball(int x, int y) {

        this.x = x;
        this.speedX = 5;
        this.y = y;
        this.speedY = 5;
        this.texture = new Texture(Gdx.files.internal("ball.png"));
    }

    public void update() {

        x += speedX;
        y += speedY;

        if (x <= 0 || x >= Gdx.graphics.getWidth() - 20) {
            speedX = -speedX + 10 * Gdx.graphics.getDeltaTime();
        }
        if (y <= 0 || y >= Gdx.graphics.getHeight() - 20) {
            speedY = -speedY + 10 * Gdx.graphics.getDeltaTime();
        }
    }

    public void checkColision(Rectangle paddle) {

        if (collidesWith(paddle)) {

            Gdx.app.log("Ball.java", "collision!");
            if (this.y >= 80) {
                this.speedY = -speedY + 10 * Gdx.graphics.getDeltaTime();
            }

            Gdx.app.log("Ball.java", "xRectangle: " + paddle.getX() + " yRectangle: " + paddle.getY());
            Gdx.app.log("Ball.java", "xball: " + this.x + " yball: " + this.y + "ballSpeedX :" + this.speedX
                    + "ballSpeedY :" + this.speedY);

            if (this.y <= 79) {
                this.speedX = -speedX + 10 * Gdx.graphics.getDeltaTime();
            }
        }
    }

    private boolean collidesWith(Rectangle paddle) {

        return paddle.getX() < this.x + texture.getWidth() && paddle.getY() < this.y + texture.getHeight()
                && paddle.getX() + 200 > this.x && paddle.getY() + 83 > this.y;

    }

    public int getX() {

        return this.x;

    }

    public int getY() {

        return this.y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
