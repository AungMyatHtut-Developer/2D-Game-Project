package com.game.asset_helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class SpriteLoader {

    private static final String BASE_PATH = "resources/img/";
    private static final String PLAYER_IMG = "player/Player";
    private static final String ENV_IMG = "map/Env";
    private static final String IMG_EXTENSION = ".png";
    private static final int MAP_FRAME_WIDTH = 16;
    private static final int MAP_FRAME_HEIGHT = 16;
    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 32;

    public final Map<ActionStore.PlayerAction, BufferedImage[]> playerSprites = new EnumMap<>(ActionStore.PlayerAction.class);
    public final BufferedImage[] map = new BufferedImage[234];

    public SpriteLoader() {
        loadPlayerSprites();
        loadMapSprites();
    }

    private void loadMapSprites() {
        BufferedImage sheet = loadImage(BASE_PATH + ENV_IMG + IMG_EXTENSION);
        int col = sheet.getWidth() / MAP_FRAME_WIDTH;// 13
        int row = sheet.getHeight() / MAP_FRAME_HEIGHT;// 18

        int track = 0;
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                map[track] = sheet.getSubimage(i * MAP_FRAME_WIDTH, j * MAP_FRAME_HEIGHT, MAP_FRAME_WIDTH, MAP_FRAME_HEIGHT);
                track++;
            }
        }

    }

    private void loadPlayerSprites() {
        BufferedImage sheet = loadImage(BASE_PATH + PLAYER_IMG + IMG_EXTENSION);

        for (ActionStore.PlayerAction action : ActionStore.PlayerAction.values()) {
            playerSprites.put(action, slice(sheet, action.getFrameCount(), action.ordinal(), FRAME_WIDTH, FRAME_HEIGHT));
        }

        System.out.println("Loaded " + playerSprites.size() + " sprites");
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image " + path, e);
        }
    }

    private BufferedImage[] slice(BufferedImage sheet, int column, int row, int frameWidth, int frameHeight) {
        BufferedImage[] frames = new BufferedImage[column];

        for (int i = 0; i < column; i++) {
            frames[i] = sheet.getSubimage(i * frameWidth, row * frameHeight, frameWidth, frameHeight);
        }

        return frames;
    }

    public BufferedImage[] getPlayerSprites(ActionStore.PlayerAction action) {
        return playerSprites.get(action);
    }

    public BufferedImage getMapSpriteByIndex(int index) {
        return map[index];
    }

}
