package org.shoupu.array;

import java.util.Stack;

public class Histogram {

    static int largestRectangleArea(int[] h) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxRec = 0;
        for (int i = 0; i <= h.length; /*increment occurs in loop*/) {
            if (stack.empty() || i < h.length && h[i] > h[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = stack.pop();
                //Calculate the maximum of the rectangles ending at the (i-1)th element
                maxRec = Math.max(maxRec, h[height] * (stack.empty() ? i : i - stack.peek() - 1));
            }
        }
        return maxRec;
    }

    /**
     * For example,
     * Given height = [2,1,5,6,2,3],
     * return 10.
     * <p>
     * Algorithm
     * *****
     * *******
     * *******
     * ********
     * *********
     * ^
     * |
     * Have a stack storing the array indexes
     * Have an array storing the length of the rectangle to the left of the current histogram at its height
     * for each i = 0...< n
     * while(height[i] <= stack top)
     * leftI = stack pop
     * end while
     * leftI = stack top;
     * leftLen[i] = i - leftI;
     * end for
     * <p>
     * repeat for rightI and rightLen
     * <p>
     * find the max area based on arrays leftLen and rightLen
     * <p>
     * Note: there is a trick to beautify the coding: make two fictitious book ends so that one never run off the ends.
     */
    static
    public int largestRectangleArea_Old(int[] height) {
        height = augmentArray(height);
        Stack<Integer> indexes = new Stack<Integer>();
        indexes.push(0);

        //max length of the rectangle to the left (right) at height[i], depending on the direction of this loop
        int[] lens = new int[height.length];
        for (int i = 1, backward = 0; /* termination condition in the loop*/ ; ) {
            if (i == height.length - 1) {
                //switch iteration direction to backward
                backward = 1;
                indexes.clear();
                indexes.push(i);
                --i;
            }

            //termination check
            if (i == 0 && backward != 0) {
                break;
            }

            Integer latestPoppedIndex = null;
            while (height[i] <= height[indexes.peek()]) {
                latestPoppedIndex = indexes.pop();
            }
            //The current histogram is included in both ways
            lens[i] += Math.abs(i - indexes.peek());

            if (latestPoppedIndex != null && height[latestPoppedIndex] == height[i]) {
                //Condition 1: tall element, then latestPoppedIndex == null
                //Condition 2: two equal elements sandwiching tall elements in between
                indexes.push(latestPoppedIndex);
            }
            indexes.push(i);
            //iterate
            if (backward == 0) {
                ++i;
            } else {
                --i;
            }
        }

        for (int i = 1, maxArea = 0; ; ++i) {
            if (i == lens.length - 1) {
                return maxArea;
            }
            int area = height[i] * (lens[i] - 1);
            maxArea = Math.max(maxArea, area);
        }
    }

    /**
     * add two zero-height bars one to each side of the array
     */
    private static int[] augmentArray(int[] height) {
        int[] copy = new int[height.length + 2];
        System.arraycopy(height, 0, copy, 1, height.length);
        copy[0] = -1;
        copy[copy.length - 1] = -1;
        return copy;
    }

    public static void main(String[] args) {
        int[][] height = {
                {2, 1, 4, 5, 1, 3, 3},
                {},
                {4, 2, 0, 3, 2, 5},
                {2, 1, 2},
                {1, 2, 1, 2},
                {1, 2, 3, 4, 5},
                {0},
                {1, 2, 4, 2},
        };

        for (int i = 0; i < height.length; ++i) {
            int maxArea = largestRectangleArea(height[i]);
            System.out.format("%s = %d%n", toString(height[i]), maxArea);
        }
    }

    private static String toString(int[] is) {
        StringBuilder sb = new StringBuilder("[");
        for (int i : is) {
            sb.append(i).append(",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }
}
