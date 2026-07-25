package recursion;

public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(3)); // 3
        System.out.println(climbStairs(5)); // 8
    }

    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1; // base case: ways(0)=1, ways(1)=1
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}

/*
Sample for (n = 3):-
===================

                climbStairs(3)
               /              \
      climbStairs(2)        climbStairs(1)
      /          \              → returns 1 (base case)
climbStairs(1)  climbStairs(0)
  → returns 1     → returns 1

climbStairs(2) = 1 + 1 = 2
climbStairs(3) = 2 + 1 = 3 ✅


 */