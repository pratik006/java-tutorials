package hackerrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MaxAreaBuilding {
    public static void main(String[] args) throws IOException {
        int[] A = new int[] {8979, 4570, 6436, 5083, 7780, 3269, 5400, 7579, 2324, 2116};
        int[] h = new int[] {11, 11, 10 ,10, 10};

        //System.out.println(largestRectangle(A));
        //System.out.println(largest(A));
        //System.out.println(minTime(new long[]{2,3}, 5));
        //System.out.println(search(new long[]{63, 2, 26, 59, 16, 55, 99, 21, 98, 65}, 56));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //String[] nos = reader.readLine().split(" ");
        /*long[] input = new long[nos.length];
        for (int i=0;i<nos.length;i++) {
            input[i]  = Long.parseLong(nos[i]);
        }
        System.out.println(search(input, 927));*/

        //String s = "asasd";
        String s = reader.readLine();
        //System.out.println(sort("aaatr", 0, 5));
        //System.out.println(substrCount(s.length(), s));
        //System.out.println(substrCount(s.length(), s));
        List<Long> t = new ArrayList<>();
        for (String s1 : s.split(" ")) {
            t.add(Long.parseLong(s1));
        }
        System.out.println(countTriplets(/*new ArrayList<Long>(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L))*/t, 3L));

        reader.close();

    }

    static long largest(int[] h) {
        Stack<Integer> pStack = new Stack<>();
        Stack<Integer> hStack = new Stack<>();
        long area = 0;
        for (int i=0;i<h.length;i++) {
            if (hStack.isEmpty() || h[i] > hStack.peek()) {
                hStack.push(h[i]);pStack.push(i);
                continue;
            } else if (h[i] == hStack.peek())
                continue;

            boolean f = false;
            while (!hStack.isEmpty() && h[i] < hStack.peek()) {
                if(f) pStack.pop();
                long tempA = hStack.pop() * (i-pStack.peek());
                if (tempA > area)
                    area = tempA;
                f = true;
            }
            hStack.push(h[i]);
        }

        while (hStack.size() > 0) {
            long tempA = hStack.pop() * (h.length - pStack.pop());
            if (tempA > area)
                area = tempA;
        }

        return area;
    }

    static long largestRectangle(int[] h) {
        if (h == null || h.length == 0)
            return 0;
        long max = 0;
        int[][] d = new int[h.length][h.length];
        for (int i=0;i<h.length;i++) {
            int min = Integer.MAX_VALUE;
            for (int j=i;j<h.length;j++) {
                if (h[j] < min)
                    min = h[j];

                long area = min * (j-i+1);
                System.out.println(i+" "+j+ " "+min+" Area: "+area);
                if (area > max) {
                    max = area;
                }
            }

        }
        return max;
    }



    static long[][] d;
    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        d = new long[machines.length+1][(int)goal+1];
        for (int i=0;i<=machines.length;i++) {
            for (int j = 0; j <= goal; j++) {
                if (i == 0)
                    d[i][j] = Integer.MAX_VALUE;
                if (j == 0) {
                    d[i][j] = 0;
                }
            }
        }
        //return knap(machines, goal, machines.length-1);
        return knap1(machines, goal);
    }

    static long knap(long[] m, long goal, int n) {
        if (goal == 0)
            return 0;
        if (n == -1)
            return Integer.MAX_VALUE;

        long temp = Math.min(Math.max(m[n], knap(m, goal-1, n-1)), knap(m, goal, n-1));
        if (temp == Integer.MAX_VALUE) {
            return Math.min(m[n] + knap(m, goal-1, m.length-1), knap(m, goal, n-1));
        }
        return temp;
    }

    static long knap1(long[] m, long goal) {
        for (int i=1;i<=m.length;i++) {
            for (int j=1;j<=goal;j++) {
                long temp;
                if (j>1 && i>1) {
                    temp = Math.min(Math.min(Math.max(m[i-1], d[i-1][j-1]), d[i-1][j]), d[i][j-1]);
                    if (temp == Integer.MAX_VALUE) {
                        temp = Math.min(Math.min(m[i-1] + d[i][j-1], d[i-1][j]), d[i][j-1]);
                    }
                } else{
                    temp = Math.min(Math.max(m[i-1], d[i-1][j-1]), d[i-1][j]);
                    if (temp == Integer.MAX_VALUE) {
                        temp = Math.min(m[i-1] + d[i][j-1], d[i-1][j]);
                    }
                }
                d[i][j] = temp;
            }
        }
        return d[m.length][(int)goal];
    }

    static long search(long[] m, long goal) {
        long min = Integer.MAX_VALUE, max = 0;
        for (int i=0;i<m.length;i++) {
            if (m[i] > max)
                max = m[i];
            if (m[i] < min)
                min = m[i];
        }
        long mid = 0;
        max = goal*max/m.length;
        min = goal*min/m.length;

        while (max - min > 1) {
            mid = (max+min)/2;
            long work = 0;
            for (int i=0;i<m.length;i++) {
                work += mid/m[i];
            }
            if (goal > work) {
                min = mid;
            } else if (goal <= work) {
                max = mid;
            }
        }
        return max;
    }

    /*static long substrCount(int n, String s) {
        long count = 0;
        for (int i=0;i<s.length();i++) {
            for (int j=i;j<s.length();j++) {
                if (isSpecialStr(s, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }*/


    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> map1 = new HashMap<>();
        Map<Long, Long> map2 = new HashMap<>();
        long count = 0;
        for (Long i : arr) {
            if (map2.containsKey(i/r)) {
                count += map2.get(i/r);
            }
            if (map1.containsKey(i/r)) {
                map2.put(i, map2.getOrDefault(i, 0L)+map1.get(i/r));
            }

            map1.put(i, map1.getOrDefault(i, 0L)+1L);
        }

        return count;
    }

    static String sort(String s, int i, int j) {Map<Long, Integer> map = new HashMap<>();map.entrySet();
        int[] charCount = new int[26];
        for (char ch : s.substring(i, j).toCharArray()) charCount[ch - 'a']++;
        StringBuilder sb = new StringBuilder(s.length());
        for (int k=0;k<26;k++) {
            for (int l=1;l<=charCount[k];l++)
            sb.append((char)(k+97));
        }
        return sb.toString();
    }

    static int sherlockAndAnagrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            for (int j=0;j<=i;j++) {
                String key = sort(s, j, i+1);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key)+1);
                } else {
                    map.put(key, 1);
                }
            }
        }

        int count = 0;
        for (Integer val : map.values()) {
            if (val > 1)
                count += ((val-1)*val)/2;
        }

        return count;

    }

    static Map<String, Long> map = new HashMap<>();
    static long mcm(String s, int i, int j) {
        if (map.containsKey(i+"-"+j)) return 0;
        if (i==j) return 0;

        long max = 0;
        long count = 0;
        for (int k=i+1;k<=j;k++) {
            long temp = mcm(s, i, k-1)+mcm(s, k, j);
            if (temp > max)
                max = temp;

            count += temp;
        }

        long res = count + (isSpecialStr(s,i,j)?1:0);
        map.put(i+"-"+j, res);
        //System.out.println(s.substring(i,j+1)+" -> "+res);
        return res;
    }

    static boolean isSpecialStr(String s, int i, int j) {
        if (j==i) return false;

        char ch = s.charAt(i);
        for (int k=i+1;k<=j;k++) {
            if (s.charAt(k) != ch) {
                if ((j-i+1)%2==1 && k == i+(j-i)/2)
                    continue;
                return false;
            }
        }

        //System.out.println("Special: "+s.substring(i, j+1));
        return true;
    }
}
