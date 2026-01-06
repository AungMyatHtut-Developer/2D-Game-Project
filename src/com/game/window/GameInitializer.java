package com.game.window;

import com.game.input_handler.KeyboardHandler;
import com.game.input_handler.MouseHandler;

public class GameInitializer {

    private GameWorld gameWorld;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private GameLoop gameLoop;

    private KeyboardHandler keyboardHandler;
    private MouseHandler mouseHandler;

    public GameInitializer() {
        gameWorld = new GameWorld();
        gamePanel = new GamePanel(gameWorld);
        gameFrame = new GameFrame(gamePanel);

        keyboardHandler = new KeyboardHandler(gameWorld);
        gamePanel.addKeyListener(keyboardHandler);

        mouseHandler = new MouseHandler(gameWorld);
        gamePanel.addMouseListener(mouseHandler);
        gamePanel.addMouseMotionListener(mouseHandler);

        gameLoop = new GameLoop(gamePanel, gameWorld);
        gameLoop.start();

    }


}
