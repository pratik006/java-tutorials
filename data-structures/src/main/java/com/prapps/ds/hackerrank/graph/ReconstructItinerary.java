package hackerrank.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class ReconstructItinerary {

    public static class Graph {
        int edges = 1;
        Map<String, PriorityQueue<String>> adj = new HashMap<>();

        public void addEdge(String from, String to) {
            if (!adj.containsKey(from)) {
                adj.put(from, new PriorityQueue<>());
            }
            adj.get(from).add(to);
            edges++;
        }

        public void search(String from, LinkedList<String> res) {
            PriorityQueue<String> pq = adj.getOrDefault(from, new PriorityQueue<>());
            while (!pq.isEmpty()) {
                search(pq.poll(), res);
            }

            res.addFirst(from);
        }
    }

    public static void main(String[] args) {
        String[][] input = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        Graph graph = new Graph();
        for (int i=0;i<input.length;i++) {
            graph.addEdge(input[i][0], input[i][1]);
        }
        LinkedList<String> res = new LinkedList<>();
        graph.search("JFK", res);

        System.out.println(res);
    }
}
