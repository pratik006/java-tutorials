import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {
    static final int BASE = 1000;
    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
       return rotLeft(a, d, 0);
    }

    static int[] rotLeft(int[] a, int d, int start) {
        System.out.println("rot "+d+" len: "+(a.length-start)+"\n\n");
        int q = ((a.length-start)/d);
        int r = (a.length - start) %d;
        int tc =0;
        while (q>0) {
            int c = 0;
            for(int i=start;i<start+d && i+d<a.length;i++) {
                int temp = a[i];
                a[i] = a[i+d];
                a[i+d] = temp;
                c++;
                //System.out.println(Arrays.toString(a));
            }
            start += c;
            tc += c;
            q--;
        }
        //System.out.println();
        if (start < a.length && r != 0) {
            if (2*d > a.length-start) {
                int d1 = d;
                while (d1-tc <0)
                    d1=d1+d;
                return rotLeft(a, d1-tc, start);
            } else {
                return rotLeft(a, d, start);
            }
        }
        return a;
    }

    static int sherlockAndAnagrams(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            for (int j=0;j<=i;j++) {
                Integer key = getSum(s, j, i+1);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key)+1);
                } else {
                    map.put(key, 1);
                }
                System.out.println();
            }
        }

        int count = 0;
        List<Integer> values = map.values().stream().filter(v->v>1).collect(Collectors.toList());
        for (Integer val : values) {
            if (val > 1)
                count += ((val-1)*val)/2;
        }

        return count;

    }

    static int getSum(String s, int start, int end) {
        int sum = 0;
        for (int i=start;i<end;i++) {
            System.out.print(s.charAt(i));
            sum += ((int)s.charAt(i)) + BASE;
        }
        System.out.print(" -> "+sum);
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int l = 9787;
        int d = 2895;
        int input[] = new int[l];
        for (int i=0;i<l;i++) {
            input[i] = i+1;
        }

        System.out.println(sherlockAndAnagrams("mqmtjwxaaaxklheghvqcyhaaegtlyntxmoluqlzvuzgkwhkkfpwarkckansgabfclzgnumdrojexnrdunivxqjzfbzsodycnsnmw"));
    }
}
