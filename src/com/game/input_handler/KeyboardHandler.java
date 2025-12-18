package com.game.input_handler;

import com.game.window.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    public GameWorld gameWorld;

    public KeyboardHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> gameWorld.getPlayer().isUp(true);
            case KeyEvent.VK_DOWN -> gameWorld.getPlayer().isDown(true);
            case KeyEvent.VK_LEFT -> gameWorld.getPlayer().isLeft(true);
            case KeyEvent.VK_RIGHT -> gameWorld.getPlayer().isRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> gameWorld.getPlayer().isUp(false);
            case KeyEvent.VK_DOWN -> gameWorld.getPlayer().isDown(false);
            case KeyEvent.VK_LEFT -> gameWorld.getPlayer().isLeft(false);
            case KeyEvent.VK_RIGHT -> gameWorld.getPlayer().isRight(false);
        }
    }
}
