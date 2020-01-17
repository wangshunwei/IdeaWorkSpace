package offer;

// 链表操作

/**
 * 一般遍历
 *  for (Node x = first; x != null; x = x.next){
 *      // 处理 x.item;
 *  }
 */
public class Test13 {

    /**
     * 链表结点
     */
    public static class ListNode {
        int value; // 保存链表的值
        ListNode next; // 下一个结点
    }

    public static ListNode deleteNode(ListNode head, ListNode toBeDeleted) {
        // 没有节点的时候
        if (head == null || toBeDeleted == null) {
            return head;
        }
        // 只有一个节点
        if (head == toBeDeleted) {
            head = null;// 结点置为null
            toBeDeleted = null;// 结点置为null
            return head;
        }
        // 存在多个节点的时候，删除的是尾节点，进行遍历
        if (toBeDeleted.next == null) {
            // 从head开始遍历
            ListNode temp = head;
            while (temp.next != toBeDeleted) {
                temp = temp.next;
            }
            // 找到啦尾节点进行删除引用变为null
            toBeDeleted = null;
            temp.next = null;
        } else {
            // 删除的是中间节点 比如是第i个节点 toBeDeleted
            toBeDeleted.value = toBeDeleted.next.value;
            toBeDeleted.next = toBeDeleted.next.next;
            toBeDeleted.next.next = null;// 删除原来的j节点
        }
        return head;
    }

}
