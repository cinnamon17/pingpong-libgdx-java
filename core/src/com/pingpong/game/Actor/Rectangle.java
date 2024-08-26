
package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rectangle {

    private float x;
    private float y;
    private Texture texture;
    private boolean leftMove;
    private boolean rightMove;

    public Rectangle(int x, int y) {

        this.x = x;
        this.y = y;
        this.texture = new Texture(Gdx.files.internal("rectangle.png"));
    }

    public void update() {

        if (leftMove) {
            this.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (rightMove) {
            this.x += 200 * Gdx.graphics.getDeltaTime();
        }
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

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
