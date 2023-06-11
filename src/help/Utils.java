package help;

import java.util.ArrayList;

public class Utils {

    /**
     * Przeniesienie wartości z ArrayListy do dwuwymiarowej tablicy
     * @param list  ArrayLista zawierająca tablicę z poziomem
     * @param ySize Liczba kolumn
     * @param xSize Liczba wierszy
     * @return Dwuwymiarowa tablica poziomu
     */
    public static int[][] ArrayList2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArr = new int[ySize][xSize];

        for (int j = 0; j < newArr.length; j++) {
            for (int i = 0; i < newArr[j].length; i++) {
                int index = j * xSize + i;
                newArr[j][i] = list.get(index);
            }
        }
        return newArr;
    }

    /**
     * Przeniesienie wartości z dwuwymiarowej tablicy do jednowymiarowej
     * @param twoArr  Dwuwymiarowa tablica
     * @return Jednowymiarowa tablica poziomu
     */
    public static int[] TwoDto1DintArr(int[][] twoArr) {
        int[] oneArr = new int[twoArr.length * twoArr[0].length];

        for (int j = 0; j < twoArr.length; j++) {
            for (int i = 0; i < twoArr[j].length; i++) {
                int index = j * twoArr[j].length + i;
                oneArr[index] = twoArr[j][i];
            }
        }
        return oneArr;
    }

    public static int GetHypoDistance(float x1, float y1, float x2, float y2){
        float xDiff = Math.abs(x1-x2);
        float yDiff = Math.abs(y1-y2);

        return (int) Math.hypot(xDiff, yDiff);
    }
}
