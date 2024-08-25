
package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rectangle {

    private float x;
    private float y;
    private Texture ballTexture;
    private Color color;
    private boolean leftMove;
    private boolean rightMove;

    public Rectangle(int x, int y) {

        this.x = x;
        this.y = y;
        this.ballTexture = new Texture(Gdx.files.internal("rectangle.png"));
        this.color = Color.WHITE;
    }

    public void update() {

        if (leftMove) {
            this.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (rightMove) {
            this.x += 200 * Gdx.graphics.getDeltaTime();
        }
    }

    public void draw(SpriteBatch batch) {

        batch.setColor(color);
        batch.draw(ballTexture, x, y);

    }

    public void checkColision(Texture paddle, int xRectangle, int yRectangle) {

        if (collidesWith(paddle, xRectangle, yRectangle)) {
            color = Color.GREEN;
        } else {
            color = Color.WHITE;
        }

    }

    private boolean collidesWith(Texture paddle, int xRectangle, int yRectangle) {

        if (paddle.getWidth() == this.y) {

            return true;
        }
        return false;
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

    public void setLeftMove(boolean t) {
        if (rightMove && t)
            rightMove = false;
        leftMove = t;
    }

    public void setRightMove(boolean t) {
        if (leftMove && t)
            leftMove = false;
        rightMove = t;
    }
}
