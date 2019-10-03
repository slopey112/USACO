import java.io.*;
import java.util.*;

public class vacationgold {
    private static class Pair implements Comparable<Pair> {
        private int n, w;
        private Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }
        public int compareTo(Pair o) { return Integer.compare(this.w, o.w); }

        public String toString() { return "(" + n + ", " + w + ")"; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("vacationgold.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());
        int Q = Integer.parseInt(str.nextToken());
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(N);
        ArrayList<ArrayList<Pair>> adj2 = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
            adj2.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            str = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(str.nextToken())-1;
            int q = Integer.parseInt(str.nextToken())-1;
            int w = Integer.parseInt(str.nextToken());
            adj.get(p).add(new Pair(q, w));
            adj2.get(q).add(new Pair(p, w));
        }
        int[] hubs = new int[K];
        for (int i = 0; i < K; i++) hubs[i] = Integer.parseInt(in.readLine())-1;
        int[][] distTo = new int[K][N];
        for (int i = 0; i < K; i++) {
            int hub = hubs[i];
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(hub, 0));
            distTo[i][hub] = 0;
            while (!pq.isEmpty()) {
                Pair curr = pq.poll();
                if (curr.w > distTo[i][curr.n]) continue;
                for (Pair next : adj.get(curr.n)) {
                    if (distTo[i][next.n] > distTo[i][curr.n] + next.w) {
                        distTo[i][next.n] = distTo[i][curr.n] + next.w;
                        pq.add(next);
                    }
                }
            }
        }
        int[][] distTo2 = new int[K][N];
        for (int i = 0; i < K; i++) {
            int hub = hubs[i];
            Arrays.fill(distTo2[i], Integer.MAX_VALUE);
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(hub, 0));
            distTo2[i][hub] = 0;
            while (!pq.isEmpty()) {
                Pair curr = pq.poll();
                if (curr.w > distTo2[i][curr.n]) continue;
                for (Pair next : adj2.get(curr.n)) {
                    if (distTo2[i][next.n] > distTo2[i][curr.n] + next.w) {
                        distTo2[i][next.n] = distTo2[i][curr.n] + next.w;
                        pq.add(next);
                    }
                }
            }
        }
        int[] ans = new int[Q];
        for (int i = 0; i < Q; i++) {
            str = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(str.nextToken())-1;
            int q = Integer.parseInt(str.nextToken())-1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                int ptox = distTo2[j][p];
                int qtox = distTo[j][q];
                if (ptox == Integer.MAX_VALUE || qtox == Integer.MAX_VALUE) continue;
                min = Math.min(ptox + qtox, min);
            }
            ans[i] = min;
        }
        int ct = 0;
        int sum = 0;
        for (int a : ans) {
            if (a != Integer.MAX_VALUE) {
                ct++;
                sum += a;
            }
        }
        PrintWriter out = new PrintWriter(new File("vacationgold.out"));
        out.println(ct);
        out.println(sum);
        out.close();
    }
}
