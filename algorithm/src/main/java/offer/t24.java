package offer;

public class t24 {

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。
     * @param sequence 某二叉搜索树的后序遍历的结果
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     *
     */
    public static boolean verifySequenceOfBST(int[] sequence) {

        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int index = start;
        while (index < end - 1 && sequence[index] < sequence[end]) {
            index++;
        }
        int right = index;
        while (index < end - 1 && sequence[index] > sequence[end]) {
            index++;
        }
        if (index != end - 1) {
            return false;
        }
        index = right;
        return verifySequenceOfBST(sequence, start, index - 1) && verifySequenceOfBST(sequence, index, end - 1);
    }
}
