package com.game.game_screens;

import com.game.window.GameWorld;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.game.constants.GameConstants.GAME_WIDTH;

public class PauseScreen {

    private GameWorld  gameWorld;
    private int x,y,width,height;

    private int buttonWidth = 100;
    private int buttonHeight = 30;

    private List<GameButton> gameButtons = new ArrayList<>();

    public PauseScreen(int x, int y, int width, int height, GameWorld gameWorld) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.gameWorld = gameWorld;

        gameButtons.add(new GameButton(100,100, buttonWidth,buttonHeight,"Resume"));
        gameButtons.add(new GameButton(100,150, buttonWidth,buttonHeight,"Options"));
        gameButtons.add(new GameButton(100,200, buttonWidth,buttonHeight,"Exit"));

    }

    public void render(Graphics g) {
        Color color = new Color(61, 59, 59,100);
        g.setColor(color);
        g.fillRect(x, y, width, height);

        for(GameButton gameButton : gameButtons) {
            int buttonX = (GAME_WIDTH / 2) - (gameButton.getWidth() / 2);
            gameButton.setX(buttonX);
            gameButton.render(g);
        }
    }

    public void updateButtons(int mouseX, int mouseY, boolean isClicked, boolean isReleased) {
        for(GameButton gameButton : gameButtons) {
            gameButton.updateHover(mouseX, mouseY);
            gameButton.updateClick(isClicked);

            if (gameButton.isClicked(isReleased)) {
                makeAction(gameButton);
            }
        }
    }

    private void makeAction(GameButton gameButton) {
        switch (gameButton.getText()){
            case "Resume" -> gameWorld.resumeGame();
            case "Option" -> System.out.println("Option Clicked");
            case "Exit" -> System.exit(0);
        }
    }
}
