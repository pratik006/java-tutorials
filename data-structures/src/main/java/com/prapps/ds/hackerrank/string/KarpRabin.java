package hackerrank.string;

import java.util.ArrayList;
import java.util.List;

public class KarpRabin {

    private static int prime = 7;

    public static void main(String[] args) {
        String text = "adadasdasdchtsdfkgfjhhgasdasdasd";
        String pattern = "chtsdfkgfjhhg";
        long patternHash = createHash(pattern, 0, pattern.length()-1);
        long rollingHash = createHash(text, 0, pattern.length()-1);
        List<Integer> res = new ArrayList<>();
        System.out.println(rollingHash+" "+patternHash);
        if (patternHash == rollingHash) {
            if (equals(pattern, 0, pattern.length()-1, text, 0, pattern.length()-1)) {
                res.add(0);
            }
        }
        for (int i=1;i<=text.length()-pattern.length(); i++) {
            rollingHash = rollingHash(text, i-1, i+pattern.length()-1, rollingHash);

            if (rollingHash == patternHash) {
                System.out.println(rollingHash+" "+patternHash);
                if (equals(pattern, 0, pattern.length()-1, text, i, i+pattern.length()-1)) {
                    res.add(i);
                }
            }
        }
        System.out.println(res);
    }

    private static long createHash(String s, int startIndex, int endIndex) {
        long hash = 0;
        for (int i=startIndex; i<=endIndex; i++) {
            hash += ((int)s.charAt(i)) * (Math.pow(prime, i - startIndex));
        }

        return hash;
    }

    private static long rollingHash(String s, int oldIndex, int newIndex, long oldHhash) {
        return (long) (((oldHhash - ((long)s.charAt(oldIndex)))/prime) +  ((long)s.charAt(newIndex))*Math.pow(prime, newIndex-oldIndex-1));
    }

    private static boolean equals(String s1, int start1, int end1, String s2, int start2, int end2) {
        if ((end2-start2) - (end1-start1) != 0)
            return false;

        while (start1<=end1 && start2<=end2) {
            if (s1.charAt(start1) != s2.charAt(start2))
                return false;

            start1++;start2++;
        }

        return true;
    }


}
