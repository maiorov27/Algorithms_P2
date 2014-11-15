import org.junit.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DFSearchImplTest {
    public static final int VERTICES_AMOUNT = 6;
    private Graph graph;
    private DepthFirstSearch dfs;
    @Before
    public void setUp() {
        graph = new Graph(VERTICES_AMOUNT);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        dfs = new DepthFirstSearch(graph, 0);
    }

    @Test
    public void test1() {
        assertThat(dfs.isMarked(4)).isFalse();
        assertThat(dfs.isMarked(3)).isTrue();
    }


}
