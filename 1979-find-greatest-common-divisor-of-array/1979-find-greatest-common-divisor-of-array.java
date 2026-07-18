class Solution {

    public int findGCD(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}