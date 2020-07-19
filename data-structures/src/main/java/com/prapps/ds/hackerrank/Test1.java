package hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class Test1 {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> map2 = new HashMap<>();
        for (List<Integer> q : queries) {
            System.out.println(q);
            if (q.get(0) == 1) {
                Integer existingFreq = map.getOrDefault(q.get(1), 0);
                map.put(q.get(1), map.getOrDefault(q.get(1), 0) + 1);

                if (existingFreq == 0) {
                    if (!map2.containsKey(1)) map2.put(1, new HashSet<Integer>());
                    map2.get(1L).add(q.get(1));
                } else {
                    map2.get(existingFreq).remove(q.get(1));
                    if (!map2.containsKey(existingFreq+1)) map2.put(existingFreq+1, new HashSet<Integer>());
                    map2.get(existingFreq+1).add(q.get(1));
                }
            } else if (q.get(0) == 2) {
                if (map.containsKey(q.get(1))) {
                    Integer existingFreq = map.get(q.get(1));
                    map2.get(existingFreq).remove(q.get(1));
                    if (!map2.containsKey(existingFreq-1)) map2.put(existingFreq-1, new HashSet<Integer>());
                    map2.get(existingFreq-1).add(q.get(1));
                    map.put(q.get(1), map.get(q.get(1)) - 1);
                }
            } else if (q.get(0) == 3) {
                result.add(map2.containsKey(q.get(1)) ? 1 : 0);
            }
            //System.out.println(map2);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            //List<Integer> ans = freqQuery(queries);
            /*try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                bufferedWriter.write(
                        ans.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n");
            }*/
            //System.out.println(ans);
        }
    }
}
