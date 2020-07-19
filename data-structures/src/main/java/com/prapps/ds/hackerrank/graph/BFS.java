package hackerrank.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.bfs(2);
    }

    static class Graph {
        int v;
        LinkedList<Integer> adj[];
        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i=0;i<v;i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int node) {
            adj[v].add(node);
        }

        public void bfs(int node) {
            boolean visited[] = new boolean[v];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(node);
            visited[node] = true;
            System.out.print(node);
            while (queue.size() > 0) {
                int nodeId = queue.poll();
                for (int edge : adj[nodeId]) {
                    if (!visited[edge]) {
                        visited[edge] = true;
                        queue.offer(edge);
                        System.out.print(edge+" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
