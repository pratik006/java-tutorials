package hackerrank.stack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MinMaxRiddle {

    static class Pair<K,V> {
        K key;
        V value;
        public Pair(K index, V value) {
            this.key = index;
            this.value = value;
        }
    }
    // Complete the riddle function below.

    static long[] riddle(long[] arr) {
        // complete this function
        long nsr[] = nsr(arr);
        long nsl[] = nsl(arr);
        long w[] = new long[arr.length];
        Map<Long, Long> widthToIndex = new HashMap<>();
        for (int i=0;i<arr.length;i++) {
            w[i] = nsr[i] - nsl[i] - 1;
            if (widthToIndex.containsKey(w[i])) {
                if (arr[i] > widthToIndex.get(w[i])) {
                    widthToIndex.put(w[i], arr[i]);
                }
            } else {
                widthToIndex.put(w[i], arr[i]);
            }
        }
        System.out.println(widthToIndex);
        long[] res = new long[arr.length];
        long prev = -1;
        for (int i=arr.length-1;i>=0;i--) {
            Long mval = widthToIndex.get((long)i+1);
            if (mval != null) {
                prev = mval;
                res[i] = mval;
            } else {
                res[i] = prev;
            }
        }

        return res;
    }

    static long[] nsl(long[] arr) {
        long[] res = new long[arr.length];
        int ctr = 0;
        Stack<Pair<Integer, Long>> stack = new Stack<>();
        for (int i=0;i<arr.length;i++) {
            if (stack.isEmpty()) {
                res[ctr++] = -1L;
            } else if(arr[i] > stack.peek().value) {
                res[ctr++] = stack.peek().key;
            } else {
                while (!stack.isEmpty() && stack.peek().value >= arr[i])
                    stack.pop();
                if (stack.isEmpty())
                    res[ctr++] = -1;
                else
                    res[ctr++] = stack.peek().key;
            }

            stack.push(new Pair<Integer, Long>(i, arr[i]));
        }
        return res;
    }

    static long[] nsr(long[] arr) {
        long[] res = new long[arr.length];
        int ctr = arr.length-1;
        Stack<Pair<Integer, Long>> stack = new Stack<>();
        for (int i=arr.length-1;i>=0;i--) {
            if (stack.isEmpty()) {
                res[ctr--] = arr.length;
            } else if(arr[i] > stack.peek().value) {
                res[ctr--] = stack.peek().key;
            } else {
                while (!stack.isEmpty() && stack.peek().value >= arr[i])
                    stack.pop();
                if (stack.isEmpty())
                    res[ctr--] = arr.length;
                else
                    res[ctr--] = stack.peek().key;
            }

            stack.push(new Pair<Integer, Long>(i, arr[i]));
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());

        long[] arr = new long[n];

        String[] arrItems = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);
        FileOutputStream fos = new FileOutputStream("/home/sengp/Downloads/test_output.txt");
        for (int i=0;i<res.length;i++) {
            fos.write((res[i]+" ").getBytes());
        }
        fos.close();

        reader.close();
    }
}
