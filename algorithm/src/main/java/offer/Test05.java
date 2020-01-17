package offer;

/**
 * 递归从尾到头打印链表
 *
 */
public class Test05 {

    public static class Node {
        int val; // 结点的值
        Node nxt; // 下一个结点
    }

    /**
     * 递归打印链表 从尾到头
     * @param node
     */
    public  static void printNode(Node node) {
        if (node != null) {
            if (node.nxt != null) {
                printNode(node);
            }
            System.out.println(node.val);
        }
        System.out.println("链表为空");
    }

    /**
     * 从头到尾打印链表
     */
    public static void printNode1(Node node) {
        if (node != null) {
            // node干一些事
            System.out.println(node.val + " ");
            if (node.nxt != null) {
                // node.next干一些事
            printNode1(node.nxt);
            }
        }
    }
}
