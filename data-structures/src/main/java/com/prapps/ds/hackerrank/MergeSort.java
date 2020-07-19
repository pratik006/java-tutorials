package hackerrank;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr, int l, int r) {
        if (l<r) {
            int mid = (r+l)/2;
            sort(arr, l, mid);
            sort(arr, mid+1, r);
            merge(arr, l, r);
        }
    }

    static void merge(int[] arr, int l, int r) {
        int mid = (r+l)/2;
        int nleft = mid-l+1;
        int nright = r - mid;

        int[] L = new int[nleft];
        int[] R = new int[nright];
        for (int i=0;i<nleft;i++)
            L[i] = arr[l+i];
        for (int i=0;i<nright;i++)
            R[i] = arr[mid+1+i];

        int i=0, j=0, k=l;
        while (i<nleft && j<nright) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i<nleft)
            arr[k++] = L[i++];
        while (j<nright)
            arr[k++] = R[j++];
    }
}
