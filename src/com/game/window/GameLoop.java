package com.game.window;

public class GameLoop implements Runnable {

    private GamePanel gamePanel;
    private GameWorld gameWorld;
    private Thread gameThread;
    private boolean running;

    public GameLoop(GamePanel gamePanel, GameWorld gameWorld) {
        this.gamePanel = gamePanel;
        this.gameWorld = gameWorld;
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if(!running){
            return;
        }
        running = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {

        long targetFPS = 1_000_000_000/60;  // 60 -> 1B
        long startFPSTime = 0;
        long lastFPSCheckTime = 0;

        long targetUPS = 1_000_000_000/60;  // 60 -> 1B
        long startUPSTime = 0;
        long lastUPSCheckTime = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {

            startFPSTime = System.nanoTime();
            if(startFPSTime - lastFPSCheckTime >= targetFPS) {
                render();
                lastFPSCheckTime = startFPSTime;
                frames++;
            }

            startUPSTime = System.nanoTime();
            if(startUPSTime - lastUPSCheckTime >= targetUPS) {
                update();
                lastUPSCheckTime = startUPSTime;
            }

            if(System.currentTimeMillis() - timer > 1_000){
                System.out.println("FPS : "+ frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }
        }
    }

    public void update() {
        gameWorld.update();
    }

    public void render() {
        gamePanel.paintImmediately(0,0,gamePanel.getWidth(),gamePanel.getHeight());
    }
}
