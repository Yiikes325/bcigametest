package Output;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animations {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] image;
    private BufferedImage currentImage;

    public Animations(int speed, BufferedImage...args) {
        this.speed = speed;
        image = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            image[i] = args[i];
        }

        frames = args.length;
    }

    public void runAnimations() {
        index++;
        if (index > speed) {
            index = 0;
            nextAnimFrame();
        }
    }

    private void nextAnimFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) {
                currentImage = image[i];
            }
            count++;

            if (count > frames) {
                count = 0;
            }
        }
    }

    public void drawAnim(Graphics g, int x, int y) {
        g.drawImage(currentImage, x, y, null);
    }

    public void drawAnim(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImage, x, y, scaleX, scaleY, null);
    }
}
