package ui;

import java.awt.*;
import java.io.File;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class MyButton {
    public int x, y, width, height, id;
    String text;
    int type;
    private Rectangle borders;
    private boolean mouseOver, mousePress;
    private Font Inkfree;

    /**
     * Funckja dla normalnych przycisków
     */
    public MyButton(String text, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = -1;

        setBorders();
    }

    /**
     * Funkcja dla kafelkowych przycisków
     */
    public MyButton(int type, int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.id = id;

        setBorders();
    }

    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = -1;
        this.text = text;
        this.id = id;

        setBorders();
    }

    public void setBorders() {
        this.borders = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        //body
        drawBody(g);

        //bordes
        drawBorder(g);

        //text
        drawText(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);

        if (mousePress) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    private void drawBody(Graphics g) {
        g.setColor(Color.decode("0x7f5415"));
        g.fillRect(x, y, width, height);

        if (mouseOver) {
            g.setColor(Color.white);
            g.fillRect(x, y, width, height);
        }
    }

    private void drawText(Graphics g) {
        try {
            Inkfree = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/PixelMplus12-Regular.ttf")).deriveFont(Font.BOLD, 30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.setFont(Inkfree);
        g.setColor(Color.RED);
        g.drawString(text, x - w/2 + width/2, y + height/2);
    }

    public void resetBooleans() {
        this.mousePress = false;
        this.mouseOver = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePress(boolean mousePress) {
        this.mousePress = mousePress;
    }

    public Rectangle getBorders() {
        return borders;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePress;
    }
}
