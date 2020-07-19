package hackerrank.heap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KSmallest {
    public static void main(String[] args) {
        System.out.println(KSmallest(new int[]{7,10,4,3,20,15}, 3));//3,4,7,10,15,20
        System.out.println(KLargest(new int[]{7,10,4,3,20,15}, 3));//3,4,7,10,15,20
        System.out.println(Arrays.toString(KLargestElements(new int[]{7,10,4,3,20,15}, 3)));//3,4,7,10,15,20
        System.out.println(Arrays.toString(KClosestElements(new int[]{4,5,6,7,8,9}, 7, 3)));

        System.out.println("riddle: "+Arrays.toString(riddle(new long[]{2,6,1,12})));
    }

    static int KSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o2-o1);
        for (int n : arr) {
            pq.offer(n);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.poll();
    }

    static int KLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o1-o2);
        for (int n : arr) {
            pq.offer(n);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.poll();
    }

    static int[] KLargestElements(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o1-o2);
        int[] res = new int[k];
        for (int n : arr) {
            pq.offer(n);
            if (pq.size() > k)
                pq.remove();
        }

        int ctr = k-1;
        while(ctr >= 0)
            res[ctr--] = pq.poll();

        return res;
    }

    static class Pair {
        int key;
        int value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    static int[] KClosestElements(int[] arr, int x, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, (o1, o2) -> o2.key-o1.key);
        int[] res = new int[k];
        for (int i=0;i<arr.length;i++) {
            pq.offer(new Pair(Math.abs(arr[i]-x), arr[i]));
            if (pq.size() > k)
                pq.remove();
        }

        int ctr = k-1;
        while(ctr >= 0)
            res[ctr--] = pq.poll().value;

        return res;
    }

    static long[] riddle(long[] arr) {
        // complete this function
        Queue<Long> queue = new LinkedList<Long>();
        long[] res = new long[arr.length];
        long max = 0;
        for (int i=0;i<arr.length;i++) {
            queue.offer(arr[i]);
            if (max < arr[i])
                max = arr[i];
        }
        res[0] = max;

        for (int w=2;w<=arr.length;w++) {
            int size = queue.size();
            max = -1;
            for (int j=0;j<size-1;j++) {
                long n1 = queue.poll();
                long n2 = queue.peek();
                long minpair = Math.min(n1,n2);
                queue.offer(minpair);
                if (minpair > max) max = minpair;
            }
            queue.remove();
            res[w-1] = max;
            System.out.println(queue+" "+max);
        }

        return res;
    }
}
