package offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 */
public class Test25 {


    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void find(BinaryTreeNode root, int exepectSum) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            findPath(root, 0, exepectSum, result);
        }
    }

    public static void findPath(BinaryTreeNode root, int curSum, int exepectSum, List<Integer> result) {

        if (root != null) {
            curSum += root.value;
            result.add(root.value);
            if (curSum < exepectSum) {
                // 递归左子树
                findPath(root.left,curSum,exepectSum,result);
                // 递归右子树
                findPath(root.right, curSum, exepectSum, result);
            }
            if (curSum == exepectSum) {
                // 是否是叶子结点
                if (root.left == null && root.right == null) {
                    System.out.println(result);
                }
            }
            result.remove(result.size() - 1);

        }


    }

}
