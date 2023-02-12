package  com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball {

    private int x;
    private int speedX;
    private int y;
    private int speedY;
    private Texture ballTexture;
    private Color color;

    public Ball(int x, int speedX, int y,int speedY ) {

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

        if (x <= 0 || x >= Gdx.graphics.getWidth() -20) {
            speedX = -speedX;
        }
        if (y <= 0 || y >= Gdx.graphics.getHeight() -20) {
            speedY = -speedY;
        }
    }

    public void draw(SpriteBatch batch) {

        batch.setColor(color);
        batch.draw(ballTexture, x, y);

    }

    public void checkColision(Rectangle paddle, int xRectangle, int yRectangle) {

        System.out.println("=================================================================");
        System.out.println("Ball x: " + this.x + " Ball y: " + this.y);
        System.out.println("Rectangle x: " + paddle.getX() + " Rectangle y: " + paddle.getY());
        System.out.println("=================================================================");

         if(collidesWith(paddle, xRectangle, yRectangle)){

             this.speedY = -speedY;
        }
        else{
            color = Color.WHITE;
        }

    }

     private boolean collidesWith(Rectangle paddle, int xRectangle, int yRectangle) {

         return paddle.getX() < this.x + ballTexture.getWidth() && paddle.getY() < this.y + ballTexture.getHeight() && paddle.getX() + 200 > this.x && paddle.getY() + 83 > this.y;

    }

}
