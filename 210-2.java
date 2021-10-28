class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < numCourses; ++i) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int source = prerequisites[i][1];
            int destination = prerequisites[i][0];
            
            List<Integer> children = graph.get(source);
            children.add(destination);
            
            inDegree.put(destination, inDegree.get(destination) + 1);
        }
        
        // find nodes with no dependencies
        Queue<Integer> bfsQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree.get(i) == 0) {
                bfsQueue.add(i);
            }
        }
        
        List<Integer> solution = new ArrayList<>();
        while (bfsQueue.size() > 0) {
            int source = bfsQueue.poll();
            solution.add(source);
            for (int child: graph.get(source)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0 ) {
                    bfsQueue.add(child);
                }
            }
        }
        if (solution.size() != numCourses) {
            return new int[0];
        }
        return solution.stream().mapToInt(i -> i).toArray();
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
