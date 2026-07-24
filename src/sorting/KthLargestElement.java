package sorting;

import java.util.Random;

public class KthLargestElement {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));       // 5
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
    }

    public static int findKthLargest(int[] nums, int k) {
        int targetIndex = nums.length - k; // k-th largest = (n-k)-th smallest, 0-indexed
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private static int quickSelect(int[] nums, int left, int right, int targetIndex) {
        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, right, targetIndex); // search right side only
        } else {
            return quickSelect(nums, left, pivotIndex - 1, targetIndex);  // search left side only
        }
    }

    private static int partition(int[] nums, int left, int right) {
        // pick a random pivot to avoid worst-case O(n^2) on already-sorted input
        int randomIndex = left + new Random().nextInt(right - left + 1);
        swap(nums, randomIndex, right); // move pivot to the end temporarily

        int pivotValue = nums[right];
        int boundary = left; // everything before 'boundary' is < pivotValue

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, boundary);
                boundary++;
            }
        }

        swap(nums, boundary, right); // place pivot in its final sorted position
        return boundary;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}