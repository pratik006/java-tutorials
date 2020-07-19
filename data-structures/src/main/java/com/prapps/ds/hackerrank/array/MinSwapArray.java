package hackerrank.array;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinSwapArray {
        static long inv = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String line = null;
        int[][] arr = new int[3][];

        int ctr = 0;
        while ((line = reader.readLine()) != null) {
            String[] nos = line.split(" ");
            arr[ctr] = new int[nos.length];
            for (int i=0;i<nos.length;i++) {
                arr[ctr][i] = Integer.parseInt(nos[i]);
            }
            ctr++;
        }

        reader.close();


        //int[] arr = { 7,5,3,1 };
        //System.out.println(countInversions(arr));
        System.out.println(triplets(arr[0], arr[1], arr[2]));
    }

    static long countInversions(int[] arr) {
        partition(arr, 0, arr.length-1);
        return inv;
    }

    static void partition(int[] arr, int start, int end) {
        if (end == start)
            return;

        int mid = (end+start)/2;
        partition(arr, start, mid);
        partition(arr, mid+1, end);
        merge(arr, start, end);
    }

    static void merge(int[] arr, int start, int end) {
        int mid = (start + end)/2;
        int n1 = mid-start+1;
        int n2 = end-mid;

        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i=0;i<n1;i++) {
            L[i] = arr[start+i];
        }
        for (int i=0;i<n2;i++) {
            R[i] = arr[mid+1+i];
        }

        int i=0,j=0,k=start;
        while (i<n1 && j<n2) {
            if(L[i] <= R[j])
                arr[k++] = L[i++];
            else {
                arr[k++] = R[j++];
                inv += (n1-i);
            }
        }

        while(i<n1)
            arr[k++] = L[i++];
        while(j<n2)
            arr[k++] = R[j++];
    }

    static long triplets1(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);

        int i=0,j=0;
        /*while () {

        }*/
        return 0;
    }

    static long triplets(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        List<Integer> al = Arrays.stream(a).boxed().collect(java.util.stream.Collectors.toSet())
                .stream().collect(java.util.stream.Collectors.toList());
        List<Integer> bl = Arrays.stream(b).boxed().collect(java.util.stream.Collectors.toSet())
                .stream().collect(java.util.stream.Collectors.toList());
        List<Integer> cl = Arrays.stream(c).boxed().collect(java.util.stream.Collectors.toSet())
                .stream().collect(java.util.stream.Collectors.toList());


        int[] ba = new int[bl.size()];
        int[] bc = new int[bl.size()];
        for (int i = 0; i < b.length; i++) {
            ba[i] = findPos(al, bl.get(i), 0, a.length - 1);
        }

        for (int i = 0; i < b.length; i++) {
            bc[i] = findPos(cl, bl.get(i), 0, c.length - 1);
        }
        long res = 0;
        for (int i = 0; i < b.length; i++) {
            res += ba[i] * bc[i];
        }
        return res;
    }

    static int findPos(List<Integer> arr, int item, int l, int h) {
        int pos = -1;
        while (l<=h) {
            int mid = l + (h-l)/2;
            if (item == arr.get(mid))
                return mid+1;
            else if(item > arr.get(mid)) {
                l = mid+1;
                pos = l;
            }
            else if (arr.get(mid) > item) {
                h = mid-1;
                pos = mid;
            }
        }

        return pos;
    }
}
