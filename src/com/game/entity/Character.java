package com.game.entity;

import java.awt.*;

public interface Character {
    default void render(Graphics g){};
    void render(Graphics g, int camX, int camY);
    void update();
}
