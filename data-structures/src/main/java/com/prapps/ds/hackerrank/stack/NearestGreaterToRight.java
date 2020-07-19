package hackerrank.stack;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class NearestGreaterToRight {

    static class Entry {
        int index;
        int value;
        public Entry(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    static class Pair<K,V> {
        K key;
        V value;
        public Pair(K index, V value) {
            this.key = index;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        /*int[] input = new int[]{1,3,0,0,1,2,4};
        System.out.println(Arrays.toString(ngr(input)));
        System.out.println(Arrays.toString(ngrIndex(input)));
        System.out.println(Arrays.toString(ngl(input)));
        System.out.println(histogramArea(new int[]{6,2,5,4,5,1,6}));*/

        System.out.println(Arrays.toString(riddle(new long[]{2,6,1,12})));
    }

    static public int[] ngr(int[] input) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[input.length];
        int ctr = input.length;
        for (int i=input.length-1; i>=0; i--) {
            if (stack.isEmpty()) {
                res[--ctr] = -1;
            } else if(input[i] < stack.peek()) {
                res[--ctr] = stack.peek();
            } else if(input[i] >= stack.peek()) {
                while (!stack.isEmpty() && input[i] >= stack.peek())
                    stack.pop();

                res[--ctr] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(input[i]);
        }

        return res;
    }

    static public int[] ngrIndex(int[] input) {
        Stack<Entry> stack = new Stack<>();
        int[] res = new int[input.length];
        int ctr = input.length;
        for (int i=input.length-1; i>=0; i--) {
            if (stack.isEmpty()) {
                res[--ctr] = -1;
            } else if(input[i] < stack.peek().value) {
                res[--ctr] = stack.peek().index;
            } else if(input[i] >= stack.peek().value) {
                while (!stack.isEmpty() && input[i] >= stack.peek().value)
                    stack.pop();

                res[--ctr] = stack.isEmpty() ? -1 : stack.peek().index;
            }

            stack.push(new Entry(i, input[i]));
        }

        return res;
    }

    static public int[] ngl(int[] input) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[input.length];
        int ctr = 0;
        for (int i=0; i<input.length; i++) {
            if (stack.isEmpty()) {
                res[ctr++] = -1;
            } else if(input[i] < stack.peek()) {
                res[ctr++] = stack.peek();
            } else if(input[i] >= stack.peek()) {
                while (!stack.isEmpty() && input[i] >= stack.peek())
                    stack.pop();

                res[ctr++] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(input[i]);
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

    static int histogramArea(int[] input) {
        int[] nsr = new int[input.length];
        int[] nsl = new int[input.length];
        int maxArea = 0, ctr = 0;
        Stack<Entry> stackl = new Stack<>();
        Stack<Entry> stackr = new Stack<>();

        for (int i=0; i<input.length; i++) {
            if (stackl.isEmpty()) {
                nsl[ctr++] = -1;
            } else if (stackl.peek().value < input[i]) {
                nsl[ctr++] = stackl.peek().index;
            } else if (stackl.peek().value >= input[i]) {
                while (!stackl.isEmpty() && stackl.peek().value >= input[i])
                    stackl.pop();

                if (stackl.isEmpty())
                    nsl[ctr++] = -1;
                else
                    nsl[ctr++] = stackl.peek().index;
            }
            stackl.push(new Entry(i, input[i]));
        }

        ctr = input.length;
        for (int i=input.length-1; i>=0; i--) {
            if (stackr.isEmpty()) {
                nsr[--ctr] = input.length;
            } else if (stackr.peek().value < input[i]) {
                nsr[--ctr] = stackr.peek().index;
            } else if (stackr.peek().value >= input[i]) {
                while (!stackr.isEmpty() && stackr.peek().value >= input[i])
                    stackr.pop();

                if (stackr.isEmpty())
                    nsr[--ctr] = input.length;
                else
                    nsr[--ctr] = stackr.peek().index;
            }
            stackr.push(new Entry(i, input[i]));
        }

        for (int i=0;i<nsl.length;i++) {
            int area = (nsr[i] - nsl[i] - 1) * input[i];
            if (area > maxArea)
                maxArea = area;
        }

        return maxArea;
    }


    /*Given an integer array of size , find the maximum of the minimum(s) of every window size in the array. The window size varies from  to .

    For example, given , consider window sizes of  through . Windows of size  are . The maximum value of the minimum values of these windows is . Windows of size  are  and their minima are . The maximum of these values is . Continue this process through window size  to finally consider the entire array. All of the answers are .

            Function Description

    Complete the riddle function in the editor below. It must return an array of integers representing the maximum minimum value for each window size from  to .*/
    static long[] riddle(long[] arr) {
        long res[] = new long[arr.length];
        long nsr[] = nsr(arr);
        long nsl[] = nsl(arr);
        Map<Long, Long> map = new TreeMap<>();
        for (int i=0;i<nsr.length;i++) {
            long w = nsr[i] - nsl[i] -1;
            if (map.containsKey(w)) {
                if (arr[i] > map.get(w))
                    map.put(w, arr[i]);
            } else {
                map.put(w, arr[i]);
            }
        }

        int ctr = 0; long i = 1;
        while (ctr < arr.length) {
            if (map.containsKey(i)) {
                res[ctr++] = map.get(i);
                if (i==ctr)
                    i++;
            } else
                i++;
        }
        return res;
    }
}
