package help;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {
    /**
     * Rotacja spiritsów (o wielokrotności 90 stopni)
     * @param img Obraz obracany
     * @param rotAngle Stopnie rotacji
     * @return Obrócony obrazek
     */
    public static BufferedImage getRotImg(BufferedImage img, int rotAngle){
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), w/2, h/2 ); //koordynaty spirita wokół których rotacja
        g2d.drawImage(img, 0, 0,null);
        g2d.dispose();

        return newImg;
    }

    /**
     * Dodawawanie więcej niż jednego obrazka w jedym miejscu
     * @param imgs Tablica obiektów do nałożenia na siebie
     * @return Zwraca nowy obiekt (obrazek)
     */
    public static BufferedImage buildImg(BufferedImage[] imgs){
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for(BufferedImage i : imgs){
            g2d.drawImage(i, 0, 0, null);
        }
        g2d.dispose();
        return newImg;
    }

    /**
     * Rotacja tylko drugiego obrazka
     * @param imgs Tablica obiektów do nałożenia na siebie
     * @param rotAngle Stopnie rotacji
     * @param rotAtIndex Który indeks obracać
     * @return Obrócony obrazek
     */
    public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex){
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for(int i=0; i < imgs.length; i++){
            if(rotAtIndex == i)
                g2d.rotate(Math.toRadians(rotAngle), w/2, h/2 );
            g2d.drawImage(imgs[i],0,0,null);
            if(rotAtIndex == i)
                g2d.rotate(Math.toRadians(rotAngle), w/2, h/2 ); //obracamy ponownie "na miejsce", żeby następny obrazek nie był obrócony
        }
        g2d.dispose();
        return newImg;
    }
}
