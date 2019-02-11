import java.io.*;
import java.util.*;

public class visitfj {
    private static int T;
    private static int N;
    private static int[][] grid;

    static class State implements Comparable<State> {
        private int distance;
        private int lastEaten;
        private int x;
        private int y;

        private State(int distance, int lastEaten, int x, int y) {
            this.distance += distance;
            this.lastEaten = lastEaten;
            this.x = x;
            this.y = y;
        }

        public int compareTo(State a) {
            return this.distance - a.distance;
        }

        ArrayList<State> genTransitions() {
            ArrayList<State> arr = new ArrayList<>();
            if (lastEaten == 2) {
                if (y < N-1) arr.add(new State(distance + grid[y+1][x] + T, 0, x, y+1));
                if (x < N-1) arr.add(new State(distance + grid[y][x+1] + T, 0, x+1, y));
                if (y > 0) arr.add(new State(distance + grid[y-1][x] + T, 0, x, y-1));
                if (x > 0) arr.add(new State(distance + grid[y][x-1] + T, 0, x-1, y));
            } else {
                if (y < N-1) arr.add(new State(distance + T,lastEaten+1, x, y+1));
                if (x < N-1) arr.add(new State(distance +T, lastEaten+1, x+1, y));
                if (y > 0) arr.add(new State(distance + T,lastEaten+1, x, y-1));
                if (x > 0) arr.add(new State(distance + T, lastEaten+1, x-1, y));
            }
            return arr;
        }

        public String toString() {
            return "[" + distance + ", " + lastEaten + ", (" + x + ", " + y + ")]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        T = Integer.parseInt(str.nextToken());

        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        int[][][] minDist = new int[N+1][N+1][3];
        for(int[][] a : minDist) for(int[] b : a) Arrays.fill(b, Integer.MAX_VALUE);
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0,0, 0, 0));
        minDist[0][0][0]=0;
        int min = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            State a = pq.poll();
            if(a.distance>minDist[a.y][a.x][a.lastEaten])continue;
            if (a.x == N-1 && a.y == N-1) min = Math.min(min, a.distance);
            for (State n : a.genTransitions()) {
                if (minDist[n.y][n.x][n.lastEaten]>n.distance ) {
                    minDist[n.y][n.x][n.lastEaten]=n.distance;
                    pq.add(n);
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("visitfj.out"));
        out.print(min);
        out.close();
    }
}
