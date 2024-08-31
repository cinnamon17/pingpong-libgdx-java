
package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Paddle extends Image {

    private boolean leftMove;
    private boolean rightMove;

    public Paddle(TextureRegion textureRegion) {

        super(textureRegion);
        this.setPosition(0, 0);
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

        this.setX(this.getX() - 200 * Gdx.graphics.getDeltaTime());
        if (isAxisYTouched()) {
            this.setPosition(0, 0);
        }
    }

    public void handleRightMove() {

        this.setX(this.getX() + 200 * Gdx.graphics.getDeltaTime());
        if (isAxisYTouched()) {
            this.setPosition(Gdx.graphics.getWidth() - this.getWidth(), 0);
        }
    }

    public boolean isAxisYTouched() {
        return this.getX() <= 0 || this.getX() >= Gdx.graphics.getWidth() - this.getWidth();
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

    public boolean isLeftMoved() {
        return this.leftMove;
    }

    public boolean isRightMoved() {
        return this.rightMove;
    }
}
