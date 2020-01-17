package offer;

import java.util.LinkedList;

/**
 *最短路径和
 */
public class CopeTest51 {


    //查找指定节点的标记
    public static boolean bLeafIsFound = false;
    public static Integer number = 0;
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;


        @Override
        public String toString() {
            return "TreeNode [value=" + value + "]";
        }
    }

    /**
     * 求公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
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

    /**
     * 单个结点到公共祖先的路径和
     * @param root  公共祖先
     * @param path
     * @param nodeToFind 单个结点
     * @return
     */
    public static Integer findPath(TreeNode root, LinkedList<Integer> path, TreeNode nodeToFind){
        if (root == null) {
            return null;
        }
        //将路径节点添加到栈中，用于记录结点
        path.push(root.value);
        //如果到达了子节点，就是从公共祖先进行遍历 两者一样说明找到啦 重合啦
        if (!bLeafIsFound && root.value == nodeToFind.value) {
            //打印路径
            //number = getLength(path);
            number = path.size() - 1;
            bLeafIsFound = true;
            return number;

        }
        //查询左子树
        if (!bLeafIsFound && root.left != null) {
            findPath(root.left,path, nodeToFind);
        }
        //查询右子树
        if (!bLeafIsFound && root.right != null) {
            findPath(root.right, path, nodeToFind);
        }
        //如果没找到则弹栈
        if (!bLeafIsFound) {
            path.pop();
        }
        return number == null ? null : number;
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

        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        //  找到公共的祖先
        TreeNode treeNode = lowestCommonAncestor(node1, node6, node7);
        int s1 = findPath(treeNode, list1, node6);
        bLeafIsFound = false;//全局变量复位
        int s2 = findPath(treeNode, list2, node7);
        System.out.println(s2 + s2);

    }


}
