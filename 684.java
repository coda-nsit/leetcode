// O(n^2) solution based on cycle detection in an undirected graph
class Solution {
    private final static int WHITE = 0;
    private final static int GREY = 1;
    private final static int BLACK = 2;
    
    private int[] colorGraph(int source, int parent, Map<Integer, List<Integer>> graph, Map<Integer, Integer> color) {
        color.put(source, GREY);
        int[] cycleEdge;
        for (int child: graph.get(source)) {
            if (color.get(child) == GREY && child != parent) {
                return new int[] {source, child};
            }
            
            if (color.get(child) == WHITE) {
                cycleEdge = colorGraph(child, source, graph, color);
                if (cycleEdge.length > 0) {
                    return cycleEdge;
                }
            }
        }
        color.put(source, BLACK);
        return new int[0];
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> color = new HashMap<>();
        int n  = edges.length;
        
        for (int i = 1; i <= n; ++i) {
            graph.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 1; i <= n; ++i) {
            int n1 = edges[i - 1][0];
            int n2 = edges[i - 1][1];
            List<Integer> childrenN1 = graph.get(n1);
            childrenN1.add(n2);
            List<Integer> childrenN2 = graph.get(n2);
            childrenN2.add(n1);
            
            for(int j = 1; j <= n; ++j) {
                color.put(j, WHITE);
            } 
            
            int[] edge = colorGraph(n1, n2, graph, color);
            if (edge.length > 0) {
                return new int[]{n1, n2};
            }
        }
        
        return new int[0];
    }
}

// [[1,2],[1,3],[2,3]]
// [[1,2],[2,3],[3,4],[1,4],[1,5]]
