package com.color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author hrajapaksha2
 * @since 7/3/2022
 */
public class GenerateColorGrid {
    public static String[][] getColorGrid(int row, int col, int colorCount) {

        String[][] colorGrid = new String[row][col];

        ArrayList<String> colors = new ArrayList<String>();
        for (int i = 0; i < colorCount; i++)
            colors.add(randomColor().toString());

        for (int i = 0; i < colorGrid.length; i++)
            for (int j = 0; j < colorGrid[i].length; j++) {
                int a = (int) (Math.random() * colorCount);
                colorGrid[i][j] = colors.get(a);
            }
        return colorGrid;
    }

    /**
     * @return
     * Random color generate
     */
    private static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

}
