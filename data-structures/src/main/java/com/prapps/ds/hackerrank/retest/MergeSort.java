package hackerrank.retest;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] {3,6,2,8};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr, int l, int h) {
        if (l>=h) return;
        int mid = (h+l)/2;
        sort(arr, l, mid);
        sort(arr, mid+1, h);
        merge(arr, l, h);
    }

    static void merge(int[] arr, int l, int h) {
        int mid = (h+l)/2;
        int n1 = mid-l+1;
        int n2 = h-mid;

        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i=0;i<n1;i++) left[i] = arr[l+i];
        for (int i=0;i<n2;i++) right[i] = arr[mid+i+1];

        int i=0,j=0,k=l;
        while (i<n1 && j<n2) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }

        while (i<n1) arr[k++] = left[i++];
        while (j<n2) arr[k++] = right[j++];
    }
}
