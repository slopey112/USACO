import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class rblock {
    private static int N, M;
    private static ArrayList<ArrayList<Edge>> adj;

    private static class Edge implements Comparable<Edge> {
        int q, w;
        private Edge(int q, int w) {
            this.q = q;
            this.w = w;
        }

        public int compareTo(Edge o) { return Integer.compare(this.w, o.w); }

        public String toString() { return "(" + q + ", " + w + ")"; }
    }

    private static int Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        int[] distTo = new int[N];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[0] = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (curr.w > distTo[curr.q]) continue;
            for (Edge next : adj.get(curr.q)) {
                if (distTo[next.q] > distTo[curr.q] + next.w) {
                    distTo[next.q] = distTo[curr.q] + next.w;
                    pq.add(next);
                }
            }
        }
        return distTo[N-1];
    }

    private static void doubleWeight (int p, int q) { for (Edge e : adj.get(p)) if (e.q == q) e.w *= 2; }

    private static void unDouble (int p, int q) { for (Edge e : adj.get(p)) if (e.q == q) e.w /= 2; }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            str = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(str.nextToken())-1;
            int q = Integer.parseInt(str.nextToken())-1;
            int w = Integer.parseInt(str.nextToken());
            adj.get(p).add(new Edge(q, w));
            adj.get(q).add(new Edge(p, w));
        }
        int[] edgeTo = new int[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        int[] distTo = new int[N];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[0] = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (curr.w > distTo[curr.q]) continue;
            for (Edge next : adj.get(curr.q)) {
                if (distTo[next.q] > distTo[curr.q] + next.w) {
                    distTo[next.q] = distTo[curr.q] + next.w;
                    edgeTo[next.q] = curr.q;
                    pq.add(next);
                }
            }
        }
        int dist = distTo[N-1];
        int ans = Integer.MIN_VALUE, p = N-1;
        for (int e = edgeTo[N-1]; p != 0; e = edgeTo[e]) {
            doubleWeight(e, p);
            int newDist = Dijkstra();
            unDouble(e, p);
            ans = Math.max(ans, newDist - dist);
            p = e;
        }
        PrintWriter out = new PrintWriter(new File("rblock.out"));
        out.print(ans);
        out.close();
    }
}
