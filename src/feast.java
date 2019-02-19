import java.util.*;
import java.io.*;

public class feast {
  private static int T, A, B;
  private static int[][] dp;

  private static int mine(int full, boolean water) {
    if (full > T) return 0;
    if (dp[full][((water)?1:0)]!=-1) return dp[full][((water)?1:0)];
    if (full + Math.min(A, B) > T && !water) return full;

    int ans = Integer.MIN_VALUE;
    if (water) ans = Math.max(ans, mine((int)Math.floor(full/2), false));
    ans = Math.max(ans, mine(full+A, water));
    ans = Math.max(ans, mine(full+B, water));

    dp[full][((water)?1:0)] = ans;
    return ans;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("feast.in"));
    StringTokenizer str = new StringTokenizer(in.readLine());
    T = Integer.parseInt(str.nextToken());
    A = Integer.parseInt(str.nextToken());
    B = Integer.parseInt(str.nextToken());
    dp = new int[T+1][2];
    for (int[] a : dp) Arrays.fill(a, -1);

    PrintWriter out = new PrintWriter(new File("feast.out"));
    out.print(mine(0, true));
    out.close();
  }
}
