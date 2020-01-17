package offer;

public class Test16 {


    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList(ListNode head) {
        ListNode root = new ListNode();
        root.next = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = root.next;
            root.next = head;
            head = next;
        }
        return root.next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * 【书本上的方法，不使用逻辑头结点】
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode reverseHead = null;
        ListNode curr = head;// 迭代的中间变量
        ListNode prev = null;
        ListNode next;
        /**
         * 第一次遍历
         * 1 当前的节点的下个结点复制
         * 2 当前结点作为前一个结点
         * 3 下个结点作为当前结点，进行下次迭代
         * 用第一个数字去记忆 规律
         *
         *
         */
        while (curr != null) {
            next = curr.next;
            if (next == null) {
                reverseHead = curr;
            }
            //  1 2 3
            curr.next = prev;
            prev = curr;
            curr = next;// 遍历到下一个
        }
        // 返回转后的头结点
        return reverseHead;
    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode();
        ListNode node2 = new ListNode();
        ListNode node3 = new ListNode();
        node1.next = node2;
        node2.next = node3;
        node1.value = 3;
        node2.value = 2;
        node3.value = 1;
        ListNode listNode = reverseList2(node1);
        // 遍历链表输出
        while (listNode != null) {
            System.out.print(listNode.value + "->");
            listNode = listNode.next;
        }

    }

}
