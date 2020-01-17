package offer;

import java.util.Arrays;

/**
 * 前半个数组找偶数，后半个数组找奇数  两者交换位置
 */

public class Test14 {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
     *
     * @param arr 输入的数组
     */
    public static void reorderOddEven(int[] arr) {
        // 对于输入的数组为空，或者长度小于2的只接返回
        if (arr == null || arr.length < 2) {
            return;
        }

        // 从左向右记录偶数的位置  开始索引
        int start = 0;
        // 从右向左记录奇数的位置  结束索引
        int end = arr.length - 1;
        // 开始调整奇数和偶数的位置
        while (start < end) {
            // 找偶数
            while (start < end && arr[start] % 2 != 0) {
                start++;
            }
            // 找奇数
            while (start < end && arr[end] % 2 == 0) {
                end--;
            }
            // 找到后就将奇数和偶数交换位置
            // 对于start=end的情况，交换不会产生什么影响
            // 所以将if判断省去了
            // 交换位置
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }

    public static void main(String[] args) {

        int[] numbers = {1,5,6,2,7,11,54,22};
        reorderOddEven(numbers);
        System.out.println(Arrays.toString(numbers));
    }


}
