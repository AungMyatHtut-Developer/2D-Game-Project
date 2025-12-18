package com.game.asset_helper;

public class ActionStore {

    public enum PlayerAction{
        IDLE_LEFT(5, 0.5f),
        IDLE_RIGHT(5, 0.5f),
        WALK_LEFT(4, 0.5f),
        WALK_RIGHT(4, 0.5f),
        WALK_DOWN(8, 0.5f),
        WALK_UP(8, 0.5f),
        HURT(2, 0.3f),
        DIE(10, 0.5f);

        private final int frameCount;
        private final float frameSpeed;

        PlayerAction(int frameCount, float frameSpeed) {
            this.frameCount = frameCount;
            this.frameSpeed = frameSpeed;
        }

        public int getFrameCount() {
            return frameCount;
        }

        public float getFrameSpeed() {
            return frameSpeed;
        }
    }

}
