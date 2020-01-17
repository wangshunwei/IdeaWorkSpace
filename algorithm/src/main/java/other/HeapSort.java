package other;

public class HeapSort {

    public static void main(String[] args) {

    }

    public static void sort(int[] array) {
        // 1 先建堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        // 对堆进行排序
        for (int j = array.length - 1; j > 0; j--) {
            // 2
            swap(array, 0, j);
            // 3
            adjustHeap(array, 0, j);
        }
    }
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            // 如果发现子节点更大，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 下面就是非常关键的一步了
                // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
                // 所以，循环对子节点所在的树继续进行判断
                i = k;
                // 如果不用交换，那么，就直接终止循环了
            } else {
                break;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
