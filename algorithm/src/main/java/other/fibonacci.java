package other;

/**
 *
 * 斐波那契数列
 */
public class fibonacci {
    public long fibonacci(int n) {
        long result = 0;
        long preOne = 1;
        long preTwo = 0;

        if (n == 0) {
            return preTwo;
        }
        if (n == 1) {
            return preOne;
        }
        for (int i = 2; i <= n; i++) {
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }
}
