package Output;

import GameFrame.GameObject;
import GameFrame.ObjectId;
import Objects.*;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class GameHandler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private GameCamera camera;
    private GameObject temporaryObject;

    public GameHandler(GameCamera camera) {
        this.camera = camera;
    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            temporaryObject = objects.get(i);
            temporaryObject.tick(objects);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            temporaryObject = objects.get(i);
            temporaryObject.render(g);
        }
    }

    public void LoadImageLevel(BufferedImage gameImage) {
        int w = gameImage.getWidth();
        int h = gameImage.getHeight();

        System.out.println("width, height: " + w + " " + h);
        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = gameImage.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 & blue == 255) { //If the colour is white...
                    addObject(new GameBlock(xx * 32, yy * 32, 0, ObjectId.GameBlock)); //It becomes the game block.
                }
                if (red == 0 && green == 255 & blue == 0) { //If the colour on the image is green...
                    addObject(new Player(xx * 32, yy * 32, this, camera, ObjectId.Player)); //That is where the player will spawn.
                }
                if (red == 0 && green == 0 & blue == 255) { //If the colour on the image is blue...
                    addObject(new NextLevel(xx * 32, yy * 32, ObjectId.NextLevel)); //That is where the normal level loads up.
                }
                if (red == 128 && green == 0 & blue == 255) { //If the colour on the image is purple..
                    addObject(new NextLevel2(xx * 32, yy * 32, ObjectId.NextLevel2)); //That is where the hard level loads up.
                }
                if (red == 237 && green == 28 & blue == 36) { //If the colour on the image is a variation of red...
                    addObject(new EndLevel(xx * 32, yy * 32, ObjectId.EndLevel)); //The game will revert to the menu.
                }
            }
        }
    }

    public void clearLevel() {
        objects.clear();
    }

    public void addObject(GameObject objects) {
        this.objects.add(objects);
    }

    public void removeObject(GameObject objects) {
        this.objects.remove(objects);
    }


    //public void levelCreate() {
    //    for (int yy = 0; yy < Game.HEIGHT + 32; yy += 32) {
    //        addObject(new GameBlock(0, yy, ObjectId.GameBlock));
    //    }
    //    for (int xx = 0; xx < Game.WIDTH * 2; xx += 32) {
    //        addObject(new GameBlock(xx, Game.HEIGHT - 32, ObjectId.GameBlock));
    //    }
    //    for (int xx = 200; xx < 600; xx += 32) {
    //        addObject(new GameBlock(xx, 400, ObjectId.GameBlock));
    //    }
    //}
}
