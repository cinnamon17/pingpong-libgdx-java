
package  com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rectangle {

    private int x;
    private int speedX;
    private int y;
    private int speedY;
    private Texture ballTexture;
    private Color color;

    public Rectangle(int x, int speedX, int y,int speedY ) {

        this.x = x;
        this.speedX = speedX;
        this.y = y;
        this.speedY = speedY;
        this.ballTexture = new Texture(Gdx.files.internal("rectangle.png"));
        this.color = Color.WHITE;
    }

    public void update() {


        x += speedX;
        y += speedY;

        if (x <= 0) {

            this.setX(0);
        }
        if (x >= Gdx.graphics.getWidth() - 200) {

            this.setX(Gdx.graphics.getWidth() - 200 );
        }

        if (y <= 0 ) {
            this.setY(0);
        }

        if (y >= Gdx.graphics.getHeight() - 83) {

            this.setY(Gdx.graphics.getHeight() - 83);
        }
    }

    public void draw(SpriteBatch batch) {

        batch.setColor(color);
        batch.draw(ballTexture, x, y);

    }

    public void checkColision(Texture paddle, int xRectangle, int yRectangle) {

         if(collidesWith(paddle, xRectangle, yRectangle)){
            color = Color.GREEN;
        }
        else{
            color = Color.WHITE;
        }

    }

     private boolean collidesWith(Texture paddle, int xRectangle, int yRectangle) {

         if (paddle.getWidth() == this.y) {

            return true;
         }
         return false;
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
