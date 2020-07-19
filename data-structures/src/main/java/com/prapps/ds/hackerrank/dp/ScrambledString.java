package hackerrank.dp;

public class ScrambledString {
    public static void main(String[] args) {
        ScrambledString scrambledString = new ScrambledString();
        System.out.println(scrambledString.isScramble("xstjzkfpkggnhjzkpfjoguxvkbuopi" ,"xbouipkvxugojfpkzjhnggkpfkzjts"));
    }
    public boolean isScramble(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n != m) return false;
        if (n == 0) return true;
        if (s1.equals(s2)) return true;

        int[] count = new int[26];
        for (int i=0;i<n;i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i=0;i<26;i++) if (count[i]!=0) return false;

        for (int i=1;i<n;i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(m-i)) && isScramble(s1.substring(i), s2.substring(0, m-i))) {
                return true;
            }
        }

        return false;

    }
}
