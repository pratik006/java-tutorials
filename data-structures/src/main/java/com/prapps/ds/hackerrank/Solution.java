package hackerrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static boolean[][] d;
    static int d1[][];
    public static void main(String[] args) throws IOException {

        //int[] input = new int[]{-1, 3, 5, -7, 8, 10, -1};
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(abbreviation(line, reader.readLine()));
        }

        reader.close();
    }

    static String abbreviation(String a, String b) {
        if (a == null || b == null)
            return "NO";
        int d2[][] = new int[a.length()+1][b.length()+1];
        String A = a.toUpperCase();
        int lcs = lcsTopDown1(a, b, d2);
        int lcs2 = lcsTopDown(A, b, new int[a.length()+1][b.length()+1]);
        int temp = lcs;
        //System.out.println("lcs: "+lcs);
        if (lcs == b.length()) {
            int i=a.length();int j=b.length();
            while (i>0 && j>0) {
                char ch1 = A.charAt(i-1);
                char ch2 = b.charAt(j-1);
                if (d2[i-1][j] == d2[i][j-1]) {
                    i--;j--;
                } else if (d2[i-1][j] > d2[i][j-1]) {
                    if ((int)a.charAt(i-1) < 96 && a.charAt(i-1) != b.charAt(j-1))
                        return "NO";
                    i--;
                } else if (d2[i][j-1] > d2[i-1][j]) {
                    j--;
                }
            }
            return "YES";
        } else {
            return "NO";
        }
    }

    static int lcsTopDown(String s1, String s2, int d2[][]) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    d2[i][j] = 1 + d2[i-1][j-1];
                } else {
                    d2[i][j] = Math.max(d2[i-1][j], d2[i][j-1]);
                }
            }
        }
        return d2[s1.length()][s2.length()];
    }

    static int lcsTopDown1(String s1, String s2, int d2[][]) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    d2[i][j] = 1 + d2[i-1][j-1];
                } else if(Character.toUpperCase(ch1) == ch2) {
                    d2[i][j] = Math.max(1 + d2[i-1][j-1], Math.max(d2[i-1][j], d2[i][j-1]));
                } else if ((int)ch1 < 96) {
                    d2[i][j] = 0;
                } else {
                    d2[i][j] = Math.max(d2[i-1][j], d2[i][j-1]);
                }
            }
        }
        return d2[s1.length()][s2.length()];
    }

}
