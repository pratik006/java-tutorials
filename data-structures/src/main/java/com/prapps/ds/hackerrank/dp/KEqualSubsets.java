package hackerrank.dp;

import java.util.Arrays;

public class KEqualSubsets {
    static int[][] d1;
    static int[][] d;
    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 4, 5, 6};
        int K = 3;
        int S = 0;
        for (int i=0;i<A.length;i++) {
            S += A[i];
        }

        if (S%3 != 0) {
            System.out.println(false);
        } else {
            d1 = new int[A.length+1][S+1];
            d = new int[A.length+1][S+1];
            for (int i=0;i<=A.length;i++) {
                d1[i] = new int[S+1];
                for (int j = 0; j <= S; j++) {
                    d1[i][j] = -1;
                    if (i == 0)
                        d[i][j] = 0;
                    if (j == 0)
                        d[i][j] = 1;
                }
            }

            System.out.println(K == subsetSumMemoization(A, S/3, A.length-1));
        }
    }

    static int subsetSum(int[] A, int S, int n) {
        if (S == 0)
            return 1;
        if (n == -1)
            return 0;

        if (A[n] > S)
            return subsetSum(A, S, n-1);

        return subsetSum(A, S-A[n], n-1) + subsetSum(A, S, n-1);

    }

    static int subsetSumMemoization(int[] A, int S, int n) {
        if (S == 0)
            return 1;
        if (n == -1)
            return 0;

        if (d1[n][S] != -1) {
            return d1[n][S];
        }
        int res;
        if (A[n] > S)
            res = subsetSumMemoization(A, S, n-1);
        else
            res = subsetSumMemoization(A, S-A[n], n-1) + subsetSumMemoization(A, S, n-1);
        d1[n][S] = res;
        return res;
    }

    static int subsetSumBottomUup(int[] A, int S) {
        for (int i=1;i<=A.length;i++) {
            for (int j=1;j<=S;j++) {
                int item = A[i-1];
                if (item > j)
                    d[i][j] = d[i-1][j];
                else
                    d[i][j] = d[i-1][j-item] + d[i-1][j];
            }
        }

        return d[A.length][S];
    }
}
