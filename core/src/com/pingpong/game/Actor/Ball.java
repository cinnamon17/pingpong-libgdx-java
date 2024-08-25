package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball {

    private int x;
    private float speedX;
    private int y;
    private float speedY;
    private Texture ballTexture;
    private Color color;

    public Ball(int x, int speedX, int y, int speedY) {

        this.x = x;
        this.speedX = speedX;
        this.y = y;
        this.speedY = speedY;
        this.ballTexture = new Texture(Gdx.files.internal("ball.png"));
        this.color = Color.WHITE;
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

    public void draw(SpriteBatch batch) {

        batch.setColor(color);
        batch.draw(ballTexture, x, y);

    }

    public void checkColision(Rectangle paddle, float xRectangle, float yRectangle) {

        if (collidesWith(paddle, xRectangle, yRectangle)) {

            Gdx.app.log("Ball.java", "collision!");
            if (this.y >= 80) {
                this.speedY = -speedY + 10 * Gdx.graphics.getDeltaTime();
            }

            Gdx.app.log("Ball.java", "xRectangle: " + xRectangle + " yRectangle: " + yRectangle);
            Gdx.app.log("Ball.java", "xball: " + this.x + " yball: " + this.y + "ballSpeedX :" + this.speedX
                    + "ballSpeedY :" + this.speedY);

            if (this.y <= 79) {
                this.speedX = -speedX + 10 * Gdx.graphics.getDeltaTime();
            }
        }
    }

    private boolean collidesWith(Rectangle paddle, float xRectangle, float yRectangle) {

        return paddle.getX() < this.x + ballTexture.getWidth() && paddle.getY() < this.y + ballTexture.getHeight()
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

}
