package com.game.window;

import com.game.asset_helper.SpriteLoader;
import com.game.entity.Player;

import java.awt.*;

public class GameWorld {

    //TODO Players, Enemies, Env Obj, Trees

    private SpriteLoader spriteLoader;
    private Player player;

    public GameWorld() {
        init();
    }

    public void init() {
        spriteLoader = new SpriteLoader();
        player = new Player(10,10, 32,32, spriteLoader );
    }

    public void render(Graphics graphics) {

        // -> player.render(g); enemy.render(g)
        player.render(graphics);
    }

    public void update(float deltaTime) {
        //TODO to add player and enemy update logics
        player.update(deltaTime);
    }

    public Player getPlayer() {
        return player;
    }
}
