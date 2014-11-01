public class Graph {

    private Bag<Integer>[] vertexArray;
    private final int vertices;
    private int edges;

    public Graph(int vertices) {
        this.vertices = vertices;

        vertexArray = (Bag<Integer>[])new Bag[vertices];
        for (int i = 0; i < vertices; i++){
            vertexArray[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this.vertices = in.readInt();
        int edges = in.readInt();

        for(int i = 0; i < edges; i++ ) {

            int v = in.readInt();
            int w = in.readInt();

            addEdge(v, w);
        }

    }

    public void addEdge(int v, int w) {
        vertexArray[v].add(w);
        vertexArray[w].add(v);
        edges++;
    }

    public int V() {return vertices;}

    public int E() {return edges;}

    public Iterable adj(int v) {
        return vertexArray[v];
    }

}
