package ui;

import java.awt.*;
import java.io.File;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class MyButton {
    public int x, y, width, height, id;
    String text;
    private Rectangle borders;
    private boolean mouseOver, mousePress;

    Font Inkfree;

    /*
    Funckja dla normalnych przycisków
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

    /*
    Funkcja dla kafelkowych przyckisków
     */
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        if (mouseOver) {
            g.setColor(Color.white);
            g.fillRect(x, y, width, height);
        }



    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();

        g.drawString(text, x - w / 2 + width / 2, y + height / 2);

        //TODO ogarnać to zeby działało vvvv
//        try {
//
//            String fontPath = "/res/Inkfree.ttf";
//
//            // Uzyskanie bezwzględnej ścieżki do pliku czcionki
//            String absoluteFontPath = getClass().getClassLoader().getResource(fontPath).getPath();
//            File fontFile = new File(absoluteFontPath);
//
//            // Ładowanie czcionki z pliku
//            Inkfree = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(10f);
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/res/Inkfree.ttf"))); //to w sumie nie iwem od czego jest
//
//            // Rysowanie tekstu
//            g.setFont(Inkfree);
//            g.drawString(text, x - w/2 + width/2, y + height/2);
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
    }

    public void resetBooleans() {
        this.mousePress = false;
        this.mouseOver = false;
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

    public boolean isMousePress() {
        return mousePress;
    }
}
