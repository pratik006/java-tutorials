package hackerrank;

public class CountingStairs {
    public static void main(String[] args) {
        System.out.println(fib(5, 2));
    }

    static int fib(int S, int n) {
        if (S == 0) return 1;
        return fib(S,n-1)+fib(S,n-2)+fib(S,n-3);
    }
}
