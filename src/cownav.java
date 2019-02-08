import java.io.*;
import java.util.LinkedList;

public class cownav {
    private static final int UP = 3;
    private static final int RIGHT = 2;
    private static final int DOWN = 1;
    private static final int LEFT = 0;
    private static int N;
    private static int[][] grid;c

    static class State {
        private int x1, y1, d1, x2, y2, d2;

        public State(int x1, int y1, int d1, int x2, int y2, int d2) {
            this.x1 = x1;
            this.y1 = y1;
            this.d1 = d1;
            this.x2 = x2;
            this.y2 = y2;
            this.d2 = d2;
        }

        private State turnRight() {
            int newd1 = (d1 == LEFT) ? UP : d1 - 1;
            int newd2 = (d2 == LEFT) ? UP : d2 - 1;
            return new State(x1, y1, newd1, x2, y2, newd2);
        }

        private State turnLeft() {
            int newd1 = (d1 == UP) ? LEFT : d1 + 1;
            int newd2 = (d2 == UP) ? LEFT : d2 + 1;
            return new State(x1, y1, newd1, x2, y2, newd2);
        }

        private State forward() {
            int nx1 = x1, ny1 = y1, nx2 = x2, ny2 = y2;
            if (!(nx1 == N-1 && ny1 == 0)) {
                if (d1 == UP && ny1 > 0 && grid[y1-1][x1] != 0) ny1--;
                else if (d1 == RIGHT && nx1 < N-1 && grid[y1][x1+1] != 0) nx1++;
                else if (d1 == DOWN && ny1 < N-1 && grid[y1+1][x1] != 0) ny1++;
                else if (d1 == LEFT && nx1 > 0 && grid[y1][x1-1] != 0) nx1--;
            }
            if (!(nx2 == N-1 && ny2 == 0)) {
                if (d2 == UP && ny2 > 0 && grid[y2-1][x2] != 0) ny2--;
                else if (d2 == RIGHT && nx2 < N-1 && grid[y2][x2+1] != 0) nx2++;
                else if (d2 == DOWN && ny2 < N-1 && grid[y2+1][x2] != 0) ny2++;
                else if (d2 == LEFT && nx2 > 0 && grid[y2][x2-1] != 0) nx2--;
            }
            return new State(nx1, ny1, d1, nx2, ny2, d2);
        }

        private State[] nextStates() { return new State[] {turnRight(), turnLeft(), forward()}; }

        public String toString() { return "[" + x1 + ", " + y1 + ", " + direc(d1) + ", " + x2 + ", " + y2 + ", " + direc(d2) + "]"; }

        private String direc(int a) {
            switch(a) {
                case(UP):
                    return "UP";
                case(RIGHT):
                    return "RIGHT";
                case(DOWN):
                    return "DOWN";
                default:
                    return "LEFT";
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
        N = Integer.parseInt(in.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] line = in.readLine().toCharArray();
            for (int j = 0; j < N; j++) grid[i][j] = (line[j] == 'H') ? 0 : 1;
        }

        int[][][][][][] dp = new int[N+1][N+1][4][N+1][N+1][4];
        dp[N-1][0][UP][N-1][0][RIGHT] = 1;
        LinkedList<State> queue = new LinkedList<>();
        queue.add(new State(0, N-1, UP, 0, N-1, RIGHT));
        PrintWriter out = new PrintWriter(new File("cownav.out"));
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.x1 == N-1 && curr.y1 == 0 && curr.x2 == N-1 && curr.y2 == 0) {
                out.print((dp[curr.y1][curr.x1][curr.d1][curr.y2][curr.x1][curr.d2] - 1));
                break;
            }
            for (State next : curr.nextStates()) {
                if (dp[next.y1][next.x1][next.d1][next.y2][next.x2][next.d2] == 0) {
                    dp[next.y1][next.x1][next.d1][next.y2][next.x2][next.d2] = dp[curr.y1][curr.x1][curr.d1][curr.y2][curr.x2][curr.d2] + 1;
                    queue.add(next);
                }
            }
        }
        out.close();
    }
}
