package binarysearch;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                // left half is sorted
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // right half is sorted
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}

/*
What each part is doing:

* nums[left] <= nums[mid] — this is the check for "is the left half sorted?" If the value at left is less than or equal to the value at mid, nothing weird (like a rotation break) happened between them, so that side is clean.
* Inside that branch, your exact condition (nums[left] <= target && target <= nums[mid]) decides whether to narrow into that sorted left half (right = mid - 1) or skip it entirely (left = mid + 1).
* The else branch mirrors this logic for when the right half is the sorted one instead — same idea, just flipped.
 */