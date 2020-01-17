package offer;

public class Test15 {


    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 输入一个键表，输出该链表中倒数第k 个结点．为了符合大多数人的习惯，
     * 本题从1开始计数，即链表的尾结点是倒数第1个结点．例如一个链表有6个结点，
     * 从头结点开始它们的值依次是1、2、3、4、5 6。这个链表的倒数第3个结点是值为4的结点．
     * @param head 链表的头结点
     * @param k    倒数第k个结点
     * @return 倒数第k个结点
     */
    public static ListNode findKthToTail(ListNode head, int k) {
        // 输入的链表不能为空，并且k大于0
        if (k < 1 || head == null) {
            return null;
        }
        // 指向头结点
        ListNode pointer = head;

        // 倒数第k个结点与倒数第一个结点相隔k-1个位置
        // pointer先走k-1个位置  看i能变几次 就是指针变化啦几次，具象思维和语言的转化
        for (int i = 1; i < k; i++) {
            // 说明还有结点
            if (pointer.next != null) {
                // 代表着移动，变换当前指针
                pointer = pointer.next;
            }
            // 已经没有节点了，但是i还没有到达k-1说明k太大，链表中没有那么多的元素
            else {
                // 返回结果
                return null;
            }

        }
        // pointer还没有走到链表的末尾，那么pointer和head一起走，
        // 当pointer走到最后一个结点即，pointer.next=null时，head就是倒数第k个结点
        while (pointer.next != null) {
            //head指针到达头结点的位置
            head = head.next;
            // pointer下一个结点 ，下次循环head的下个结点作为head,完成啦指针的同时移动
            pointer = pointer.next;
        }
        // 返回结果
        return head;
    }

}
