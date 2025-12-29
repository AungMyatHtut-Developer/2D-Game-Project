package com.game.window;

import com.game.asset_helper.SpriteLoader;
import com.game.entity.Player;
import com.game.helpers.Camera;
import com.game.world.Grass;
import com.game.world.Map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.game.constants.GameConstants.*;

public class GameWorld {

    //TODO Players, Enemies, Env Obj, Trees

    private SpriteLoader spriteLoader;
    private Map map;
    private Player player;
    private Camera camera;

    public GameWorld() {
        init();
    }

    public void init() {
        spriteLoader = new SpriteLoader();
        map = new Map(spriteLoader);
        camera = new Camera(GAME_WIDTH, GAME_HEIGHT, (int) map.getMAP_WIDTH(), (int) map.getMAP_HEIGHT());
        player = new Player(10, 10, 32, 32, spriteLoader);
    }

    public void render(Graphics graphics) {

        // -> player.render(g); enemy.render(g)
        int camX = (int) camera.getX();
        int camY = (int) camera.getY();

        map.render(graphics, camX, camY);

        List<Object> renderingQueue = new ArrayList<>();
        renderingQueue.addAll(map.getGrassList());
        renderingQueue.add(player);

        renderingQueue.sort(ySortComparator());

        for (Object o : renderingQueue) {
            if (o instanceof Grass) {
                Grass grass = (Grass) o;
                if (grass.getSpriteInex() < 0) {
                    continue;
                }
                graphics.drawImage(spriteLoader.getMapSpriteByIndex(grass.getSpriteInex()),
                        grass.getWorldX() - camX,
                        grass.getWorldY() - camY,
                        grass.getWidth(),
                        grass.getHeight(),
                        null
                );
            } else if (o instanceof Player) {
                player.render(graphics, camX, camY);
            }
        }

    }

    public Comparator<Object> ySortComparator() {
        Comparator<Object> ySort = (a, b) -> {
            int depthA = (a instanceof Player) ? ((Player) a).getPlayerDepth() : ((Grass) a).getDepth();
            int depthB = (b instanceof Player) ? ((Player) b).getPlayerDepth() : ((Grass) b).getDepth();

            return Integer.compare(depthA, depthB);
        };

        return ySort;
    }

    public void update() {
        //TODO to add player and enemy update logics
        player.update();
        camera.follow(player);
    }

    public Player getPlayer() {
        return player;
    }
}
