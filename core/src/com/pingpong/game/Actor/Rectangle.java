
package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;

public class Rectangle {

    private float x;
    private float y;
    private boolean leftMove;
    private boolean rightMove;
    final private float width = 104;
    final private float height = 24;

    public Rectangle(int x, int y) {

        this.x = x;
        this.y = y;
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

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isLeftMoved() {
        return this.leftMove;
    }

    public boolean isRightMoved() {
        return this.rightMove;
    }
}
