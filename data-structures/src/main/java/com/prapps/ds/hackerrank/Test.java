package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int[] input = new int[] {546, 54, 548, 60 };

        Map<Integer, List<Integer>> nosMap = new HashMap<>();
        for (int i=0;i<input.length;i++) {
            int fdgt = getFirstDgt(input[i]);
            if (!nosMap.containsKey(fdgt)) {
                nosMap.put(fdgt, new ArrayList<>());
            }
            nosMap.get(fdgt).add(input[i]);
        }

        StringBuilder res = new StringBuilder();
        for (int i=9; i>=1;i--) {
            if (nosMap.containsKey(i)) {
                List<Integer> list = nosMap.get(i);
                Collections.sort(list, (o1, o2) -> {
                    int l1 = String.valueOf(o1).length();
                    int l2 = String.valueOf(o2).length();;
                    int diff = l1 - l2;
                    if (diff == 0) {
                        return o2-o1;
                    } else {
                        if (l2 > l1) {
                            for (int j=0;j<Math.abs(diff) && j<l1;j++) {
                                char ch1 = String.valueOf(o1).charAt(j+l1);
                                char ch2 = String.valueOf(o2).charAt(j);
                                if (ch1 != ch2)
                                    return ch1 - ch2;
                            }
                        } else {
                            for (int j=0;j<diff && j<l2;j++) {
                                char ch1 = String.valueOf(o1).charAt(j+l2);
                                char ch2 = String.valueOf(o2).charAt(j);
                                if (ch1 != ch2)
                                    return ch2 - ch1;
                            }
                        }

                        return diff;
                    }
                });
                list.forEach(n -> res.append(n));
            }
        }

        long finalResult =  Long.parseLong(res.toString());
        System.out.println("input: "+Arrays.toString(input)+"\tfinalResult : " + finalResult);
    }

    static int getFirstDgt(int n) {
        while (n/10 > 0)
            n = n/10;

        return n;
    }
}
