package com.color;

/**
 * @author hrajapaksha2
 * @since 7/3/2022
 */
public class ColorGridChallenge {

    private static final String BLANK = "...............................";
    static int ROW;
    static int COL;

    // original color grid
    static String[][] colorGrid;

    // temporary array for check whether visited or not
    static int[][] VISITED_LIST;

    // Final result Array
    static String[][] RESULT_LIST;

    static int COUNT;

    public static void main(String[] args) {
        ROW = 6;
        COL = 8;
        int colorCount = 3;
        //  initialize arrays
        VISITED_LIST = new int[ROW][COL];
        RESULT_LIST = new String[ROW][COL];
        colorGrid = GenerateColorGrid.getColorGrid(ROW, COL, colorCount);

        int maxValue = findContinuousColors();
        printColorArray();
        printResults(maxValue);

    }

    /**
     * @param maxValue
     * Print result grid with maximun count
     */
    private static void printResults(int maxValue) {
        System.out.println();
        System.out.println("################################################################");
        System.out.println("#####################  RESULT ##################################");
        System.out.println("################################################################");
        System.out.println();
        System.out.println("Maximum count : " + maxValue);

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                    System.out.print(RESULT_LIST[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return
     * Initialize the searching process
     */
    private static int findContinuousColors() {
        int currentMaxCount = Integer.MIN_VALUE;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                COUNT = 0;
                resetVisitArray();

                if (j + 1 < COL) {
                    traverse(colorGrid[i][j], colorGrid[i][j + 1], i, j);
                }
                if (currentMaxCount <= COUNT) {
                    currentMaxCount = COUNT;
                    resetResultArray(colorGrid[i][j]);
                }
            }
        }
        return currentMaxCount;
    }

    /**
     * @param x current value
     * @param y j+1 value
     * @param i
     * @param j
     * Grid traverse using Breadth First Search algorithm
     */
    private static void traverse(String x, String y, int i, int j) {
        if (!x.equals(y)) {
            return;
        }
        COUNT++;
        VISITED_LIST[i][j] = 1;
//        When we are using Breadth First Search, there are maximum 4 possible positions available(minimum 2)
        int[] horizontal_x = {-1, 1, 0, 0};
        int[] vertical_y = {0, 0, -1, 1};

        for (int a = 0; a < 4; a++) {
            if (isValidCell(i + horizontal_x[a], j + vertical_y[a], colorGrid[i][j])) {
                traverse(x, y, i + horizontal_x[a], j + vertical_y[a]);
            }
        }
    }

    /**
     * @param p
     * @param q
     * @param color //check it is available and not visited
     *              and color code is same
     */

    private static boolean isValidCell(int p, int q, String color) {
        return p >= 0 && q >= 0 && p < ROW && q < COL && (VISITED_LIST[p][q] == 0 && colorGrid[p][q].equals(color));

    }

    /**
     * @param color
     * Update result array after finding new mac COUNT
     */
    private static void resetResultArray(String color) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (VISITED_LIST[i][j]==1 &&
                        colorGrid[i][j].equalsIgnoreCase(color)){
                    RESULT_LIST[i][j] = color;
                }else{
                    RESULT_LIST[i][j]=BLANK;
                }
            }
        }
    }

    /**
     * Reset visit array for new iteration
     */
    private static void resetVisitArray() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                VISITED_LIST[i][j] = 0;
    }

    /**
     * Initially generated color grid
     */
    private static void printColorArray() {
        System.out.println();
        System.out.println("################################################################");
        System.out.println("#####################  INPUT COLOR GRID  #######################");
        System.out.println("################################################################");
        System.out.println();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(colorGrid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
