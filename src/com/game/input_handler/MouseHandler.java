package com.game.input_handler;

import com.game.window.GameWorld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private GameWorld gameWorld;

    public MouseHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameWorld.clickButton(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameWorld.releaseButton(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameWorld.updateButtons(e.getX(), e.getY());
    }
}
