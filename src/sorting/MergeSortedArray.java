package sorting;

public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);
        System.out.println(java.util.Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]

        int[] nums1b = {1};
        merge(nums1b, 1, new int[]{}, 0);
        System.out.println(java.util.Arrays.toString(nums1b)); // [1]
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;          // pointer to last real element in nums1
        int j = n - 1;          // pointer to last element in nums2
        int k = m + n - 1;      // pointer to last slot in nums1 (where we write)

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}

/*
What each part is doing:

* i tracks the last real (non-zero-placeholder) value in nums1, j tracks the last value in nums2, and k tracks the last slot in nums1 overall — the actual position we're about to fill.

* At each step, we compare nums1[i] and nums2[j] — whichever is bigger gets placed at nums1[k], since we're building the array biggest-to-smallest, back to front.

* i >= 0 && guards against running off the front of nums1 — if nums1's real values are exhausted first, we just keep copying from nums2 for the rest.

* Notice we never need an explicit check for "j ran out" the same way — the while (j >= 0) loop condition handles that: once all of nums2 is placed, we stop, because any remaining values in nums1 are already sitting in their correct final positions (they started there, sorted, and never needed to move).

 */