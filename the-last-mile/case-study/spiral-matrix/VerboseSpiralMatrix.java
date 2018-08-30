package org.shoupu.matrix;

import java.util.ArrayList;

/**
 * This is an example to be criticized on.
 * The problem is a pure coding problem: algorithmic wise, it's very simple, just go and turn around
 * as needed.
 * But implementation wise, it's not easy to implement gracefully-free from duplicate code, simple,
 * and more importantly, easy to read.
 * This implementation is an earlier implementation I myself labored.
 * Thought it works, it is very verbose.
 */
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                m[i][j] = 1 + getRank(n, i, j);
        return m;
    }

    int getRank(int n, int i, int j) {
        if (n == 1) return 0;
        int layerNum = min(i, n - 1 - i, j, n - 1 - j);
        int outLayerRank = sumProgression(4 * (n - 1), -8, layerNum);
        int inLayerRank = getInLayerRank(n, i, j);
        return outLayerRank + inLayerRank;
    }

    int sumProgression(int start, int diff, int num) {
        return start * num + diff * (num - 1) * num / 2;
    }

    int getInLayerRank(int n, int i, int j) {
        //layer number: from outer layer to inner layer starting at 0, 1, 2...
        int layerNum = min(i, n - 1 - i, j, n - 1 - j);
        int len = n - layerNum * 2 - 1;
        //check which side [i,j] lies on and calc the rank accordingly
        //Do the check in the exact order: upper, right, lower, left; following the spiral direction
        if (layerNum == i /* && j < layerNum + len this check is unnecessary if order is followed*/) {
            //upper side
            return j - layerNum;
        } else if (layerNum == n - 1 - j/* && i < layerNum + len ditto*/) {
            //right side
            return len + i - layerNum;
        } else if (layerNum == n - 1 - i/* && j != layerNum ditto*/) {
            //lower side
            return 2 * len + (n - 1 - j) - layerNum;
        } else {
            //left side
            return 3 * len + (n - 1 - i) - layerNum;
        }
    }

    int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    /**
     * Bug:
     * 1. empty matrix, shouldn't try to do 'j2 = matrix[0].length'
     * 2. spiral direction: clockwise or counter-clockwise.
     * The mapping between the visual 'matrix' and the real 2D array must be clearly drawn on the white board
     * The mapping of indexes i, j, etc and their moving directions must also be drawn
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix.length == 0) return result;

        int i1 = 0, i2 = matrix.length;
        int j1 = 0, j2 = matrix[0].length;
        int s = 0;
        while (i1 < i2 && j1 < j2) {
            switch (s % 4) {
                case 0:
                    for (int j = j1; j < j2; ++j) {
                        result.add(matrix[i1][j]);
                    }
                    ++i1;
                    break;
                case 1:
                    --j2;
                    for (int i = i1; i < i2; ++i) {
                        result.add(matrix[i][j2]);
                    }
                    break;
                case 2:
                    --i2;
                    for (int j = j2; j > j1; ) {
                        result.add(matrix[i2][--j]);
                    }
                    break;
                case 3:
                    for (int i = i2; i > i1; ) {
                        result.add(matrix[--i][j1]);
                    }
                    ++j1;
                    break;
            }
            ++s;
        }
        return result;
    }
}
