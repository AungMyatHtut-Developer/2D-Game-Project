package com.game.helpers;

import com.game.entity.Player;

public class Camera {

    private float x, y;
    private int screenWidth, screenHeight;
    private int worldWidth, worldHeight;

    public Camera(int screenWidth, int screenHeight, int worldWidth, int worldHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    //follow along the player
    public void follow(Player player) {
        //game logics
        x = (player.getX() + player.getWidth() / 2) - (screenWidth / 2);
        y = (player.getY() + player.getHeight() / 2) - (screenHeight / 2);

        clampToWorld();
    }

    //screen clamp
    private void clampToWorld() {

        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }

        if (x > worldWidth - screenWidth) {
            x = worldWidth - screenWidth;
        }

        if (y > worldHeight - screenHeight) {
            y = worldHeight - screenHeight;
        }
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
