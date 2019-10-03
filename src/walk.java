import java.io.*;
import java.util.*;

public class walk {
    private static final long P1 = 2019201913, P2 = 2019201949, MOD = 2019201997;

    private static class Edge {
        private int a, b;
        private long val;
        private Edge(int a, int b, long val) {
            this.a = a;
            this.b = b;
            this.val = val;
        }
        private long getVal() { return val; }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("walk.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());
        ArrayList<Edge> edges = new ArrayList<>((N*(N-1))/2);
        int ct = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                edges.add(new Edge(i, j, (P1*i+P2*j)%MOD));
            }
        }
        Collections.sort(edges, Comparator.comparingLong(Edge::getVal));
        HashSet<Integer> set = new HashSet<>();
        long val = 0;
        int i = 0;
        while (set.size() <= N-K+1) {
            Edge e = edges.get(i);
            set.add(e.a);
            set.add(e.b);
            val = e.val;
            i++;
//            System.out.println(e.a + " --- " + e.b + " " + val + " " + set.size());
        }
        PrintWriter out = new PrintWriter(new File("walk.out"));
        out.print(val);
        out.close();
    }
}
