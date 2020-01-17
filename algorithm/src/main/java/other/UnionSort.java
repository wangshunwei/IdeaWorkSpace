package other;

public class UnionSort {

    public static void main(String[] args) {

    }


    public static void sort(int[] newArray, int[] array, int low, int high) {
        // 按照拆分的逻辑，现在先把整个数组拆成两部分，mid作为数组的中间值，作为划分标准
        int mid = (low + high) / 2;
        if (low < high) {
            // 如果还可以继续拆分的话，递归调用本逻辑，先对左边的数据进行排序
            sort(newArray, array, low, mid);
            // 递归调用右侧的排序逻辑
            sort(newArray, array, mid + 1, high);
            // 左右归并
            mergeSort(low, high, mid, newArray, array);
        }
    }

    public static void mergeSort(int begin, int end, int mid, int[] tempArray,
                                 int[] array) {
        int index = begin;
        int i = begin;
        int j = mid + 1;
        // 如果两个数列都没有遍历到末尾，谁小谁就先进入temp中
        // 注意，同时temp的索引也要移动
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tempArray[index++] = array[i++];
            } else {
                tempArray[index++] = array[j++];
            }
        }
        while (i <= mid) {
            tempArray[index++] = array[i++];
        }
        while (j <= end) {
            tempArray[index++] = array[j++];
        }
        for (int k = begin; k <= end; k++) {
            array[k] = tempArray[k];
        }
    }

}
