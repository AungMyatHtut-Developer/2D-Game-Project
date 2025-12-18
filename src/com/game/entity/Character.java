package com.game.entity;

import java.awt.*;

public interface Character {
    void render(Graphics g);
    void update(float deltaTime);
}
