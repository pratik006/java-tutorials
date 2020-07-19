package hackerrank.dp;

public class SubsetSum {
    static int[][] d1;
    static boolean[][] d;
    public static void main(String[] args) {
        int[] A = { 3, 2, 7, 1};
        int S = 6;
        d1 = new int[A.length+1][S+1];
        d = new boolean[A.length+1][S+1];
        for (int i=0;i<=A.length;i++) {
            d1[i] = new int[S+1];
            for (int j = 0; j <= S; j++) {
                d1[i][j] = -1;
                if (i == 0)
                    d[i][j] = false;
                if (j == 0)
                    d[i][j] = true;
            }
        }

        //System.out.println(subsetSumMemoization(A, S, A.length-1));
        System.out.println(subsetSumBottomUup(A, S));
    }

    static boolean subsetSum(int[] A, int S, int n) {
        if (S == 0)
            return true;
        if (n == -1)
            return false;

        if (A[n] > S)
            return subsetSum(A, S, n-1);

        return subsetSum(A, S-A[n], n-1) || subsetSum(A, S, n-1);

    }

    static boolean subsetSumMemoization(int[] A, int S, int n) {
        if (S == 0)
            return true;
        if (n == -1)
            return false;

        if (d1[n][S] != -1) {
            return d1[n][S] == 1;
        }
        boolean res;
        if (A[n] > S)
            res = subsetSumMemoization(A, S, n-1);
        else
            res = subsetSumMemoization(A, S-A[n], n-1) || subsetSumMemoization(A, S, n-1);
        d1[n][S] = res?1:0;
        return res;
    }

    static boolean subsetSumBottomUup(int[] A, int S) {
        for (int i=1;i<=A.length;i++) {
            for (int j=1;j<=S;j++) {
                int item = A[i-1];
                if (item > j)
                    d[i][j] = d[i-1][j];
                else
                    d[i][j] = d[i-1][j-item] || d[i-1][j];
            }
        }

        return d[A.length][S];
    }
}
