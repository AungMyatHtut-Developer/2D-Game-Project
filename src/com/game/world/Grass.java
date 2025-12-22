package com.game.world;

public class Grass {

    private int worldX, worldY;
    private int width, height;
    private int spriteInex;
    private int depth;

    public Grass(int worldX, int worldY, int spriteInex, int depth, int width, int height) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.spriteInex = spriteInex;
        this.depth = depth;
        this.width = width;
        this.height = height;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpriteInex() {
        return spriteInex;
    }

    public int getDepth() {
        return depth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
