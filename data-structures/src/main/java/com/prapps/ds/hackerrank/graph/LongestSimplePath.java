package hackerrank.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LongestSimplePath {
    // Complete the maxRegion function below.
    static int maxRegion2(int[][] grid) {
        for (int i=0;i<grid.length;i++)
            System.out.println(Arrays.toString(grid[i]));
        System.out.println();
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[i].length;j++) {
                if (grid[i][j] > 0) {
                    for (int r = i-1;r<=i+1;r++) {
                        for(int c=j-1;c<=j+1;c++) {
                            if (r == i && c == j) continue;
                            if (r<0 || r>=grid.length || c<0 || c>=grid[r].length) continue;
                            if (grid[r][c] > 0) {
                                grid[r][c]++;
                            }
                        }
                    }
                }
            }
        }
        for (int i=0;i<grid.length;i++)
            System.out.println(Arrays.toString(grid[i]));
        return 0;
    }

    static int maxRegion(int[][] grid) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[i].length;j++) {
                if (grid[i][j] == 1) {
                    int curIndex = i*grid[i].length+j;
                    adj.put(curIndex, new HashSet<>());
                    if (j+1 < grid[i].length && grid[i][j+1] == 1) {
                        int otherIndex = i*grid[i].length+j+1;
                        adj.get(curIndex).add(otherIndex);
                    }
                    if (j-1 >= 0 && grid[i][j-1] == 1) {
                        int otherIndex = i*grid[i].length+j-1;
                        adj.get(curIndex).add(otherIndex);
                    }
                    if (i+1 < grid.length && grid[i+1][j] == 1) {
                        adj.get(curIndex).add((i+1)*grid[i].length+j);
                    }
                    if (i-1 >= 0 && grid[i-1][j] == 1) {
                        int otherIndex = (i-1)*grid[i].length+j;
                        adj.get(curIndex).add(otherIndex);
                    }
                    if (i+1 < grid.length && j+1 < grid[i].length && grid[i+1][j+1] == 1) {
                        adj.get(curIndex).add((i+1)*grid[i].length+j+1);
                    }
                    if (i-1 >= 0 && j-1 >=0 && grid[i-1][j-1] == 1) {
                        int otherIndex = (i-1)*grid[i].length+j-1;
                        adj.get(curIndex).add(otherIndex);
                    }
                    if (i+1 < grid.length && j-1 >= 0 && grid[i+1][j-1] == 1) {
                        adj.get(curIndex).add((i+1)*grid[i].length+j-1);
                    }
                    if (i-1 >= 0 && j+1 < grid[i].length && grid[i-1][j+1] == 1) {
                        int otherIndex = (i-1)*grid[i].length+j+1;
                        adj.get(curIndex).add(otherIndex);
                    }
                }
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : adj.entrySet()) {
            Stack<Integer> parents = new Stack<>();
            parents.push(entry.getKey());
            int size = traverse(adj, entry.getKey(), parents);
            max = Math.max(max, size);
        }

        System.out.println(max);
        return max;
    }

    static int traverse(Map<Integer, Set<Integer>> adj, int node, Stack<Integer> parents) {
        //System.out.println(parents);
        int max = 0;
        for (Integer neighbour : adj.get(node)) {
            if (!parents.contains(neighbour)) {
                parents.push(neighbour);
                int size = traverse(adj, neighbour, parents);
                max = Math.max(max, size);
                parents.pop();
            }
        }

        return 1+max;
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(maxCircle(new int[][]{{1000000000, 23},{11, 3778},{7, 47},{11, 1000000000}}));
        BufferedReader bufferedWriter = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(bufferedWriter.readLine());
        int m = Integer.parseInt(bufferedWriter.readLine());

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = bufferedWriter.readLine().split(" ");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion3(grid);
        System.out.println(res);
    }

    static int[] maxCircle(int[][] queries) {
        int[] res = new int[queries.length];
        int max = 0;
        List<Set<Integer>> list = new ArrayList<>();

        for (int q=0;q<queries.length;q++) {
            int a=-1, b=-1;
            int n1 = queries[q][0], n2 = queries[q][1];
            for (int i=0;i<list.size();i++) {
                a = a==-1?list.get(i).contains(n1)?i:-1:a;
                b = b==-1?list.get(i).contains(n2)?i:-1:b;
            }
            System.out.println(a+" "+b);
            if (a == b && a != -1) {
                //existing in both list
            } else if (a != -1 && b != -1) {
                list.get(a).addAll(list.get(b));
                list.remove(b);
                max = Math.max(max, list.get(a).size());
            } else if (a != -1 && b == -1) {
                list.get(a).add(n2);
                max = Math.max(max, list.get(a).size());
            } else if (a == -1 && b != -1) {
                list.get(b).add(n1);
                max = Math.max(max, list.get(b).size());
            } else {
                Set<Integer> set = new HashSet();
                set.add(n1);
                set.add(n2);
                list.add(set);
                max = Math.max(max, set.size());
            }
            System.out.println(list);
            res[q] = max;
        }

        return res;
    }

    static int maxRegion3(int[][] grid) {
        int max = 0;
        for (int row=0; row<grid.length; row++) {
            for (int col=0; col<grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    max = Math.max(max, regionSize(grid, row, col));
                }
            }
        }

        return max;
    }

    static int regionSize(int[][] grid, int row, int col) {
        if (row >= grid.length || row < 0 || col >= grid[row].length || col < 0) {
            return 0;
        }
        if (grid[row][col] == 0) return 0;

        grid[row][col] = 0;
        int size = 1;
        for (int r=row-1;r<=row+1;r++) {
            for (int c = col-1;c<=col+1;c++) {
                if (r == row && c == col) continue;
                size += regionSize(grid, r, c);
            }
        }

        return size;
    }
}
