package other;

public class SearchNumber {

    public static void main(String[] args) {

        int[] arr = {1, 4, 2, 7, 12, 78};
        int findk = findk(arr, 0, 5, 4);
        System.out.println(findk);

    }

    public static int findk(int[] arr,int low ,int high ,int k) {
        int partition = partition(arr,low,high);
        if (partition ==k) {
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
            while (low < high && arr[high] > key) {
                high--;
            }
            arr[low] = arr[high];
            // 左侧指针变换
            while (low < high && arr[low] < key) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        return low;
    }
}

