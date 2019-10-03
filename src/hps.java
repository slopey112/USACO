import java.util.*;
import java.io.*;

public class hps {
    private final char[] seq;
    private final int N;
    private final int FJ = 0;
    private final int TIE = 0;
    private final int BC = 1;
    private final int answer;
    private final int HOOF = 2;
    private final int PAPER = 1;
    private final int SCISSORS = 0;
    private int[][][] dp;

    public hps(FileReader f) throws IOException {
        BufferedReader in = new BufferedReader(f);
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < N; i++) s.append(in.readLine());

        dp = new int[N+1][K+1][3];
        for (int[][] a : dp) for (int[] b : a) Arrays.fill(b, -1);

        seq = s.toString().toCharArray();
        answer = max(mine(0, K, 'H'), mine(0, K, 'P'), mine(0, K, 'S'));
    }

    private int mine(int i, int k, char m) {
        if (i >= N) return 0;

        //i is index, k is how many switches left, and m is Bessie's move.
        //We only care if game results in win.
        if (dp[i][k][toInt(m)] != -1) return dp[i][k][toInt(m)];

        int ans = play(seq[i], m);
        if (k > 0) {
            switch (m) {
                //We try all other moves that is not the current move, and we either switch or don't switch.
                //If we don't switch, then neither k or m is touched. Otherwise, k - 1 is passed, and what we switch to.
                case ('H'):
                    ans += max(mine(i+1, k-1, 'S'), mine(i+1, k-1, 'P'), mine(i+1, k, m));
                    break;
                case ('S'):
                    ans += max(mine(i+1, k-1, 'H'), mine(i+1, k-1, 'P'), mine(i+1, k, m));
                    break;
                case ('P'):
                    ans += max(mine(i+1, k-1, 'S'), mine(i+1, k-1, 'H'), mine(i+1, k, m));
            }
        } else {
            //If we can no longer switch, we should see how many more games we can win with Bessie's choice.
            ans += mine(i+1, 0, m);
        }

        dp[i][k][toInt(m)] = ans;
        return ans;
    }

    private int play(char a, char b) {
        //Simulates a single hoof paper scissors game.
        if (a == 'H' && b == 'S') return FJ;
        if (a == 'S' && b == 'H') return BC;
        if (a == 'S' && b == 'P') return FJ;
        if (a == 'P' && b == 'S') return BC;
        if (a == 'P' && b == 'H') return FJ;
        if (a == 'H' && b == 'P') return BC;
        else return TIE;
    }

    private int max(int a, int b, int c) { return Math.max(Math.max(a, b), Math.max(b, c)); }

    public int getAnswer() { return answer; }

    private int toInt(char a) {
        switch (a) {
            case ('H'):
                return HOOF;
            case ('P'):
                return PAPER;
            default:
                return SCISSORS;
        }
    }

    public static void main(String[] args) throws IOException {
        hps test = new hps(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new File("hps.out"));
        out.print(test.getAnswer());
        out.close();
    }
}
