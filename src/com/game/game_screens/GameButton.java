package com.game.game_screens;

import java.awt.*;

public class GameButton {

    private int x,y,width,height;
    private String text;

    private boolean isHovered;
    private boolean isClicked;

    public GameButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void render(Graphics g) {
        if(isHovered){
            g.setColor(new Color(45, 44, 44,255));
        }else{
            g.setColor(new Color(23,23,23,255));
        }

        g.fillRect(x, y, width, height);

        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);

        //text width
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        System.out.println(textHeight);

        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - textHeight) / 2 + fm.getAscent();

        g.drawString(text, textX, textY);
    }

    public void updateHover(int mouseX, int mouseY) {
        isHovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void updateClick(boolean isClicked) {
        if (isHovered && isClicked) {
            this.isClicked =  true;
        }
    }

    public boolean isClicked(boolean isReleased) {
        if(isHovered && isClicked && isReleased) {
            isClicked = false;
            return true;
        }

        if(!isReleased){
            return false;
        }

        isClicked = false;
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }



}
