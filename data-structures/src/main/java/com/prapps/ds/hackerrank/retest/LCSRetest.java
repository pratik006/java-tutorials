package hackerrank.retest;

public class LCSRetest {
    static int[][] dp;
    public static void main(String[] args) {
        String s1 = "ABCDEFG";
        String s2 = "FBDAMNG";
        dp = new int[s1.length()+1][s2.length()+1];
        for (int i=0;i<s1.length()+1;i++) {
            for (int j=0;j<s2.length()+1;j++) {
                if (i==0 || j==0)
                    dp[i][j] = 0;
                else dp[i][j] = -1;
            }
        }
        System.out.println(lcs(s1, s2, s1.length()-1, s2.length()-1));
        System.out.println(lcs(s1, s2));
    }

    private static int lcs(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1+lcs(s1, s2, i-1, j-1);
        } else {
            return dp[i][j] = Math.max(lcs(s1, s2, i-1, j), lcs(s1, s2, i, j-1));
        }
    }

    private static int lcs(String s1, String s2) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    dp[i][j] = 1+dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
