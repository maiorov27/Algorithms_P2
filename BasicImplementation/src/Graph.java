public class Graph {

    private Bag<Integer>[] vertexArray;
    private final int V;
    private int E;

    public Graph(int V) {
        this.V = V;

        vertexArray = (Bag<Integer>[])new Bag[V];
        for (int i = 0; i < V; i++){
            vertexArray[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this.V = in.readInt();
        this.E = in.readInt();

        for(int i = 0; i < 2*E; i++ ) {

        }

    }


}
