package com.prapps.ds.hackerrank.string;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome("aacdefcaa"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        int start = 0, end = 0;
        for (int i=0;i<s.length();i++) {
            int len1 = expandFromMiddle(s, i, i);
            int len2 = expandFromMiddle(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start+1) {
                start = i-((len-1)/2);
                end = i+(len/2);
                //System.out.println(i+" len "+len+" "+start+" "+end);
            }
        }

        return s.substring(start, end+1);
    }

    int expandFromMiddle(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
