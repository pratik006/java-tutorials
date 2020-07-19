package hackerrank.array;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingTriplets {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input1.txt")));
        String line = reader.readLine();
        List<Long> input = Arrays.stream(line.split(" ")).map(s->Long.parseLong(s)).collect(Collectors.toList());
        System.out.println(countTriplets(input, 3));
        reader.close();
    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> map1 = new HashMap<>();
        Map<Long, Long> map2 = new HashMap<>();
        long count = 0;

        for (Long i : arr) {
            if (i%r == 0) {
                if (map1.containsKey(i/r)) {
                    if(map2.containsKey(i/r)) {count += map2.get(i/r);}

                    map2.put(i, map2.getOrDefault(i, 0L) + map1.get(i / r));
                }
            }


            map1.put(i, map1.getOrDefault(i, 0L)+1);
        }
        return count;
    }
}
