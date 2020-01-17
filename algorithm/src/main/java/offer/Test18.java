package offer;

public class Test18 {

    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }
    public static boolean hasSubtree(BinaryTreeNode root1, BinaryTreeNode root2) {
        // 只要两个对象是同一个就返回true
        // 【注意此处与书本上的不同，书本上的没有这一步】
        if (root1 == root2) {
            return true;
        }
        // 只要树B的根结点点为空就返回true
        if (root2 == null) {
            return true;
        }
        // 树B的根结点不为空，如果树A的根结点为空就返回false
        if (root1 == null) {
            return false;
        }
        // 记录匹配结果
        boolean result = false;
        // 如果结点的值相等就，调用匹配方法
        if (root1.value == root2.value) {
            result = match(root1, root2);
        }
        // 如果匹配就直接返回结果
        if (result) {
            return true;
        }
        // 如果不匹配就找树A的左子结点和右子结点进行判断
        return hasSubtree(root1.left, root2) || hasSubtree(root1.right, root2);
    }
    public static boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {
        // 只要两个对象是同一个就返回true
        if (root1 == root2) {
            return true;
        }
        // 只要树B的根结点点为空就返回true
        if (root2 == null) {
            return true;
        }
        // 树B的根结点不为空，如果树A的根结点为空就返回false
        if (root1 == null) {
            return false;
        }
        // 如果两个结点的值相等，则分别判断其左子结点和右子结点
        if (root1.value == root2.value) {
            return match(root1.left, root2.left) && match(root1.right, root2.right);
        }
        // 结点值不相等返回false
        return false;
    }

}
