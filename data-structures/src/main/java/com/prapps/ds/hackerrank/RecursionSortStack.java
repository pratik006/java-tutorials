package hackerrank;

import java.util.Collections;
import java.util.Stack;

public class RecursionSortStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(1);
        stack.push(5);
        stack.push(0);
        sort(stack);
        System.out.println(stack);
        //StringBuilder sb;sb.deleteCharAt()
    }

    static void sort(Stack<Integer> stack) {
        if (stack.size() == 1) return;
        int val = stack.pop();
        sort(stack);
        insert(stack, val);
    }

    static void insert(Stack<Integer> stack, int val) {
        if (stack.size() == 0 || stack.peek() < val) {
            stack.push(val);
            return;
        }
        int preval = stack.pop();
        insert(stack, val);
        stack.push(preval);
    }
}
