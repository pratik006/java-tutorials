package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SurroundedRegions {
    class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public boolean equals(Object other) {
            Point otherPt = (Point) other;
            return otherPt.r == r && otherPt.c == c;
        }
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) {
        SurroundedRegions test  = new SurroundedRegions();
        test.solve(new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}});
    }

    public void solve(char[][] board) {
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                if (board[i][j] == 'O') {
                    Set<Point> set = new HashSet<>();
                    if (solve(board, i, j, set)) {
                        for (Point pos : set) {
                            board[pos.r][pos.c] = 'X';
                        }
                    }
                }
            }
        }

        for (int i=0;i<board.length;i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public boolean solve(char[][] board, int row, int col, Set<Point> path) {
        if (row < 0 || col < 0) return false;
        if (row == 0 || col == 0 || row == board.length-1 || col == board[row].length-1) {
            return board[row][col] == 'X';
        }
        if (path.contains(new Point(row, col))) return true;

        //System.out.println(row+","+col+" "+board[row][col]);
        path.add(new Point(row, col));
        for (int r=row-1;r<=row+1;r++) {
            if (r == row) continue;
            if (board[r][col] == 'O') {
                boolean temp = solve(board, r, col, path);
                if (!temp) {
                    return false;
                }
            }
        }

        for (int c=col-1;c<=col+1;c++) {
            if (c == col) continue;
            if (board[row][c] == 'O') {
                boolean temp = solve(board, row, c, path);
                if (!temp) {
                    return false;
                }
            }
        }

        return true;
    }
}
