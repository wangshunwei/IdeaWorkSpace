package offer;

public class Test17 {

    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     * 非递归方式   归并的思想
     * @param head1 第一个有序链表
     * @param head2 第二个有序链表
     * @return 合并后的有序链表头
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        // 创建新的链表结点，用于添加元素时方便
        ListNode mergeHead = new ListNode();
        // 用于指向合并后的新的合并后的头结点开始 相当于数组的开始指针
        // 当两个链表都不为空就进行合并操作
        while (head1 != null && head2 != null) {
            // 下面的操作合并较小的元素
            if (head1.value < head2.value) {
                mergeHead = head1;
                //移动比较后链表指针，另一个链表还是原来的指针位置
                head1 = head1.next;
            } else {
                mergeHead = head2;
                head2 = head2.next;
            }
            // 移动新链表指针
            mergeHead = mergeHead.next;
        }
        // 如果第一个链表的元素未处理完将其，接到合并链表的最后一个结点之后
        while (head1 != null) {
            mergeHead.next = head1;
            head1 = head1.next;
        }
        // 如果第二个链表的元素未处理完将其，接到合并链表的最后一个结点之后
        while (head2 != null) {
            mergeHead.next = head2;
            head2 = head2.next;
        }
        // 返回处理结果
        return mergeHead;
    }

}
