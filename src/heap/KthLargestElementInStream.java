package heap;

import java.util.PriorityQueue;

/**
 * PROBLEM: Design a class that returns the k-th largest element after each addition
 * to a growing stream of numbers.
 * LINK: https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * APPROACH: Maintain a min-heap of size k containing the k largest values seen so far.
 * The top of that heap is always the k-th largest overall.
 * PATTERN: Heap / Priority Queue (top-K pattern)
 *
 * WHY IT WORKS: If the heap holds exactly the k largest values, the SMALLEST among
 * those k (the heap's top) is by definition the k-th largest of everything seen.
 * New values smaller than the heap's min can never be in the top k, so they're ignored.
 *
 * EXAMPLE: k=3, heap holds {4,5,8} after init. add(3): 3<4(top), ignored, still returns 4.
 * add(10): 10>4, evict 4, add 10 -> heap {5,8,10}, top=5, returns 5.
 *
 * TIME: O(log k) per add | SPACE: O(k)
 */
public class KthLargestElementInStream {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll(); // evict the smallest, since heap should only ever hold k values
        }
        return minHeap.peek(); // top of min-heap = smallest among the k largest = k-th largest
    }

    public static void main(String[] args) {
        KthLargestElementInStream kthLargest = new KthLargestElementInStream(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));  // 4
        System.out.println(kthLargest.add(5));  // 5
        System.out.println(kthLargest.add(10)); // 5
        System.out.println(kthLargest.add(9));  // 8
    }
}