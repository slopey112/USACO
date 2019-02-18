import java.io.*;
import java.util.*;

public class moocast {
    static class DisjointSet {
        private int[] id;
        private int[] size;

        private DisjointSet(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        private int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        private boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        private void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (rootP == rootQ) return;
            if (size[rootP] < size[rootQ]) {
                id[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                id[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }
    static class Point {
        private int x, y, i;
        private Point(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }
    }
    static class Edge implements Comparable<Edge> {
        private Point p, q;
        private int w;
        private Edge(Point p, Point q, int w) {
            this.p = p;
            this.q = q;
            this.w = w;
        }
        public int compareTo(Edge a) { return Integer.compare(this.w, a.w); }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        int N = Integer.parseInt(in.readLine());
        Point[] pt = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            pt[i] = new Point(Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()), i);
        }
        Edge[] edges = new Edge[(int)Math.pow(N, 2) - N];
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                edges[k] = new Edge(pt[i], pt[j], ((int) (Math.pow(pt[i].x - pt[j].x, 2) + Math.pow(pt[i].y - pt[j]. y, 2))));
                k++;
            }
        }
        Arrays.sort(edges);
        DisjointSet set = new DisjointSet(N);
        k = 0;
        int sum = 0;
        for (Edge e : edges) {
            if (k >= N-1) break;
            int p = e.p.i;
            int q = e.q.i;
            if (!set.connected(p, q)) {
                set.union(p, q);
                sum = Math.max(e.w, sum);
                k++;
            }
        }
        PrintWriter out = new PrintWriter(new File("moocast.out"));
        out.print(sum);
        out.close();
    }
}
