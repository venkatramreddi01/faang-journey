package linkedlist;

/**
 * PROBLEM: Determine if a linked list has a cycle.
 * LINK: https://leetcode.com/problems/linked-list-cycle/
 *
 * APPROACH: Two pointers, slow (1 step) and fast (2 steps), walk simultaneously.
 * If they ever meet, there's a cycle. If fast hits null, there isn't one.
 * PATTERN: Linked List (Floyd's Cycle Detection / Fast-Slow Pointers)
 *
 * WHY IT WORKS: On a cycle, fast gains 1 step on slow every iteration — like a
 * faster runner lapping a slower one on a circular track. They're guaranteed to meet.
 *
 * EXAMPLE: 1->2->3->4->(back to 2). slow/fast start at 1, eventually both land on
 * the same node inside the loop -> cycle detected -> true.
 *
 * TIME: O(n) | SPACE: O(1)
 */
public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next; // creates a cycle back to node 2

        System.out.println(hasCycle(head)); // true

        ListNode noCycleHead = new ListNode(1);
        noCycleHead.next = new ListNode(2);
        System.out.println(hasCycle(noCycleHead)); // false
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}