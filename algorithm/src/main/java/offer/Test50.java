package offer;

public class Test50 {

    /**
     * 68题也是公共祖先的问题 第二版本   搜索二叉树的情况下
     *
     */

    public static class TreeNode {
     int value;
     TreeNode left;
     TreeNode right;


        @Override
    public String toString() {
                   return "TreeNode [value=" + value + "]";
               }
    }


    private static TreeNode findLCA(TreeNode node1, TreeNode node2, TreeNode current) {
        int value =  current.value;
        if(value > node1.value && value > node2.value ) {
            return findLCA(node1,node2,current.left);
        }
        if(value < node1.value && value < node2.value ) {
            return findLCA(node1,node2,current.right);
        }
            return current;
    }

    public static void main(String[] args) {

        // 1 2 3 4 5 6 7

        /**
         *
         *        4
         *      /   \
         *     2     6
         *    / \   / \
         *   1   3  5  7
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
        node1.value = 4;
        node2.value = 2;
        node3.value = 6;
        node4.value = 1;
        node5.value = 3;
        node6.value = 5;
        node7.value = 7;

        /**
         *
         * 根节点 node1
         * 任意两个公共结点
         *
         */
        TreeNode parentNode = findLCA(node6, node7,node1);
        System.out.println(parentNode.value);

    }


}
