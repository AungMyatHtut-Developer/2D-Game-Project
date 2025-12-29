package com.game.world;

import com.game.asset_helper.SpriteLoader;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Map {

    private SpriteLoader spriteLoader;
    private static final String MAP_BASE_PATH = "/map_resources/map/";
    private static final String MAP_EXTENSION = ".txt";
    private static final float SCALE = 2.0f;
    private static final float MAP_SPRITE_WIDTH = 16 * SCALE;
    private static final float MAP_SPRITE_HEIGHT = 16 * SCALE;
    private float MAP_WIDTH;
    private float MAP_HEIGHT;

    int mapNumber = 1;
    int[][] background;
    int[][] grass;
    List<Grass> grassList;

    public Map(SpriteLoader spriteLoader) {
        grassList = new ArrayList<>();
        this.spriteLoader = spriteLoader;
        background = loadMapData("background");
        grass = loadMapData("grass");

        for(int i = 0; i < grass.length; i++) {
            for(int j = 0; j < grass[i].length; j++) {
                grassList.add(new Grass(
                        (int) (i * MAP_SPRITE_WIDTH),
                        (int) (j * MAP_SPRITE_HEIGHT),
                        grass[i][j],
                        (int) ((j * MAP_SPRITE_HEIGHT) + MAP_SPRITE_HEIGHT),
                        (int) MAP_SPRITE_WIDTH,
                        (int) MAP_SPRITE_HEIGHT
                ));
            }
        }

        MAP_WIDTH = background[0].length * MAP_SPRITE_WIDTH;//columns
        MAP_HEIGHT = background.length * MAP_SPRITE_HEIGHT;//rows
    }


    public int[][] loadMapData(String mapName) {
        String fileName = "map" + mapNumber + "_" +mapName + MAP_EXTENSION;
        InputStream inputStream = getClass().getResourceAsStream(MAP_BASE_PATH + fileName);

        if(inputStream == null) {
            throw new RuntimeException("File not found: " + MAP_BASE_PATH + fileName);
        }

        int[][] map = new int[20][30];

        int row = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;

            while ( (line = br.readLine()) != null) {
                String[] values =  line.split(",");
                for(int col = 0; col < values.length; col++) {
                    map[row][col]  =  Integer.parseInt(values[col].trim());
                }
                row++;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public void render(Graphics graphics, int camX, int camY) {

        for(int j = 0; j < background.length; j++) {
            for(int i = 0; i < background[j].length; i++) {
                int index = background[j][i];
                if (index < 0) {
                    continue;
                }
                graphics.drawImage(spriteLoader.getMapSpriteByIndex(index),
                        (int) (i * MAP_SPRITE_WIDTH - camX),
                        (int) (j * MAP_SPRITE_HEIGHT - camY),
                        (int) MAP_SPRITE_WIDTH, (int) MAP_SPRITE_HEIGHT, null);
            }
        }
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public float getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public float getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }
}
