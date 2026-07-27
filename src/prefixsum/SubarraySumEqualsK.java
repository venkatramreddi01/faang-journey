package prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Count the total number of subarrays whose sum equals k.
 * LINK: https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * APPROACH: Walk once, tracking a running prefix sum. At each step, check how many
 * earlier prefix sums equal (currentSum - k) — each one marks a valid subarray ending here.
 * PATTERN: Prefix Sum + HashMap (frequency counting)
 *
 * WHY IT WORKS: subarray sum(i+1..j) = prefix[j] - prefix[i]. We want that to equal k,
 * so prefix[i] = prefix[j] - k. Seed map with {0:1} to count subarrays starting at index 0.
 *
 * EXAMPLE: nums=[1,1,1], k=2 -> running sums 1,2,3 ; map builds {0:1,1:1,2:1,3:1}
 *          at sum=2, need sum-k=0 -> found once; at sum=3, need sum-k=1 -> found once -> total=2
 *
 * TIME: O(n) | SPACE: O(n)
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2)); // 2
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3)); // 2
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // seed: sum-of-nothing occurred once, before we start

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num;
            int needed = currentSum - k;

            if (prefixCount.containsKey(needed)) {
                count += prefixCount.get(needed);
            }

            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}

/*

What each part is doing:

* prefixCount.put(0, 1) — the seed we just reasoned through, handling subarrays that start at index 0.
* currentSum += num — instead of a full prefix array, we just keep one running total, updated as we walk (simpler than Problem 1's array, since we don't need to answer arbitrary range queries later — just count on the fly).
* needed = currentSum - k — this is the value we're searching for: exactly prefix[i] = prefix[j] - k, rearranged.
* count += prefixCount.get(needed) — note this is +=, not just checking existence. If the same prefix sum occurred 3 times before, that's 3 different valid subarrays ending here, not just 1 — this is why we count occurrences, not just presence.
* prefixCount.put(currentSum, ...) — always record the current running sum's count after checking for needed, so we never count a subarray of length zero against itself.


Dry run on nums=[1,1,1], k=2:

* Start: map={0:1}, sum=0, count=0
* num=1: sum=1. needed=1-2=-1. Not in map. map={0:1, 1:1}
* num=1: sum=2. needed=2-2=0. Found! map[0]=1 → count=1. map={0:1, 1:1, 2:1}
* num=1: sum=3. needed=3-2=1. Found! map[1]=1 → count=2. map={0:1, 1:1, 2:1, 3:1}
* Final: count=2 ✅ (matches the two [1,1] subarrays found by hand earlier)

 */