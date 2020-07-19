package hackerrank.dp;

public class EditDistance {
    static int[][] dp;
    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        //String s1 = "horse", s2 = "ros";
        String s1 = "intention", s2 = "execution";
        dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++) {
            for (int j=0;j<=s2.length();j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(editDistance.solve(s1, s2, s1.length()-1, s2.length()-1));
        System.out.println(editDistance.solveTopDown(s1, s2));
    }

    public int solveTopDown(String s1, String s2) {
        for (int i=0;i<=s1.length();i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) dp[i][j] = 0;
                else if (i == 0 && j != 0) dp[i][j] = j+1;
                else if (j == 0 && i != 0) dp[i][j] = i+1;
            }
        }


        for (int i=1;i<=s1.length();i++) {
            for(int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(1+dp[i-1][j], 1+dp[i][j-1]),
                            1+dp[i-1][j-1]
                    );
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public int solve(String s1, String s2, int i, int j) {
        if (i < 0 && j < 0) return 0;
        if (i < 0) return j+1;
        if (j < 0) return i+1;

        if (dp[i][j] != -1) return dp[i][j];
        if (s1.charAt(i) == s2.charAt(j)) return dp[i][j] = solve(s1, s2, i-1, j-1);
        return dp[i][j] =  Math.min(
                Math.min(
                        1+solve(s1, s2, i-1, j), //delete
                        1+solve(s1, s2, i, j-1)), // insert
                1+solve(s1, s2, i-1, j-1) //replace
        );
    }

}
