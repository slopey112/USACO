import java.util.*;
import java.io.*;

public class radio {
    private final char[] fjseq;
    private final char[] bcseq;
    private int[][] dp;
    private final int N, M;
    private final int ans;

    private class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(char a) {
            switch (a) {
                case ('N'):
                    y++;
                    break;
                case ('W'):
                    x--;
                    break;
                case ('S'):
                    y--;
                    break;
                case ('E'):
                    x++;
            }
        }

        public void unmove(char a) {
            switch (a) {
                case ('N'):
                    y--;
                    break;
                case ('W'):
                    x++;
                    break;
                case ('S'):
                    y++;
                    break;
                case ('E'):
                    x--;
            }
        }

        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    public radio(FileReader a) throws IOException {
        BufferedReader in = new BufferedReader(a);
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        str = new StringTokenizer(in.readLine());
        Point fj = new Point(Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
        str = new StringTokenizer(in.readLine());
        Point bc = new Point(Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
        fjseq = in.readLine().toCharArray();
        bcseq = in.readLine().toCharArray();
        dp = new int[N+1][M+1];
        for (int[] z : dp) Arrays.fill(z, -1);
        ans = mine(-1, -1, fj, bc) - dist(fj, bc);
    }

    private int mine(int i, int j, Point fj, Point bc) {
        if (i == N - 1 && j == M - 1) return dist(fj, bc);
        if (dp[i+1][j+1] != -1) return dp[i+1][j+1];
        int tmp1 = Integer.MAX_VALUE, tmp2 = Integer.MAX_VALUE, tmp3 = Integer.MAX_VALUE;
        int ans = dist(fj, bc);

        if (i + 1 == N) {
            //If Farmer John is already there, then only Bessie moves
            bc.move(bcseq[j+1]);
            tmp2 = mine(i, j + 1, fj, bc);
            bc.unmove(bcseq[j+1]);
        } else if (j + 1 == M) {
            //If Bessie is already there, then only Farmer John moves
            fj.move(fjseq[i+1]);
            tmp1 = mine(i + 1, j, fj, bc);
            fj.unmove(fjseq[i+1]);
        } else {
            //Only FJ moves
            fj.move(fjseq[i+1]);
            tmp1 = mine(i + 1, j, fj, bc);
            fj.unmove(fjseq[i+1]);

            //Only Bessie moves
            bc.move(bcseq[j+1]);
            tmp2 = mine(i, j + 1, fj, bc);
            bc.unmove(bcseq[j+1]);

            //Both move
            fj.move(fjseq[i+1]);
            bc.move(bcseq[j+1]);
            tmp3 = mine(i + 1, j + 1, fj, bc);
            fj.unmove(fjseq[i+1]);
            bc.unmove(bcseq[j+1]);
        }
        ans += min(tmp1, tmp2, tmp3);
        dp[i+1][j+1] = ans;
        return ans;
    }

    private int dist(Point a, Point b) { return (int) (Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)); }

    private int min(int a, int b, int c) { return Math.min(Math.min(a, b), Math.min(b, c)); }

    public int getAns() { return ans; }

    public static void main(String[] args) throws IOException {
        radio test = new radio(new FileReader("radio.in"));
        FileWriter out = new FileWriter(new File("radio.out"));
        out.write("" + test.getAns());
        out.close();
    }
}
