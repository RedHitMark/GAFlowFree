package com.company;

import com.company.GUI.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        /*FlowFreeAlgo flowFreeAlgo = new FlowFreeAlgo();

        Point[] points = new Point[10];
        points[0]=new Point(0,0);
        points[1]=new Point(1,4);
        points[2]=new Point(2,0);
        points[3]=new Point(1,3);
        points[4]=new Point(2,1);
        points[5]=new Point(2,4);
        points[6]=new Point(4,0);
        points[7]=new Point(3,3);
        points[8]=new Point(4,1);
        points[9]=new Point(3,4);



        flowFreeAlgo.runAlgorithm(populationSize, numberOfGenes, n, points);*/
    }

    /*


    // Find Longest Possible Route in a Matrix mat from source cell (0, 0) to
    // destination cell (x, y)
    // max_dist is passed by reference and stores length of longest path from
    // source to destination found so far dist maintains length of path from
    // source cell to current cell (i, j)
    static int findLongestPath(int[][] mat, boolean[][] visited, int i, int j, int x, int y, int token, int max_dist, int dist) {
        // destination not possible from current cell
        if (mat[i][j] != token) {
            return 0;
        }

        // if destination is found, update max_dist
        if (i == y && j == x) {
            return Math.max(dist, max_dist);
        }

        // set (i, j) cell as visited
        visited[i][j] = true;

        // go to bottom cell
        if (isValid(i + 1, j, mat.length) && isSafe(mat, visited, token, i + 1, j)) {
            return findLongestPath(mat, visited, i + 1, j, x, y, token, max_dist, dist + 1);
        }
        // go to right cell
        if (isValid(i, j + 1, mat.length) && isSafe(mat, visited, token, i, j + 1)) {
            return findLongestPath(mat, visited, i, j + 1, x, y, token, max_dist, dist + 1);
        }

        // go to top cell
        if (isValid(i - 1, j, mat.length) && isSafe(mat, visited, token, i - 1, j)) {
            return findLongestPath(mat, visited, i - 1, j, x, y, token, max_dist, dist + 1);
        }

        // go to left cell
        if (isValid(i, j - 1, mat.length) && isSafe(mat, visited, token, i, j - 1)) {
            return findLongestPath(mat, visited, i, j - 1, x, y, token, max_dist, dist + 1);
        }

        return 0;
    }

    // check if it is possible to go to position (x, y) from
    // current position. The function returns false if the cell
    // has value 0 or it is already visited.
    static boolean isSafe(int[][] mat, boolean[][] visited, int token, int x, int y) {
        return mat[x][y] == token && !visited[x][y];
    }

    // if not a valid position, return false
    static boolean isValid(int x, int y, int n) {
        return x < n && y < n && x >= 0 && y >= 0;
    }*/
}
