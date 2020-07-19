package hackerrank.dp;

public class MinSubsetSum {
    static boolean[][] d;
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 7};
        int S = 0;
        for (int i=0;i<A.length;i++) {
            S += A[i];
        }

        d = new boolean[A.length+1][S+1];
        for (int i=0;i<=A.length;i++) {
            for (int j = 0; j <= S; j++) {
                if (i == 0)
                    d[i][j] = false;
                if (j == 0)
                    d[i][j] = true;
            }
        }
        subsetSumBottomUp(A, S);
        int min = Integer.MAX_VALUE;
        for (int i=0;i<S/2;i++) {
            if (d[A.length][i]) {
                int x = S - 2*i;
                if (x<min)
                    min = x;
            }
        }
        System.out.println(min);
    }

    static boolean subsetSumBottomUp(int[] A, int S) {
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
