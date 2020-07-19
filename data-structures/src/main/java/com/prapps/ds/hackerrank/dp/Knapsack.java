package hackerrank.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Knapsack {

    static int[][] d;
    static int[][] d2;

    public static void main(String[] args) {
        System.out.println(new StringBuilder("test").reverse().hashCode() + "   "+new StringBuilder("test").reverse().toString().hashCode());

        int[] val = new int[]{4, 2, 1, 2, 10};
        int[] weights = new int[]{12, 2, 1, 1, 4};
        int W = 15;
        d = new int[val.length+1][50+1];
        d2 = new int[val.length+1][50+1];
        for (int i=0;i<=val.length;i++) {
            d[i] = new int[50+1];
            d2[i] = new int[50+1];
            for (int j = 0; j <= W; j++) {
                d[i][j] = -1;
                if (i== 0 || j == 0)
                    d2[i][j] = 0;
            }
        }
        int r = findMax(val, weights, W, val.length-1);
        System.out.println(r);
        int r2 = findMaxDP(val, weights, W, val.length-1);
        System.out.println(r2);
        int r3 = findMaxTopDown(val, weights, W);
        System.out.println(r3);
    }

    public static int findMax(int[] val, int[] w, int W, int n) {

        if (n == -1 || W == 0)
            return 0;
        if (w[n] > W)
            return findMax(val, w, W, n-1);

        return Math.max(val[n] + findMax(val, w, W-w[n], n-1), findMax(val, w, W, n-1));
    }

    public static int findMaxDP(int[] val, int[] w, int W, int n) {
        if (n == 0 || W <= 0)
            return 0;
        if (d[n][W] != -1)
            return d[n][W];

        if (w[n] > W)
            d[n][W] = findMaxDP(val, w, W, n-1);
        else
            d[n][W] = Math.max(val[n] + findMaxDP(val, w, W-w[n], n-1),
                    findMaxDP(val, w, W, n-1));

        return d[n][W];
    }

    public static int findMaxTopDown(int[] val, int[] w, int W) {
        for (int i=1;i<=val.length;i++) {
            for (int j = 1; j <= W; j++) {
                int weight = w[i-1];
                if (weight > j) {
                    d2[i][j] = d2[i-1][j];
                } else {
                    d2[i][j] = Math.max((val[i-1] + d2[i-1][j-weight]),
                            d2[i-1][j]);
                }
            }
        }

        return d2[val.length][W];
    }
}
