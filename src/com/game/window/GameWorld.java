package com.game.window;

import com.game.asset_helper.SpriteLoader;
import com.game.entity.Player;
import com.game.world.Grass;
import com.game.world.Map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

        List<Object> renderingQueue = new ArrayList<>();
        renderingQueue.addAll(map.getGrassList());
        renderingQueue.add(player);

        renderingQueue.sort(ySortComparator());

        for(Object o : renderingQueue) {
            if(o instanceof Grass) {
                Grass grass = (Grass) o;
                if (grass.getSpriteInex() < 0) {
                    continue;
                }
                graphics.drawImage(spriteLoader.getMapSpriteByIndex(grass.getSpriteInex()),
                        grass.getWorldX(),
                        grass.getWorldY(),
                        grass.getWidth(),
                        grass.getHeight(),
                        null
                        );
            } else if (o instanceof Player) {
                player.render(graphics);
            }
        }

    }

    public Comparator<Object> ySortComparator() {
        Comparator<Object> ySort = (a , b) ->{
            int depthA = (a instanceof Player) ? ((Player) a).getPlayerDepth() : ((Grass) a).getDepth();
            int depthB = (b instanceof Player) ? ((Player) b).getPlayerDepth() : ((Grass) b).getDepth();

            return Integer.compare(depthA, depthB);
        };

        return ySort;
    }

    public void update() {
        //TODO to add player and enemy update logics
        player.update();
    }

    public Player getPlayer() {
        return player;
    }
}
