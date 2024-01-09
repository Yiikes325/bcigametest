package Objects;

import GameFrame.GameObject;
import GameFrame.ObjectId;
import GameFrame.Textures;
import Output.Game;

import java.awt.*;
import java.util.LinkedList;

public class GameBlock extends GameObject {
    Textures textures = Game.getInstance();
    private int type;
    public GameBlock (float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    public void tick(LinkedList<GameObject> objects) {

    }

    public void render(Graphics g) {
        g.drawImage(textures.block[type], (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
