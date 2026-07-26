package prefixsum;

/**
 * PROBLEM: Given an array, answer multiple sum(left,right) range queries efficiently.
 * LINK: https://leetcode.com/problems/range-sum-query-immutable/
 *
 * APPROACH: Precompute a prefix sum array ONCE, where prefix[i] = sum of nums[0..i-1].
 * Each query then becomes a simple subtraction instead of a fresh loop.
 * PATTERN: Prefix Sum
 *
 * WHY IT WORKS: prefix[right+1] holds sum(0..right). Subtracting prefix[left]
 * (sum of 0..left-1) removes exactly the part we don't want, leaving sum(left..right).
 * Extra front slot (prefix[0]=0) avoids an out-of-bounds check when left=0.
 *
 * EXAMPLE: nums=[-2,0,3,-5,2,-1] -> prefix=[0,-2,-2,1,-4,-2,-3]
 *          sumRange(2,5) = prefix[6]-prefix[2] = -3-(-2) = -1
 *
 * TIME: O(n) to build, then O(1) per query | SPACE: O(n) for the prefix array
 */
public class RangeSumQueryImmutable {

    private int[] prefix;

    public RangeSumQueryImmutable(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }

    public static void main(String[] args) {
        RangeSumQueryImmutable obj = new RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(obj.sumRange(0, 2)); // 1
        System.out.println(obj.sumRange(2, 5)); // -1
        System.out.println(obj.sumRange(0, 5)); // -3
    }
}