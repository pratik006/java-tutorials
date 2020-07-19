package hackerrank.string;

public class LCS {
    static int d[][];
    static int d2[][];
    public static void main(String[] args) {
        String s1 = "ABCDEFG";
        String s2 = "FBDAMNG";

        d = new int[s1.length()+1][s2.length()+1];
        d2 = new int[s1.length()+1][s2.length()+1];
        for (int i=0;i<=s1.length();i++) {
            for (int j=0;j<=s2.length();j++) {
                if (i == 0 || j ==0) {
                    d[i][j] = 0;
                    d2[i][j] = 0;
                } else
                    d[i][j] = -1;
            }
        }

        //System.out.println(lcsMemo(s1, s2, s1.length(), s2.length()));
        System.out.println(lcsTopDown(s1, s2));
        System.out.println(printLcs(s1, s2));
        System.out.println(lcsubstringTopDown(s1, s2));
    }

    static int lcs(String s1, String s2, int l1, int l2) {
        if (l1 == 0 || l2 == 0)
            return 0;

        if (s1.charAt(l1-1) == s2.charAt(l2-1))
            return 1 + lcs(s1, s2, l1-1, l2-1);
        else return Math.max(lcs(s1, s2, l1-1, l2), lcs(s1, s2, l1, l2-1));
    }

    static int lcsMemo(String s1, String s2, int l1, int l2) {
        if (l1 == 0 || l2 == 0)
            return 0;

        if (d[l1][l2] != -1)
            return d[l1][l2];

        if (s1.charAt(l1-1) == s2.charAt(l2-1))
            return d[l1][l2] = 1 + lcsMemo(s1, s2, l1 - 1, l2 - 1);
        else
            return d[l1][l2] = Math.max(lcsMemo(s1, s2, l1-1, l2), lcsMemo(s1, s2, l1, l2-1));
    }

    static int lcsTopDown(String s1, String s2) {
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

    static String printLcs(String s1, String s2) {
        int i = s1.length();
        int j = s2.length();
        String res = "";
        while (i>0 && j>0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                res = s1.charAt(i-1) + res;
                i--;j--;
            } else if (d2[i-1][j] > d2[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        return res;
    }

    static int lcsubstringTopDown(String s1, String s2) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    d2[i][j] = 1 + d2[i-1][j-1];
                } else {
                    d2[i][j] = 0;//Math.max(d2[i-1][j], d2[i][j-1]);
                }
            }
        }

        int max = 0;
        for (int i=1;i<=s1.length();i++)
            for (int j=1;j<=s2.length();j++)
                if(max < d2[i][j])
                    max = d2[i][j];
        return max;
    }
}
