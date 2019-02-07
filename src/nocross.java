import java.io.*;
import java.util.Arrays;

//The key insight to this problem is that if I just simply moved up two pointers representing the endpoints of
//the crosswalk, as long as I don't decrement the counters, the lines will never intersect. After that insight,
//a DP solution is very easy.

public class nocross {
    private final int N;
    private final int[] A;
    private final int[] B;
    private final int answer;
    private int[][] dp;

    public nocross(int N, int[] A, int[] B) {
        this.N = N;
        this.A = A;
        this.B = B;
        dp = new int[N+1][N+1];
        for (int[] a : dp) Arrays.fill(a, -1);
        answer = mine(0, 0);
    }

    private int getAnswer() { return answer; }

    private int mine(int i, int j) {
        if (i >= N || j >= N) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int ans;
        int tmp1 = Integer.MIN_VALUE, tmp2, tmp3;

        //Either we build or don't build. If we don't build, we have the choice of incrementing either i or j.
        //If we do build, both i and j are incremented and 1 is added to the answer (because we built).
        //Here, tmp1 represents what happens if we choose to build.

        if (build(i, j)) tmp1 = mine(i+1, j+1) + 1;
        tmp2 = mine(i+1, j);
        tmp3 = mine(i, j+1);
        ans = max(tmp1, tmp2, tmp3);

        dp[i][j] = ans;
        return ans;
    }

    private boolean build(int a, int b) { return Math.abs(A[a] - B[b]) <= 4; }

    private int max(int a, int b, int c) { return Math.max(Math.max(a, b), Math.max(b, c)); }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
        int N = Integer.parseInt(in.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) B[i] = Integer.parseInt(in.readLine());

        nocross test = new nocross(N, A, B);
        PrintWriter out = new PrintWriter(new File("nocross.out"));
        out.print(test.getAnswer());
        out.close();
    }
}