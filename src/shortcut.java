import java.util.*;
import java.io.*;

public class shortcut {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int T = Integer.parseInt(str.nextToken());
        str = new StringTokenizer(in.readLine());
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(str.nextToken());
        }
        EdgeWeightedGraph G = new EdgeWeightedGraph(N);
        for (int i = 0; i < M; i++) {
            str = new StringTokenizer(in.readLine());
            G.addEdge(new Edge(Integer.parseInt(str.nextToken()) - 1, Integer.parseInt(str.nextToken())- 1, Integer.parseInt(str.nextToken())));
        }
        Dijkstra D = new Dijkstra(G, 0, cows);
        //int answer = u.getUsed() * u.weight() - T * u.getUsed();
        D.LexoOrder(G, 5, 0, 0, 5);
        System.out.println(D.path);
        System.out.println(D.toString(4));
    }
}

class Dijkstra {
    private Edge[] edgeTo;
    private int[] distTo;
    private boolean[] marked;
    private int[] vals;
    private boolean[] marks;
    private PriorityQueue<Pair> pq;
    private boolean flag = true;
    private int e = 0;
    private int v;
    public ArrayList<Integer> path = new ArrayList<>();

    private class Pair implements Comparable<Pair> {
        private int V;
        private int weight;

        public Pair(int V, int weight) {
            this.V = V;
            this.weight = weight;
        }

        public void setV(int V) { this.V = V; }

        public int getV() { return V; }

        public void setWeight(int weight) { this.weight = weight; }

        public int getWeight() { return weight; }

        @Override
        public int compareTo(Pair that) {
            return Integer.compare(this.getWeight(), that.getWeight());
        }

        public String toString() {
            return "{" + String.valueOf(V) + ", " + String.valueOf(weight) + "}";
        }
    }

    private int delMin(PriorityQueue<Pair> pq) {
        Pair p = pq.poll();
        return p.getV();
    }

    public Dijkstra(EdgeWeightedGraph g, int s, int[] cows) {
        edgeTo = new Edge[g.V()];
        distTo = new int[g.V()];
        marked = new boolean[g.V()];
        marks = new boolean[g.V()];
        vals = cows;
        v = g.V();
        pq = new PriorityQueue<>(g.V(), new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0 ; i < g.V(); i++) distTo[i] = Integer.MAX_VALUE;
        distTo[s] = 0;

        pq.add(new Pair(s, 0));

        while(!pq.isEmpty() && flag) {
            relax(g, delMin(pq));
        }
    }

    private void relax(EdgeWeightedGraph g, int v) {
        for (Edge e : g.adjList(v)) {
            int w = e.other(v);
            if (distTo[w] > distTo[v] + e.weight() && !marked[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                pq.add(new Pair(w, distTo[w]));
            }
        }
        marked[v] = true;
    }

    public double distTo(int v)  { return distTo[v]; }

    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }

    public Iterable<Edge> pathTo(int v) {
        if (v == -1) v = this.e;
        else if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<>();
        int rn = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[rn]) {
            rn = e.other(rn);
            path.push(e);
        }
        return path;
    }

    public String toString(int v) {
        StringBuilder str = new StringBuilder();
        for (Edge e : this.pathTo(v)) {
            if (e != null) {
                str.append(e.toString());
                str.append("\n");
            }
        }
        return str.toString();
    }

    public int LexoOrder(EdgeWeightedGraph G, int V, int w, int dist, int N) {
        if (V == 0) {
            path.add(w);
            return 0;
        }
        ArrayList<Edge> ll = G.adjList(V);
        Collections.sort(ll, new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                return Integer.compare(a.other(V), b.other(V));
            }
        });

        marks[V] = true;
        for (Edge e : ll) {
            int W = e.other(V);
            if (!marks[W]) {
                dist += e.weight();
                int num = LexoOrder(G, W, V, dist, N);
                if (num == -1) {
                    return -1;
                }
                dist += num;
            }
        }
        return dist;
    }
}

class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<ArrayList<Edge>> adjList;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public int V() { return V; }

    public void addEdge(Edge e) {
        int src = e.either();
        int dest = e.other(src);

        adjList.get(src).add(e);
        adjList.get(dest).add(e);
    }

    public ArrayList<Edge> adjList(int V) { return adjList.get(V); }
}

class Edge implements Comparable<Edge> {
    private final int v; //Vertex 1
    private final int w; //Vertex 2
    private final int weight; //Weight of edge
    private int used;

    public Edge (int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int weight() { return weight; }

    public int either() { return v; }

    public int other(int vertex) {
        if (vertex == v) return w;
        if (vertex == w) return v;
        throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge that) {
        if (this.weight < that.weight) return -1;
        if (this.weight > that.weight) return 1;
        return 0;
    }

    public String toString() {
        return String.format("%d-%d %d", v, w, weight);
    }

    public void setUsed(int a) { used = a; }

    public int getUsed() { return used; }
}
