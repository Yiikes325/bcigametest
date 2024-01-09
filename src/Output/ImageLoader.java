package Output;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    private BufferedImage gameImage;

    public BufferedImage loadGameImage(String path) {
        try {
            gameImage = ImageIO.read(getClass().getResource(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return gameImage;
    }
}
