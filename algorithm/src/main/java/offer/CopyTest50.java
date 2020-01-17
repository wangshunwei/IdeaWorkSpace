package offer;

/**
 * 普通的二叉树不是搜索树
 *
 * 从上往下遍历树 看输入的任意两个结点是不是当前的节点的左右子树
 *
 */
public class CopyTest50 {


    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;


        @Override
        public String toString() {
            return "TreeNode [value=" + value + "]";
        }
    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }

    public static void main(String[] args) {

        // 1 2 3 4 5 6 7

        /**
         *普通树
         *        7
         *      /   \
         *     4     1
         *    / \   / \
         *   6   3  5  2
         */
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();
        TreeNode node7 = new TreeNode();
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node1.value = 7;
        node2.value = 4;
        node3.value = 1;
        node4.value = 6;
        node5.value = 3;
        node6.value = 5;
        node7.value = 2;

        /**
         *
         * 根节点 node1
         * 任意两个公共结点
         *
         */
        TreeNode parentNode = lowestCommonAncestor(node1,node6, node7);
        System.out.println(parentNode.value);

    }


}
