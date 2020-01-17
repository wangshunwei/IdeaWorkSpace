package other;

import java.util.Arrays;

/**
 *
 * 重建二叉树 已知前序遍历和中序遍历
 *
 * 前序: 根  左  右
 * 中序: 左  根  右
 * 后序: 左  右  根
 */
public class ReConstructBinaryTree {

        public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

            if (pre == null || in == null) {
                return null;
            }
            if (pre.length == 0 || in.length == 0) {
                return null;
            }
            if (pre.length != in.length) {
                return null;
            }
            // 前序遍历的数组中拿到根节点
            TreeNode root = new TreeNode(pre[0]);
            for (int i = 0; i < pre.length;i++) {
                // 遍历前序的数组跟中序的数组中的数值一样是根节点
                if (pre[0] == in[i]) {
                    root.left = reConstructBinaryTree(
                            // 包左不包右
                            Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                    root.right = reConstructBinaryTree(
                            Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
                }
            }
            return root;
        }
}
