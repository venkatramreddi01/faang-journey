package linkedlist;

public class ReverseLinkedList {

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
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = reverseList(head);
        printList(newHead); // 5 4 3 2 1
    }

    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next; // save before overwriting
            current.next = previous;       // flip the arrow
            previous = current;            // move previous forward
            current = next;                // move current forward
        }

        return previous; // when current becomes null, previous IS the new head
    }

    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}