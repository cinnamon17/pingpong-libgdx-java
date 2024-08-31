package com.pingpong.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.pingpong.game.GameHandler;
import com.pingpong.game.Actor.Paddle;

/**
 * Input handler for Paddle
 */
public class PaddleInputProcessor implements InputProcessor {

    private Paddle rectangle;
    private GameHandler game;

    public PaddleInputProcessor(GameHandler game, Paddle rectangle) {
        this.game = game;
        this.rectangle = rectangle;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                rectangle.setLeftMove(false);
                break;
            case Keys.RIGHT:
                rectangle.setRightMove(false);
                break;
            case Keys.UP:
                break;
            case Keys.DOWN:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Keys.LEFT:
                rectangle.setLeftMove(true);
                break;
            case Keys.RIGHT:
                rectangle.setRightMove(true);
                break;
            case Keys.UP:
                break;
            case Keys.DOWN:
                break;
            case Keys.ESCAPE:
                this.game.setScreen(this.game.getMainTitleScreen());
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

}
