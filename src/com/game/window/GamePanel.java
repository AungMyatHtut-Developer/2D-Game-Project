package com.game.window;


import javax.swing.*;
import java.awt.*;
import static com.game.constants.GameConstants.*;

public class GamePanel extends JPanel {

    private GameWorld gameWorld;

    public GamePanel(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //gameWorld -> render();
        gameWorld.render(g);
    }

}
