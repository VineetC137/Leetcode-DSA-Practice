class Solution {

    public long sumAndMultiply(int n) {

        long x = 0;
        long sum = 0;

        if (n == 0) {
            return 0;
        }

        while (n > 0) {

            int digit = n % 10;

            if (digit != 0) {
                x = digit * multiplier(x) + x;
                sum += digit;
            }

            n /= 10;
        }

        return x * sum;
    }

    private long multiplier(long x) {
        if (x == 0) {
            return 1;
        }

        long p = 1;

        while (p <= x) {
            p *= 10;
        }

        return p;
    }
}