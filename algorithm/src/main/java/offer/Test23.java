package offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上往下打印二叉树，也就只之字形打印二叉树
 */
public class Test23 {

    public static class BinaryTreeNode{
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }
    public static void printNode(BinaryTreeNode root) {
        if (root != null) {
            Queue<BinaryTreeNode> queue = new LinkedList<>();// 实现啦队列的接口
            queue.add(root);
            BinaryTreeNode currentNode;
            while (!queue.isEmpty()) {
                 currentNode = queue.remove();
                System.out.println(currentNode.value);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

        }
    }





}
