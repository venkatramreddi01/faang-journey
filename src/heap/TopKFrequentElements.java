package heap;

import java.util.*;

/**
 * PROBLEM: Return the k most frequent elements in an array.
 * LINK: https://leetcode.com/problems/top-k-frequent-elements/
 *
 * APPROACH: Count frequencies with a HashMap (like Valid Anagram), then use a
 * min-heap of size k — same top-K pattern as Kth Largest in a Stream — but ordered
 * by FREQUENCY instead of the raw value.
 * PATTERN: HashMap (frequency count) + Heap (Top-K)
 *
 * WHY IT WORKS: Same reasoning as the stream problem — keeping only the k highest-
 * frequency entries means anything evicted was guaranteed too infrequent to matter.
 *
 * EXAMPLE: nums=[1,1,1,2,2,3], k=2. counts={1:3,2:2,3:1}.
 * Heap keeps top 2 by frequency -> [1,2]
 *
 * TIME: O(n log k) | SPACE: O(n)
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [2, 1] or [1, 2]
        System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));                // [1]
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // min-heap ordered by frequency (the count), not the number itself
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) {
                minHeap.poll(); // evict the lowest-frequency entry
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll()[0]; // [0] = the number itself, not its count
        }

        return result;
    }
}