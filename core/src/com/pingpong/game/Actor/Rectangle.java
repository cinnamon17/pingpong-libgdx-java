
package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Rectangle {

    private Vector2 rectangleVector;
    private boolean leftMove;
    private boolean rightMove;
    final private float WIDTH = 104;
    final private float HEIGHT = 24;

    public Rectangle(int x, int y) {

        this.rectangleVector = new Vector2(x, y);
    }

    public void update() {

        if (this.isLeftMoved()) {
            handleLeftMove();
        }
        if (this.isRightMoved()) {
            handleRightMove();
        }
    }

    public void handleLeftMove() {

        this.rectangleVector.x -= 200 * Gdx.graphics.getDeltaTime();
        if (isAxisYTouched()) {
            this.rectangleVector.set(0, 0);
        }
    }

    public void handleRightMove() {

        this.rectangleVector.x += 200 * Gdx.graphics.getDeltaTime();
        if (isAxisYTouched()) {
            this.rectangleVector.set(Gdx.graphics.getWidth() - this.WIDTH, 0);
        }
    }

    public boolean isAxisYTouched() {
        return this.rectangleVector.x <= 0 || this.rectangleVector.x >= Gdx.graphics.getWidth() - this.WIDTH;
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
        return this.rectangleVector.x;
    }

    public float getY() {
        return this.rectangleVector.y;
    }

    public void setX(int x) {
        this.rectangleVector.x = x;
    }

    public void setY(int y) {
        this.rectangleVector.y = y;
    }

    public float getWidth() {
        return this.WIDTH;
    }

    public float getHeight() {
        return this.HEIGHT;
    }

    public boolean isLeftMoved() {
        return this.leftMove;
    }

    public boolean isRightMoved() {
        return this.rightMove;
    }
}
