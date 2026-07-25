package stack;

import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
        int[] result = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(java.util.Arrays.toString(result)); // [1, 1, 4, 2, 1, 1, 0, 0]
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }

        return answer; // any index left on the stack never found a warmer day, stays 0 by default
    }
}