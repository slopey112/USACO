import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class dream {
    private static final int UP = 3;
    private static final int RIGHT = 2;
    private static final int DOWN = 1;
    private static final int LEFT = 0;
    private static final int RED = 0;
    private static final int PINK = 1;
    private static final int ORANGE = 2;
    private static final int BLUE = 3;
    private static final int PURPLE = 4;
    private static int N;
    private static int M;
    private static int[][] grid;

    private static class State {
        private final boolean smelly;
        private int dir = -1;
        private final int x;
        private final int y;

        private State(int x, int y, boolean smelly, int lastMove) {
            this.x = x;
            this.y = y;
            this.smelly = getCol(x, y) == ORANGE || smelly;
            if (getCol(x, y) == PURPLE) this.dir = lastMove;
        }

        private State(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.smelly = getCol(x, y) == ORANGE;
            this.dir = dir;
            if (getCol(x, y) != PURPLE) this.dir = -1;
        }

        private ArrayList<State> genTrans() {
            ArrayList<State> states = new ArrayList<>();
            boolean flag = false;
            if (dir != -1) {
                switch (dir) {
                    case (UP):
                        if (canGo(x, y+1)) states.add(new State(x, y+1, UP));
                        else flag = true;
                        break;
                    case(RIGHT):
                        if (canGo(x+1, y)) states.add(new State(x+1, y, RIGHT));
                        else flag = true;
                        break;
                    case(DOWN):
                        if (canGo(x, y-1)) states.add(new State(x, y-1, DOWN));
                        else flag = true;
                        break;
                    case (LEFT):
                        if (canGo(x-1, y)) states.add(new State(x-1, y, LEFT));
                        else flag = true;
                }
            }
            if (dir == -1 || flag) {
                if (canGo(x, y+1)) states.add(new State(x, y+1, smelly, UP));
                if (canGo(x+1, y)) states.add(new State(x+1, y, smelly, RIGHT));
                if (canGo(x, y-1)) states.add(new State(x, y-1, smelly, DOWN));
                if (canGo(x-1, y)) states.add(new State(x-1, y, smelly, LEFT));
            }
            return states;
        }

        private boolean canGo(int x, int y) {
            if (x < 0 || y < 0 || x >= M || y >= N) return false;
            if (getCol(x, y) == PINK || getCol(x, y) == ORANGE || getCol(x, y) == PURPLE) return true;
            if (getCol(x, y) == RED) return false;
            return smelly;
        }
    }

    private static int getCol(int x, int y) { return grid[N-y-1][x]; }

    private static void printMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dream.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        int[][][][] dp = new int[M+1][N+1][2][5];
        dp[0][N-1][0][0] = 1;
        LinkedList<State> q = new LinkedList<>();
        q.add(new State(0, N-1, false, UP));
        int moves = -1;
        while (!q.isEmpty()) {
            State curr = q.poll();
            if (curr.x == M-1 && curr.y == 0) {
                moves = dp[curr.x][curr.y][curr.smelly?1:0][curr.dir+1]-1;
                break;
            }
            for (State next : curr.genTrans()) {
                if (dp[next.x][next.y][next.smelly?1:0][next.dir+1] == 0) {
                    dp[next.x][next.y][next.smelly?1:0][next.dir+1] = dp[curr.x][curr.y][curr.smelly?1:0][curr.dir+1]+1;
                    q.add(next);
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("dream.out"));
        out.print(moves);
        out.close();
    }
}
