package offer;

import java.util.LinkedList;

public class TreeShortestPath {

    //查找指定节点的标记
    public static boolean bLeafIsFound = false;
    public static String path1;
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }
    }


    // 最近的公共祖先
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        //查看左子树中是否有目标结点，没有为null
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        //查看右子树是否有目标节点，没有为null
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        //都不为空，说明做右子树都有目标结点，则公共祖先就是本身
        if ((left == node1 && right == node2) || (left == node2 && right == node1)) {
            return root;
        }
        return left == null ? right : left;
    }


    public static String findPath(TreeNode root, LinkedList path, TreeNode nodeToFind){
        if (root == null) {
            return null;
        }
        //将路径节点添加到栈中
        path.push(root.value);
        //如果到达了子节点
        if (!bLeafIsFound && root.value == nodeToFind.value) {
            //打印路径
            path1 = printPath(path);
            bLeafIsFound = true;
            return path1;

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
        return path1 == null ? null : path1;
    }
    public static String printPath(LinkedList path){
        int len = path.size();
        String s = ""+ path.pop();
        for (int i = 1; i < len; i++) {
            if (path.peek() != null) {
                s += "->" + path.pop();
            }
        }
        System.out.println(s);
        return s;
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

        LinkedList<TreeNode> list1 = new LinkedList<>();
        LinkedList<TreeNode> list2 = new LinkedList<>();
        TreeNode treeNode = lowestCommonAncestor(node1, node6, node7);
        String s1 = findPath(treeNode, list1, node6);
        System.out.println("最小公共祖先节点" + treeNode.value + "和节点" + node6.value + "之间的路径");
        bLeafIsFound = false;//全局变量复位
        String s2 = findPath(treeNode, list2, node7);
        System.out.println("最小公共祖先节点" + treeNode.value + "和节点" + node7.value + "之间的路径");
        bLeafIsFound = false;//全局变量复位

    }

}

