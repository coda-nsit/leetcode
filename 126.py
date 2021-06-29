from collections import defaultdict
from queue import Queue
from copy import copy

class Solution:
    def createGraph(self, wordList):
        graph = defaultdict(list)
        for word in wordList:
            for idx in range(len(word)):
                graph[word].append(word[ : idx] + '*' + word[idx + 1 : ])
                graph[word[ : idx] + '*' + word[idx + 1 : ]].append(word)
        return graph
        
    
    # instead of putting just the current node in the queue put the entire path in the queue
    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        wordList.append(beginWord)
        
        graph = self.createGraph(wordList)
        
        # populate the distance of each node from the source node
        paths = []
        bfs_q = Queue()
        dist = defaultdict(int)
        bfs_q.put([beginWord])
        dist[beginWord] = 0
        while bfs_q.empty() is False:
            path = bfs_q.get()
            source = path[-1]
            if source == endWord:
                paths.append(copy(path))
            for neighbor in graph[source]:
                if '*' in neighbor and (neighbor not in dist or dist[neighbor] >= dist[source]):
                    dist[neighbor] = dist[source]
                    bfs_q.put(path + [neighbor])
                elif neighbor not in dist or dist[neighbor] >= dist[source] + 1:
                    dist[neighbor] = dist[source] + 1
                    bfs_q.put(path + [neighbor])
        
        used_paths = set()
        clean_paths = []
        for path in paths:
            clean_path = []
            for node in path:
                if '*' not in node:
                    clean_path.append(node)
            # since while putting the nodes in the queue we do a >= there will be duplicate paths, remove them
            string_path = ''.join(clean_path)
            if string_path not in used_paths:
                used_paths.add(string_path)
                clean_paths.append(copy(clean_path))
        return clean_paths
