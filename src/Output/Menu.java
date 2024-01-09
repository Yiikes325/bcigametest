package Output;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private GameHandler handler;
    private int gameCounter = 0;

    public Menu (Game game, GameHandler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mouseX, mouseY, 300, 150, 200, 64)) { //Play the game.
                game.gameState = Game.STATE.Game;
                game.init();
                gameCounter++;
            }
            if (mouseOver(mouseX, mouseY, 300, 350, 200, 64)) { //Quit the program.
                System.exit(1);
            }
            if (mouseOver(mouseX, mouseY, 300, 250, 200, 64)) { //Help section.
                game.gameState = Game.STATE.Help;

            }

        }
        else if (game.gameState == Game.STATE.Help) {
           if (mouseOver(mouseX, mouseY, 300, 350, 200, 64)) {
               game.gameState = Game.STATE.Menu;
               return;
           }
        }
        else if (game.gameState == Game.STATE.End) {
            if (mouseOver(mouseX, mouseY, 220, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        if (mouseX > x && mouseX < x + width) {
            if (mouseY > y && mouseY < y + height) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        if (game.gameState == Game.STATE.Menu) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 30);
            g.setFont(font);

            g.setColor(Color.white);
            g.drawString("BCI Platform Game", 180, 80);

            g.setFont(font2);
            g.drawRect(300, 150, 200, 64);
            g.drawString("Play", 370, 193);

            g.drawRect(300, 250, 200, 64);
            g.drawString("Help", 370, 293);

            g.drawRect(300, 350, 200, 64);
            g.drawString("Quit", 370, 393);
        }

        else if (game.gameState == Game.STATE.Help) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial",1, 15);
            g.setFont(font);

            g.setColor(Color.white);
            g.drawString("Help", 350, 80);

            g.setFont(font2);
            g.drawString("For keyboard and mouse input, use A and D to move, and the spacebar to jump.", 40, 120);
            g.drawString("For BCI users, please run the TCPClient class, as well as OpenVibe's Acquisition Server.", 40, 150);


            Font font3 = new Font("arial", 1, 30);
            g.setFont(font3);

            g.drawRect(300, 350, 200, 64);
            g.drawString("Back", 370, 393);
        }

        else if (game.gameState == Game.STATE.End)	{
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 30);
            //Font font3 = new Font("arial", 1, 20);

            g.setFont(font);

            g.setColor(Color.white);
            g.drawString("Game over!", 190, 70);


            g.setFont(font2);
            g.drawString("You lost. ", 110, 200);

            //g.setFont(font3);
            g.drawRect(220, 350, 200, 64);
            g.drawString("Please try again", 255, 393);

        }
    }

    public void tick() {

    }
}
