
public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }


    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;

        for(Integer w: graph.adj(v)) {
            if (!isMarked(w)) {
                dfs(graph,w);
            }
        }
    }

    public boolean isMarked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

}
