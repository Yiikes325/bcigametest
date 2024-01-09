package Objects;

import GameFrame.GameObject;
import GameFrame.ObjectId;
import GameFrame.Textures;
import Output.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player extends GameObject {
    private float width = 48, height = 96;

    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;

    private Game game;
    private GameHandler handler;
    private GameCamera camera;
    private BufferedImage normalLevel = null;
    private BufferedImage hardLevel = null;

    Textures textures = Game.getInstance();

    private Animations playerWalking, playerWalkingLeft;

    public Player (float x, float y, GameHandler handler, GameCamera camera, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        playerWalking = new Animations(5, textures.player[1], textures.player[2], textures.player[3], textures.player[4], textures.player[5],textures.player[6]);
        playerWalkingLeft = new Animations(5, textures.player[8], textures.player[9], textures.player[10], textures.player[11], textures.player[12], textures.player[13]);
    }

    public void tick(LinkedList<GameObject> objects) {
        x += velocityX;
        y += velocityY;

        if (velocityX < 0) {
            facing = -1;
        }
        else if (velocityX > 0) {
            facing = 1;
        }

        if (fall || jump) {
            velocityY += gravity;
            if (velocityY > MAX_SPEED) {
                velocityY = MAX_SPEED;
            }
        }
        Collision(objects);
        playerWalking.runAnimations();
        playerWalkingLeft.runAnimations();
    }

    private void Collision(LinkedList<GameObject> objects) {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ObjectId.GameBlock) {
                //Top collision
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velocityY = 0;
                }
                //Bottom collision
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velocityY = 0;
                    fall = false;
                    jump = false;
                }
                else {
                    fall = true;
                }
                //Right collision
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                }
                //Left collision
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 35;
                }
            }
            else if (tempObject.getId() == ObjectId.NextLevel) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.clearLevel();
                    ImageLoader imageLoader = new ImageLoader();
                    normalLevel = imageLoader.loadGameImage("/normallevel.png");
                    handler.LoadImageLevel(normalLevel);
                }
            }
            else if (tempObject.getId() == ObjectId.NextLevel2) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.clearLevel();
                    ImageLoader imageLoader = new ImageLoader();
                    hardLevel = imageLoader.loadGameImage("/hardlevel.png");
                    handler.LoadImageLevel(hardLevel);
                }
            }
            else if (tempObject.getId() == ObjectId.EndLevel) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.clearLevel();
                    System.out.println("All 3 levels have been cleared! Congratulations! If you wish to try again, press play.");
                    game.gameState = Game.STATE.Menu;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        //g.fillRect((int)x, (int)y, (int)width, (int)height);
        if (jump) {
            if (facing == 1) {
                g.drawImage(textures.playerJump[2], (int) x, (int) y, 48, 96, null);
            }
            else if (facing == -1) {
                g.drawImage(textures.playerJump[3], (int) x, (int) y, 48, 96, null);
            }
        }
        else {
            if (velocityX != 0) {
                if (facing == 1) {
                    playerWalking.drawAnim(g, (int) x, (int) y, 48, 96);
                } else {
                    playerWalkingLeft.drawAnim(g, (int) x, (int) y, 48, 96);
                }
            } else {
                if (facing == 1) {
                    g.drawImage(textures.player[0], (int) x, (int) y, 48, 96, null);
                } else if (facing == -1) {
                    g.drawImage(textures.player[7], (int) x, (int) y, 48, 96, null);
                }
            }
        }
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (width / 2) - ((width / 2) / 2)), (int)y, (int)width / 2, (int)height / 2);
    }
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (width / 2) - ((width / 2) / 2)), (int) ((int)y + (height /2 )), (int)width / 2, (int)height / 2);
    }

}
