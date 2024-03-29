package Output;

import GameFrame.GameObject;

import java.awt.*;

public class GameCamera {

    private float x, y;

    public GameCamera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        x = -player.getX() + Game.WIDTH / 2 - 16;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
