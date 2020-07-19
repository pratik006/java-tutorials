package hackerrank;

import java.util.Arrays;

public class RecursionSort {
    public static void main(String[] args) {
        int[] a = new int[]{2,1,5,0};
        sort(a, a.length-1);
        System.out.println(Arrays.toString(a));
    }

    static void sort(int[] a, int n) {
        if (n == 0) return;
        int val = a[n];
        sort(a, n-1);
        insert(a, n, val);
    }

    static void insert(int[] a, int n, int val) {
        if (n == 0 || a[n - 1] < val) {
            a[n] = val;
            return;
        }
        int preval = a[n-1];
        insert(a, n-1, val);
        a[n] = preval;
    }
}
