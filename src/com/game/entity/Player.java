package com.game.entity;

import static com.game.asset_helper.ActionStore.*;
import com.game.asset_helper.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player implements Character {

    private Color color;
    private float x, y, width, height;
    private float baseSpeed = 4.0f;
    private boolean isLeft, isRight, isUp, isDown;
    private boolean isMoving = false;
    private boolean isFacingLeft = false;
    private boolean isDead = false;
    private float scale = 2.0f;
    private PlayerAction playerAction;
    private final SpriteLoader spriteLoader;
    private int animationIndex;
    private int animationTick;
    private boolean isFinalDead;

    public Player(float x, float y, float width, float height, SpriteLoader spriteLoader) {
        playerAction = PlayerAction.IDLE_RIGHT;
        this.spriteLoader = spriteLoader;
        this.color = Color.RED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BufferedImage[] getCurrentActionAnimationFrames() {
        return spriteLoader.getPlayerSprites(playerAction);
    }

    public float getCurrentAnimationSpeed() {
        return playerAction.getFrameSpeed();
    }

    @Override
    public void render(Graphics g) {
        if(isFinalDead) return;

        BufferedImage[] frames = getCurrentActionAnimationFrames();
        g.drawImage(frames[animationIndex], (int) x, (int) y, (int) (width * scale), (int) (height * scale), null);
    }

    @Override
    public void update() {
        move();
        updatePlayerAction();
        animatePlayer();
    }

    private void changePlayerAction(PlayerAction playerAction) {
        if(this.playerAction == playerAction) return;
        animationIndex = 0;
        this.playerAction = playerAction;
    }

    private void updatePlayerAction() {

        if (isDead) {
            changePlayerAction(PlayerAction.DIE);
            return;
        }

        if (isDown && isMoving) {
            changePlayerAction(PlayerAction.WALK_DOWN);
            return;
        } else if (isUp && isMoving) {
            changePlayerAction(PlayerAction.WALK_UP);
            return;
        }

        if(isFacingLeft && isMoving) {
            changePlayerAction(PlayerAction.WALK_LEFT);
            return;
        } else if (!isFacingLeft && isMoving){
            changePlayerAction(PlayerAction.WALK_RIGHT);
            return;
        }

        if(isFacingLeft) {
            changePlayerAction(PlayerAction.IDLE_LEFT);
        }else{
            changePlayerAction(PlayerAction.IDLE_RIGHT);
        }
    }

    public void animatePlayer() {
        if(animationTick > getCurrentAnimationSpeed()) {
            animationIndex++;
            if(animationIndex >= playerAction.getFrameCount()){
                if(animationIndex == 10 && isDead){
                    animationIndex = 9;
                    isFinalDead = true;
                }else{
                    animationIndex = 0;
                }
            }
            animationTick = 0;
        }else{
            animationTick++;
        }
    }

    public void move() {
        isMoving = false;
        if (isDead) {
            return;
        }

        //TODO to calculate diagonal movement
        float movementSpeed = 0;
        if((isLeft && isUp) || (isLeft && isDown) || (isRight && isUp) || (isRight && isDown)) {
           movementSpeed = (float) (baseSpeed / Math.sqrt(2));
        }else{
            movementSpeed = baseSpeed;
        }

        if (isLeft && !isRight) {
            x -= movementSpeed;
            isFacingLeft = true;
            isMoving = true;
        } else if (isRight && !isLeft) {
            x += movementSpeed;
            isFacingLeft = false;
            isMoving = true;
        }

        if (isUp && !isDown) {
            y -= movementSpeed;
            isMoving = true;
        } else if (isDown && !isUp) {
            y += movementSpeed;
            isMoving = true;
        }

    }

    public void isLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void isRight(boolean isRight) {
        this.isRight = isRight;
    }

    public void isUp(boolean isUP) {
        this.isUp = isUP;
    }

    public void isDown(boolean isDown) {
        this.isDown = isDown;
    }

    public void dead(boolean isDead) {
        this.isDead = isDead;
    }

}
