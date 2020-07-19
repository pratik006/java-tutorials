package hackerrank.retest;

public class KnapsackReTest {
    static int[][] dp;

    public static void main(String[] args) {
        int[] val = new int[]{4, 2, 1, 2, 10};
        int[] weights = new int[]{12, 2, 1, 1, 4};
        int W = 15;
        dp = new int[val.length+1][W+1];
        for (int i=0;i<=val.length;i++) {
            for (int j=0;j<=W;j++) {
                if (i == 0 || j ==0)
                    dp[i][j] = 0;
                else dp[i][j] = -1;
            }
        }
        System.out.println(knapsack(val, weights, W, val.length-1));
    }


    private static int knapsack(int[] val, int wts[], int W, int n) {
        if (n < 0 || W == 0)
            return 0;

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wts[n] > W) {
            return dp[n][W] = knapsack(val, wts, W, n-1);
        } else {
            return dp[n][W] = Math.max(val[n]+knapsack(val, wts, W-wts[n], n-1), knapsack(val, wts, W, n-1));
        }
    }
}
