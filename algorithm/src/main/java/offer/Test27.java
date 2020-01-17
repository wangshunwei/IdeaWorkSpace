package offer;

public class Test27 {


    /**
     * 二叉树的树结点  不太好理解
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static BinaryTreeNode convert(BinaryTreeNode root) {
        // 用于保存处理过程中的双向链表的尾结点
        BinaryTreeNode[] lastNode = new BinaryTreeNode[1];
        convertNode(root, lastNode);
        // 找到双向链表的头结点
        BinaryTreeNode head = lastNode[0];
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static void convertNode(BinaryTreeNode node, BinaryTreeNode[] lastNode) {
        // 结点不为空
        if (node != null) {
            // 如果有左子树就先处理左子树
            if (node.left != null) {
                convertNode(node.left, lastNode);
            }
            node.left = lastNode[0];
            // 如果左子树转换成的双向链表不为空，设置尾结点的后继
            if (lastNode[0] != null) {
                lastNode[0].right = node;
            }
            lastNode[0] = node;
            if (node.right != null) {
                convertNode(node.right, lastNode);
            }
        }
    }
}
