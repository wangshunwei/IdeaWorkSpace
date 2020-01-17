package other;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * 从尾到头打印链表  里面的数值(val)
 *
 */
public class printListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) {
            return list;
        }
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop().val);
        }
        return  list;
    }
}
