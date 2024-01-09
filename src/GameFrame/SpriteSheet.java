package GameFrame;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage bufferedImage;
    public SpriteSheet(BufferedImage image) {
        this.bufferedImage = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage imageGrabber = bufferedImage.getSubimage((col * width) - width, (row * height) - height, width, height);
        return imageGrabber;
    }
}
