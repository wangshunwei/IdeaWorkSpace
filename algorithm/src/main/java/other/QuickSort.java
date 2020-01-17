package other;

public class QuickSort {

    public static void main(String[] args) {


    }

    public static void quickSort(int a[], int p, int r) {
        if (p < r) {
            // 中间指针
            int q = Partition(a, p, r);
            // 进行左右数据的递归
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }

    }

    public static int Partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot){
                high--;
            }
            // 从后面交换前面
            int tem = arr[low];
            arr[low] = arr[high];
            arr[high] = tem;
            while (low < high && arr[low] <= pivot)  {
                low++;
            }
            int temp = arr[high];
            arr[high] = arr[low];
            arr[low] = temp;
        }
        arr[low] = pivot; // 基准值在中间位置 最后
        // 返回中间的指针   因为两个指针从两头开始移动 每个指针的索引只能到中间值的位置
        // 返回中间值的索引，可以分别到左侧数据和右侧数据进行递归的操作
        return low;

    }

}
