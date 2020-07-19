package hackerrank.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphLibrary {

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib < c_road) return (long)c_lib*n;

        long totalCost = 0;
        long roads = 0;
        Map<Integer, Set<Integer>> citiesMap = new HashMap<>();
        for (int i=0;i<cities.length;i++) {
            if (!citiesMap.containsKey(cities[i][0])) citiesMap.put(cities[i][0], new HashSet<>());
            citiesMap.get(cities[i][0]).add(cities[i][1]);

            if (!citiesMap.containsKey(cities[i][1])) citiesMap.put(cities[i][1], new HashSet<>());
            citiesMap.get(cities[i][1]).add(cities[i][0]);
        }

        //System.out.println("Adjacency: "+citiesMap);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        for (int i=1;i<=n;i++) {
            if (visited[i])
                continue;


            queue.add(i);
            visited[i] = true;
            while(queue.size() > 0) {
                int node = queue.poll();
                //System.out.println(node);

                if (citiesMap.containsKey(node)) {
                    for (int edge : citiesMap.get(node)) {
                        if (!visited[edge]) {
                            visited[edge] = true;
                            queue.offer(edge);
                            roads++;
                        }
                    }
                }
            }
        }

        return (n-roads)*c_lib + (roads*c_road);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        int q = Integer.parseInt(reader.readLine());
        String line = null;
        for (int k=0;k<q;k++) {
            String[] nmC_libC_road = reader.readLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = reader.readLine().split(" ");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            System.out.println(result);
        }

        reader.close();
    }
}
