package offer;

public class Test37 {


    /**
     *
     * 普通二叉树 可以看做成两个链表的第一个公共的节点s
     * 二叉搜索树
     *
     * 链表结点类
     */
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }


    public static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        int length1 = getListLength(head1);
        int length2 = getListLength(head2);

        int diff = length1 - length2;
        ListNode longListHead = head1;
        ListNode shortListHead = head2;

        if (diff < 0) {
            longListHead = head2;
            shortListHead = head1;
            diff = length2 - length1;
        }
        // 长的链表先提前走diff步 然后同时一起走
        for (int i = 0; i < diff; i++) {
            longListHead = longListHead.next;
        }
        // 一起走 迭代进行遍历
        while (longListHead != null && shortListHead != null && longListHead != shortListHead) {
            longListHead = longListHead.next;
            shortListHead = shortListHead.next;
        }
        // 返回第一个相同的公共结点，如果没有返回null
        return longListHead;
    }

    private static int getListLength(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }
        return result;
    }

}
