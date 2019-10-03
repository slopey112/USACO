import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class cowrun {
    private static ArrayList<Integer> left;
    private static ArrayList<Integer> right;
    private static int L, R;
    private static int[][][] dp;
    private static final boolean LEFT = false, RIGHT = true;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowrun.in"));
        int N = Integer.parseInt(in.readLine());
        left = new ArrayList<>();
        right = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(in.readLine());
            if (tmp < 0) left.add(tmp);
            else right.add(tmp);
        }
        Collections.sort(left, Collections.reverseOrder());
        Collections.sort(right);
        L = left.size();
        R = right.size();
        dp = new int[L+1][R+1][2];
        for (int[][] a : dp) for (int[] b : a) Arrays.fill(b, -1);

        PrintWriter out = new PrintWriter(new File("cowrun.out"));
        out.print(mine(0, 0, LEFT, 0));
        out.close();
    }

    private static int mine(int l, int r, boolean side, int pos) {
        if (dp[l][r][b(side)] != -1) return dp[l][r][b(side)];
        if (l == L && r == R) return 0;

        int ans, tmp1 = Integer.MAX_VALUE, tmp2 = Integer.MAX_VALUE;
        if (l <= L-1) tmp1 = mine(l+1, r, LEFT, left.get(l)) + dist(pos, left.get(l)) * ((L - l) + (R - r));
        if (r <= R-1) tmp2 = mine(l, r+1, RIGHT, right.get(r)) + dist(pos, right.get(r)) * ((L - l) + (R - r));
        ans = Math.min(tmp1, tmp2);

        dp[l][r][b(side)] = ans;
        return ans;
    }

    private static int dist(int a, int b) { return Math.abs(a - b); }

    private static int b(boolean a) { return a?1:0; }
}
