import java.util.Iterator;

public class GraphHelper {

    public static int degree(Graph graph, int vertice) {
        int degree = 0;
        Iterator iterator = graph.adj(vertice).iterator();
        while(iterator.hasNext()) degree++;
        return degree;
    }

    public static int maxDegere(Graph graph) {
        int max = 0;
        for(int i = 0; i < graph.V(); i++) {

            if (degree(graph, i) > max) {
                max= degree(graph, i);
            }
        }

        return max;
    }

}

