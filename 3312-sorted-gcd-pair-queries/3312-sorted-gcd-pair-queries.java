import java.util.*;

class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;

        for (int x : nums)
            max = Math.max(max, x);

        int[] freq = new int[max + 1];

        for (int x : nums)
            freq[x]++;

        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {

            long count = 0;

            for (int m = g; m <= max; m += g)
                count += freq[m];

            exact[g] = count * (count - 1) / 2;

            for (int m = g + g; m <= max; m += g)
                exact[g] -= exact[m];
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1;

            int l = 1;
            int r = max;

            while (l < r) {

                int mid = (l + r) / 2;

                if (prefix[mid] >= target)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}