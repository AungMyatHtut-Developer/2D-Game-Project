package com.game.entity;

import com.game.asset_helper.ActionStore;
import com.game.asset_helper.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player implements Character {

    private Color color;
    private float x, y, width, height;
    private float movementSpeed = 100f;
    private boolean isLeft, isRight, isUP, isDown;
    private boolean isMoving = false;
    private boolean isFacingLeft = true;
    private boolean isDead = false;
    private float scale = 2.0f;

    private final SpriteLoader spriteLoader;

    public Player(float x, float y, float width, float height, SpriteLoader spriteLoader) {
        this.spriteLoader = spriteLoader;
        this.color = Color.RED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int animationCount = 0;
    int i = 0;

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, (int) width, (int) height);

        BufferedImage[] image = spriteLoader.getPlayerSprites(ActionStore.PlayerAction.IDLE_LEFT);

        g.drawImage(image[i], (int) x, (int) y, (int) (width * scale), (int) (height * scale), null);

        if (animationCount > 40) {
            if (i > 3) {
                i = 0;
            } else {
                i++;
            }
            animationCount = 0;
        } else {
            animationCount++;
        }

    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
    }

    public void move(float deltaTime) {

        //TODO to calculate diagonal movement
        if (isLeft && !isRight) {
            x -= movementSpeed * deltaTime;
        } else if (isRight && !isLeft) {
            x += movementSpeed * deltaTime;
        }

        if (isUP && !isDown) {
            y -= movementSpeed * deltaTime;
        } else if (isDown && !isUP) {
            y += movementSpeed * deltaTime;
        }
    }

    public void isLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void isRight(boolean isRight) {
        this.isRight = isRight;
    }

    public void isUp(boolean isUP) {
        this.isUP = isUP;
    }

    public void isDown(boolean isDown) {
        this.isDown = isDown;
    }

    public void stopMovements() {
        this.isLeft = false;
        this.isRight = false;
        this.isUP = false;
        this.isDown = false;
    }

}
