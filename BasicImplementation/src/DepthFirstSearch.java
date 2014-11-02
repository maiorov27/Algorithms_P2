
public class DepthFirstSearch {

    private boolean[] marked;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
    }


    private void dfs(Graph graph, int v) {
        for(int i = 0; i < graph.V(); i++){
            if (!isMarked(i)) {

            }
        }
    }

    private boolean isMarked(int v) {
        return marked[v];
    }

}
