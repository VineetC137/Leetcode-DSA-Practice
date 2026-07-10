import java.util.*;

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] sorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int[] comp = new int[n];
        int compId = 0;

        for (int i = 1; i < n; i++) {
            if (sorted[i] - sorted[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }

        int[] next = new int[n];
        int r = 0;

        for (int l = 0; l < n; l++) {
            while (r + 1 < n && sorted[r + 1] - sorted[l] <= maxDiff) {
                r++;
            }
            next[l] = r;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            if (comp[u] != comp[v]) {
                ans[i] = -1;
                continue;
            }

            int cur = u;
            int dist = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < v) {
                    cur = up[k][cur];
                    dist += 1 << k;
                }
            }

            ans[i] = dist + 1;
        }

        return ans;
    }
}