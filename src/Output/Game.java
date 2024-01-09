package Output;

import GameFrame.Keybinds;
import GameFrame.ObjectId;
import GameFrame.Textures;
import Objects.GameBlock;
import Objects.NextLevel;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int WIDTH, HEIGHT;
    private BufferedImage easyLevel = null, background = null;

    private Thread thread;
    private boolean running = false;

    GameHandler handler;
    GameCamera camera;
    static Textures textures;

    Random random = new Random();
    public static int LEVEL = 1;
    private Menu menu;

    public enum STATE {
        Menu,
        Game,
        Help,
        End
    }

    public static STATE gameState = STATE.Menu;

    public synchronized void start() {
        if (running) {
            return;
        }

        menu = new Menu(this, handler);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        textures = new Textures();

        this.addMouseListener(menu);
        camera = new GameCamera(0,0);
        handler = new GameHandler(camera);

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void init() {
        ImageLoader imageLoader = new ImageLoader();
        easyLevel = imageLoader.loadGameImage("/easylevel.png"); //loads the first level, the easy level.
        background = imageLoader.loadGameImage("/sky.png");
        handler.LoadImageLevel(easyLevel);

        //handler.addObject(new Player(100, 100, handler, ObjectId.Player)); //Testing to see if the player and map loads.
        //handler.levelCreate();
        this.addKeyListener(new Keybinds(handler));
    }


    public void run() {
        //init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double numberOfTicks = 60.0;
        double ns = 1000000000 / numberOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / ns;
            lastTime = currentTime;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Frames: " + frames + " Ticks: " + updates); //Checks to see the current frames per second, as well as the server ticks.
                frames = 0;
                updates = 0;
            }
        }
    }

    public void tick() {
        if (gameState == STATE.Game) {
            handler.tick();
            for (int i = 0; i < handler.objects.size(); i++) {
                if (handler.objects.get(i).getId() == ObjectId.Player) {
                    camera.tick(handler.objects.get(i)); //Ensures that the camera follows the player's movements.
                }
            }
        }
        else if(gameState == STATE.Menu) {
            menu.tick();
        }
        else if (gameState == STATE.End) {
            handler.objects.clear();
        }
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy(); //Canvas
        if (bufferStrategy == null) { //Only creates the buffer strategy for the first time.
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) g; //Camera controls

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        if (gameState == STATE.Game) {
            graphics2D.translate(camera.getX(), camera.getY());
            handler.render(g);
            graphics2D.translate(-camera.getX(), -camera.getY());
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.render(g);
        }

        //g.setColor(Color.black);
        //g.fillRect(0, 0, getWidth(), getHeight());

        //graphics2D.translate(camera.getX(), camera.getY());
        //handler.render(g);
        //graphics2D.translate(-camera.getX(), -camera.getY()); //The camera moves the map so that it can only follow where the player is going.

        g.dispose();
        bufferStrategy.show();
    }

    public static Textures getInstance() {
        return textures;
    }

    public static void main(String args[]) {
        new GameScreen(800, 600, "CE301 Individual Capstone Project", new Game());
    }
}
