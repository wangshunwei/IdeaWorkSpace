package other;

public class BibnaryTree {

    private Node root;

    public void add(int data) {

        if (root == null) {
            root = new Node(data);
        } else {
            root.addNode(data);
        }
    }
    public void print() {
        root.printNode();
    }

    /**
     * 内部类
     */
    private class Node {
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }

        // 小于根节点放在左边，大于放在右边
        public void addNode(int data) {

            // 放右边
            if (this.data < data) {
                if (this.right == null) {
                    right = new Node(data);
                } else {
                    // 进行递归 找到
                    right.addNode(data);
                }
            } else {
                if (this.left == null) {
                    left = new Node(data);
                } else {
                    // 进行递归 找到
                    left.addNode(data);
                }
            }
        }

        // 中序遍历  左  中  右
        public void printNode() {
            if (this.left != null) {
                // 递归
                this.left.printNode();
            }
            System.out.println(this.data + "->");
            if (this.right != null) {
                this.printNode();
            }
        }

        // 前序遍历 根 左 右
        public void prePrintNode() {
            System.out.println(this.data + "->");
            if (this.left != null) {
                // 递归
                this.left.printNode();
            }
            if (this.right != null) {
                this.printNode();
            }
        }

        // 后序遍历 左 右 根
        public void suffixPrintNode() {
            if (this.left != null) {
                // 递归
                this.left.printNode();
            }
            if (this.right != null) {
                this.printNode();
            }
            System.out.println(this.data + "->");
        }
    }
}
