package GameFrame;

import Output.ImageLoader;

import java.awt.image.BufferedImage;

public class Textures {

    SpriteSheet blsheet, plsheet;
    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;
    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[14];
    public BufferedImage[] playerJump = new BufferedImage[6];

    public Textures() {
        ImageLoader imageLoader = new ImageLoader();
        try {
            blockSheet = imageLoader.loadGameImage("/block_sheet.png"); //Block sprite sheet loader.
            playerSheet = imageLoader.loadGameImage("/player_sheet.png"); //Player sprite sheet loader.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        blsheet = new SpriteSheet(blockSheet);
        plsheet = new SpriteSheet(playerSheet);

        getTextures();
    }

    private void getTextures() {
        block[0] = blsheet.grabImage(1, 1, 32, 32);
        block[1] = blsheet.grabImage(1, 2, 32, 32);

        //Player is facing right.
        player[0] = plsheet.grabImage(1, 1, 32, 64); //Idle animation
        player[1] = plsheet.grabImage(2, 1, 32, 64); //Walking #1
        player[2] = plsheet.grabImage(3, 1, 32, 64); //Walking #2
        player[3] = plsheet.grabImage(4, 1, 32, 64); //Walking #3
        player[4] = plsheet.grabImage(5, 1, 32, 64); //Walking #4
        player[5] = plsheet.grabImage(6, 1, 32, 64); //Walking #5
        player[6] = plsheet.grabImage(7, 1, 32, 64); //Walking #6

        //Player is facing left.
        player[7] = plsheet.grabImage(20, 1, 32, 64); //Idle animation
        player[8] = plsheet.grabImage(19, 1, 32, 64); //Walking left #1
        player[9] = plsheet.grabImage(18, 1, 32, 64); //Walking left #2
        player[10] = plsheet.grabImage(17, 1, 32, 64); //Walking left #3
        player[11] = plsheet.grabImage(16, 1, 32, 64); //Walking left #4
        player[12] = plsheet.grabImage(15, 1, 32, 64); //Walking left #5
        player[13] = plsheet.grabImage(14, 1, 32, 64); //Walking left #6

        //Player is jumping.
        playerJump[0] = plsheet.grabImage(8, 2, 32, 64); //Jumping #1
        playerJump[1] = plsheet.grabImage(9, 2, 32, 64); //Jumping #2
        playerJump[2] = plsheet.grabImage(10, 2, 32, 64); //Jumping #3
        playerJump[3] = plsheet.grabImage(11, 2, 32, 64); //Jumping #4
        playerJump[4] = plsheet.grabImage(12, 2, 32, 64); //Jumping #5
        playerJump[5] = plsheet.grabImage(13, 2, 32, 64); //Jumping #6
    }
}
