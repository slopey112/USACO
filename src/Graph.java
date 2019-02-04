import java.util.*;
import java.util.stream.Collectors;

class Vertex {
    String identifier;

    public Vertex(String constructor) {
        identifier = constructor;
    }
}

public class Graph {
    String identifier;
    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph(String constructor) {
        adjVertices = new HashMap<>();
        identifier = constructor;
    }

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values()
                .stream()
                .map(e -> e.remove(v))
                .collect(Collectors.toList());
        adjVertices.remove(new Vertex(label));
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        try {
            adjVertices.get(v2).add(v1);
            adjVertices.get(v1).add(v2);
        } catch (NullPointerException e) {
            adjVertices.replace(v2, new ArrayList<>(Arrays.asList(v1)));
            adjVertices.replace(v1, new ArrayList<>(Arrays.asList(v2)));
        }
    }

    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public static void main(String[] args) {
        Graph client = new Graph("client");
        client.addVertex("1");
        client.addVertex("2");
        client.addVertex("3");
        client.addEdge("1", "2");
        List<Vertex> myList = client.getAdjVertices("1");
        System.out.println(myList);
    }
}