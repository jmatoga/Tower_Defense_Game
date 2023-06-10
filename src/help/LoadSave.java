package help;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteResource() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/texture_pack_1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getImageBG() {
        BufferedImage img_bg = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/tlo.png");

        try {
            img_bg = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        return img_bg;
    }

    /**
     * Tworzenie pliku txt
     */
    public static void CreateFile(){
        File txtFile = new File("src/res/testTextFile.txt");

        try {
            txtFile.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Tworzenie nowego poziomu (jego zapis w pliku) na podstawie tablicy tekstur idArr
     * @param name Nazwa pliku z poziomem i pliku do którego będzie zapisywany
     * @param idArr Tablica z poziomem
     */
    public static void CreateLevel(String name, int[] idArr){
        File newLevel = new File("src/res/" + name + ".txt");

        if(newLevel.exists()){
            System.out.println("File: " + name + "already exists! ");
            return;
        }else{
            try {
                newLevel.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            WriteToFile(newLevel, idArr);
        }
    }

    /**
     * Publiczna metoda zapisująca poziom do pliku
     * @param f Plik z poziomem
     * @param idArr Tablica z poziomem
     * @return Zapisuje tablicę 1D z poziomem do pliku
     */
    private static void WriteToFile(File f, int[] idArr){

        //sprawdza czy plik istnieje
        try {
            PrintWriter pw = new PrintWriter(f);
            for(Integer i : idArr)
                pw.println(i);

            pw.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Publiczna metoda zapisująca edytowany poziom do pliku
     * @param name Plik z poziomem
     * @param idArr Tablica z poziomem
     * @return Zwraca ArrayListę z poziomem do wczytania
     */
    public static void SaveLevel(String name, int[][] idArr){
        File levelFile = new File("src/res/" + name + ".txt");

        if(levelFile.exists()){
            WriteToFile(levelFile,Utils.TwoDto1DintArr(idArr));
        }else{
            System.out.println("File: " + name + "doesn't exists! ");
            return;
        }
    }

    /**
     * Odczyt poziomu i zapis do listy
     * @param file Plik z poziomem
     * @return Zwraca ArrayListę z poziomem do wczytania
     */
    private static ArrayList<Integer> ReadFromFile(File file){
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Wczytywanie poziomu do gry (do tablicy 2D)
     * @param name Nazwa poziomu (i nazwa jego pliku)
     * @return tablica 2D z poziomem
     */
    public static int[][] GetLevelData(String name){
        File levelFile = new File("src/res/" + name + ".txt");

        if(levelFile.exists()){
            ArrayList<Integer> list = ReadFromFile(levelFile);
            return Utils.ArrayList2Dint(list,15,24);
        }else{
            System.out.println("File: " + name + "doesn't exists! ");
            return null;
        }
    }
}
