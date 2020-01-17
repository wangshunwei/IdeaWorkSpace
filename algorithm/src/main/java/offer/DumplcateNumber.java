package offer;

import java.util.HashMap;

/**
 * 查找重复的数字
 */
public class DumplcateNumber {

    public static void main(String[] args) {

        int[] arr = {1, 3, 2, 5, 6, 8, 12, 34, 3, 5,6,8};
        findNumber(arr);


    }

    /**
     * 重复数字 用hashMap解决
     * @param arr
     */
    public static void findNumber(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for (int num : arr) {
            Integer put = map.put(num, ++count);
            if (put != null && put > 1) {
                System.out.println(num);
            }
        }
    }
}
