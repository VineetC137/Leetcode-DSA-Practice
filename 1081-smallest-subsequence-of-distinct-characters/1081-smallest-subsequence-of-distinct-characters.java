class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] vis = new boolean[26];

        for (int i = 0; i < s.length(); i++)
            last[s.charAt(i) - 'a'] = i;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (vis[c - 'a'])
                continue;

            while (!st.isEmpty() && st.peek() > c &&
                    last[st.peek() - 'a'] > i) {
                vis[st.pop() - 'a'] = false;
            }

            st.push(c);
            vis[c - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();
        for (char c : st)
            ans.append(c);

        return ans.toString();
    }
}