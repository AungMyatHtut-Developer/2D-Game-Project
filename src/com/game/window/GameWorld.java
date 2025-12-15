package com.game.window;

import java.awt.*;

public class GameWorld {

    //TODO Players, Enemies, Env Obj, Trees

    int speed = 5;
    int x = 0, y = 0;

    public GameWorld() {

    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(x,y, 50,50);

        // -> player.render(g); enemy.render(g)
    }

    public void update(float deltaTime) {
        //TODO to add player and enemy update logics
    }

    public void up() {
        y -= speed;
    }

    public void down() {
        y += speed;
    }

    public void left() {
        x -= speed;
    }

    public void right() {
        x += speed;
    }
}
