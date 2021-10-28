class Solution {
    private static final int WHITE = 0;
    private static final int GREY = 1;
    private static final int BLACK = 2;
    
    private boolean isCycle(int source, int numCourses, Map<Integer, Set<Integer>> graph, Map<Integer, Integer> color) {
        color.put(source, GREY);
        boolean cyclePresent = false;
        for (int child: graph.get(source)) {
            if (color.get(child) == GREY) {
                return true;
            }
            if (color.get(child) == WHITE) {
                cyclePresent = isCycle(child, numCourses, graph, color) | cyclePresent;
            }
        }
        color.put(source, BLACK);
        return cyclePresent;
    }
    
    // Kahn's algorithm
    // Note that Kahn's algorithm can also detect if cycle is present in the graph, so the additional cycle detection was not needed
    private int[] findTopologicalOrdering(int numCourses, Map<Integer, Set<Integer>> graph) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        
        for (int i = 0; i < numCourses; ++i) {
            inDegree.put(i, 0);
        }
        for (int i = 0; i < numCourses; ++i) {
            for (int j: graph.get(i)) {
               inDegree.put(j, inDegree.get(j) + 1);
            }
        }
        
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree.get(i) == 0) {
                bfsQueue.add(i);
            }
        }
        List<Integer> topologicalSortedNodes = new ArrayList<>();
        while (bfsQueue.size() > 0) {
            int source = bfsQueue.poll();
            topologicalSortedNodes.add(source);
            for (int child: graph.get(source)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    bfsQueue.add(child);
                }
            }
        }
        return topologicalSortedNodes.stream().mapToInt(i -> i).toArray();
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> color = new HashMap<>();
        
        for (int i = 0; i < numCourses; ++i) {
            graph.put(i, new HashSet<Integer>());
            color.put(i, WHITE);
        }
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int source = prerequisites[i][1];
            int destination = prerequisites[i][0];
            
            Set<Integer> children = graph.get(source);
            children.add(destination);
        }
        
        for (int i = 0; i < numCourses; ++i) {
            if(color.get(i) == WHITE && isCycle(i, numCourses, graph, color)) {
                // System.out.println(i + " cycle detected");
                return new int[0];
            }
        }
        return findTopologicalOrdering(numCourses, graph);
    }
}

// 2
// [[1,0]]
// 4
// [[1,0],[2,0],[3,1],[3,2]]
// 1
// []
// 3
// [[1,0],[1,2],[0,1]]
