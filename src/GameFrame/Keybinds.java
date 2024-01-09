package GameFrame;

import Output.GameHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keybinds extends KeyAdapter {

    GameHandler handler;

    public Keybinds (GameHandler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ObjectId.Player) {
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelocityX(5);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelocityX(-5);
                }
                if (key == KeyEvent.VK_SPACE && !tempObject.isJump()) {
                    tempObject.setJump(true);
                    tempObject.setVelocityY(-15);
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) { //Esc key exits the game, designed for KB/M only.
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ObjectId.Player) {
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelocityX(0);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelocityX(0);
                }
            }
        }
    }
}
