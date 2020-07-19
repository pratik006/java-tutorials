package hackerrank.dp;

import java.util.Arrays;

public class LongestPalindromicSubstring {
    int[][] dp;
    public static void main(String[] args) {
        String s = "aacdefcaa";
        System.out.println(new LongestPalindromicSubstring().lps(s, new StringBuilder(s).reverse().toString()));
    }

    public String lps(String s1, String s2) {
        dp = new int[s1.length()+1][s2.length()+1];

        int x=0,y=0, max=0;
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (s1.substring(0, i-1).equals(s2.substring(0, j-1))) {
                    dp[i][j] = 1+dp[i-1][j-1];
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        x=i;y=j;
                    }
                } else {
                    dp[i][j] = 0;
                    //Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        for (int i=0;i<dp.length;i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        int i=x, j=y;
        StringBuilder sb = new StringBuilder();
        while (i>0 && j>0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.append(s1.charAt(i-1));
                i--;j--;
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
