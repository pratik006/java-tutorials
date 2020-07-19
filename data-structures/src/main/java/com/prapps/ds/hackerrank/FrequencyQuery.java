package hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class FrequencyQuery {
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<long[]> queries) {
        String s = null;
        System.out.println(s);
        List<Integer> result = new ArrayList<>();
        Map<Long, Long> map = new HashMap<>();
        Map<Long, Set<Long>> map2 = new HashMap<>();
        for (long[] q : queries) {
            //System.out.println(Arrays.toString(q));
            if (q[0] == 1) {
                Long existingFreq = map.getOrDefault(q[1], 0L);
                map.put(q[1], map.getOrDefault(q[1], 0L) + 1);

                if (existingFreq == 0) {
                    if (!map2.containsKey(1)) map2.put(1L, new HashSet<Long>());
                    map2.get(1L).add(q[1]);
                } else {
                    map2.get(existingFreq).remove(q[1]);
                    if (!map2.containsKey(existingFreq+1)) map2.put(existingFreq+1, new HashSet<Long>());
                    map2.get(existingFreq+1).add(q[1]);
                }
            } else if (q[0] == 2) {
                if (map.containsKey(q[1])) {
                    Long existingFreq = map.get(q[1]);
                    map2.get(existingFreq).remove(q[1]);
                    if (!map2.containsKey(existingFreq-1)) map2.put(existingFreq-1, new HashSet<Long>());
                    map2.get(existingFreq-1).add(q[1]);
                    map.put(q[1], map.get(q[1]) - 1);
                }
            } else if (q[0] == 3) {
                result.add(map2.containsKey(q[1]) ? 1 : 0);
            }
            //System.out.println(map2);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<long[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                long[] query = new long[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Long.parseLong(m.group(1));
                    query[1] = Long.parseLong(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            System.out.println(ans.stream()
                    .map(Object::toString)
                    .collect(joining("\n"))
                    + "\n");
        }
    }
}
