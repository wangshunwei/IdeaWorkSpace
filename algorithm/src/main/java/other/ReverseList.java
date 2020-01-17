package other;

/**
 * 翻转链表
 *
 */
public class ReverseList {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = null;
        while (head != null) {
            ListNode p = head.next;
            head.next = temp;
            temp = head;
            head = p;
        }
        return temp;
    }
}
