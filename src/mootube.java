import java.util.*;
import java.io.*;

public class mootube {
    private static class DisjointSet {
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

        private int size(int p) { return size[root(p)]; }
    }
    private static class Edge implements Comparable<Edge>{
        private int p, q, w;

        private Edge(int p, int q, int w) {
            this.p = p;
            this.q = q;
            this.w = w;
        }

        public int compareTo(Edge e) { return Integer.compare(e.w, this.w); }

        public String toString() { return "[" + p + "-" + q + " " + w + "]"; }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(str.nextToken());
        int Q = Integer.parseInt(str.nextToken());
        Edge[] edges = new Edge[N-1];
        DisjointSet set = new DisjointSet(N);
        for (int i = 0; i < N-1; i++) {
            str = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(str.nextToken())-1;
            int q = Integer.parseInt(str.nextToken())-1;
            int w = Integer.parseInt(str.nextToken());
            edges[i] = new Edge(p, q, w);
        }
        Arrays.sort(edges);
        int[][] queries = new int[Q][3];
        for (int i = 0; i < Q; i++) {
            str = new StringTokenizer(in.readLine());
            queries[i][0] = Integer.parseInt(str.nextToken());
            queries[i][1] = Integer.parseInt(str.nextToken())-1;
            queries[i][2] = i;
        }
        Arrays.sort(queries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        int[] answers = new int[Q];
        int j = 0;
        for (int i = 0; i < Q; i++) {
            int w = queries[i][0];
            while (j < N-1 && edges[j].w >= w) {
                set.union(edges[j].p, edges[j].q);
                j++;
            }

            answers[queries[i][2]] = set.size(queries[i][1])-1;
        }
        PrintWriter out = new PrintWriter(new File("mootube.out"));
        for (int a : answers) out.println(a);
        out.close();
    }
}
