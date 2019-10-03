import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class snakes {
    private static int N, K;
    //private static int cap;
    private static int[] snakes;
    private static int[][][] dp;
    private static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("snakes.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        K = Integer.parseInt(str.nextToken());
        snakes = new int[N];
        dp = new int[N+1][N+1][K+1];
        //cap = (N < 200) ? N : N/4+N/3;
        for (int[][] a : dp) for (int[] b : a) Arrays.fill(b, -1);
        str = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) snakes[i] = Integer.parseInt(str.nextToken());

        int ans = INF;
        for (int k = 0; k < N; k++) ans = Math.min(ans, mine(0, k, K));
        PrintWriter out = new PrintWriter(new File("snakes.out"));
        out.print(ans);
        out.close();
    }

    /*private static int mine(int i, int s, int size) {
        if (i == N) return 0;
        if (size < snakes[i]) return INF;
        //if (dp[i][s][size%1000] != -1) return dp[i][s][size%1000];

        int ans = INF;
        if (s > 0) for (int g : snakes) ans = Math.min(ans, mine(i+1, s-1, g)+(size-snakes[i]));
        for (int g : snakes) ans = Math.min(ans, mine(i+1, s, size)+(size-snakes[i]));
        //dp[i][s][size%1000] = ans;
        return ans;
    }*/

    private static int mine(int i, int j, int s) {
        if (i == N) return 0;
        if (snakes[j] < snakes[i]) return INF;
        if (dp[i][j][s] != -1) return dp[i][j][s];

        // Try binary searching for k
        int ans = INF;
        if (s > 0) for (int k = j; k < N; k++) ans = Math.min(ans, mine(i+1, k, s-1)+(snakes[j]-snakes[i]));
        ans = Math.min(ans, mine(i+1, j, s)+(snakes[j]-snakes[i]));
        dp[i][j][s] = ans;
        return ans;
    }
}
