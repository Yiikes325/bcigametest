package Objects;

import GameFrame.GameObject;
import GameFrame.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class NextLevel2 extends GameObject {
    public NextLevel2(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
