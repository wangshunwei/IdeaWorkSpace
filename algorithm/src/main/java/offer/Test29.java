package offer;

public class Test29 {

    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     *
     * @param numbers 输入数组
     * @return 找到的数字
     */
    public static int moreThanHalfNum(int[] numbers) {

        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("array length must large than 0");
        }
        // 用于记录出现次数大于数组一半的数
        int result = numbers[0];
        // 于当前记录的数不同的数的个数
        int count = 1;      // 0 1 2 3 4 5 6
        // 从第二个数开始向后找  1, 2,2 ,2 ,2, 2,2,2, 3, 4, 5 ,6
        for (int i = 1; i < numbers.length; i++) {
            // 如果记数为0
            if (count == 0) {
                // 重新记录一个数，假设它是出现次数大于数组一半的
                result = numbers[i];
                // 记录统计值
                count = 1;
            }
            // 如果记录的值与统计值相等，记数值增加
            else if (result == numbers[i]) {
                count++;
            }
            // 如果不相同就减少，相互抵消
            else {
                count--;
            }
        }
        // 最后的result可能是出现次数大于数组一半长度的值
        // 统计result的出现次数
        count = 0;
        for (int number : numbers) {
            if (result == number) {
                count++;
            }
        }
        // 如果出现次数大于数组的一半就返回对应的值
        if (count > numbers.length / 2) {
            return result;
        }
        // 否则输入异常
        else {
            throw new IllegalArgumentException("invalid input");
        }
    }

    /**
     *
     * 用快排解决 找到中间的数字
     *
     *
     */


    public static int findk(int[] arr,int low ,int high ,int k) {
        int partition = partition(arr,low,high);
        if (partition == k) {
            return arr[partition -1];
        } else if (partition > k) {
            // 递归左侧数据
            return findk(arr,low,partition -1 ,k);
        } else {
            // 递归右侧数据
            return findk(arr,partition + 1 ,high ,k);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int key = arr[low];
        while (low < high) {
            // 右侧指针变换
            while (low < high && arr[high] >= key) {
                high--;
            }
            int tempLow = arr[low];
            arr[low] = arr[high];
            arr[high] = tempLow;
            // 左侧指针变换
            while (low < high && arr[low] <= key) {
                low++;
            }
            int temphigh = arr[high];
            arr[high] = arr[low];
            arr[low] = temphigh;
        }
        arr[low] = key;
        return low;
    }


    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1,7,7, 2,2 ,2,2, 2, 5 ,6};
        //System.out.println(moreThanHalfNum(numbers));
        int findk = findk(numbers, 0, numbers.length - 1, numbers.length / 2);
        System.out.println(findk);
    }

}
