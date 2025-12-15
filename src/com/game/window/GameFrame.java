package com.game.window;

import javax.swing.*;
import static com.game.constants.GameConstants.*;

public class GameFrame {

    //Swing JFrame, JPanel , Canvas
     private JFrame jFrame;

     public GameFrame(GamePanel gamePanel) {
         jFrame = new JFrame(GAME_TITLE);
         jFrame.add(gamePanel);
         jFrame.pack();
         jFrame.setResizable(false);
         jFrame.setLocationRelativeTo(null);
         jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jFrame.setVisible(true);
     }

}
