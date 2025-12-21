package com.game.window;

import com.game.asset_helper.SpriteLoader;
import com.game.entity.Player;
import com.game.world.Map;

import java.awt.*;

public class GameWorld {

    //TODO Players, Enemies, Env Obj, Trees

    private SpriteLoader spriteLoader;
    private Map map;
    private Player player;

    public GameWorld() {
        init();
    }

    public void init() {
        spriteLoader = new SpriteLoader();
        map = new Map(spriteLoader);
        player = new Player(10,10, 32,32, spriteLoader );
    }

    public void render(Graphics graphics) {

        // -> player.render(g); enemy.render(g)
        map.render(graphics);
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
