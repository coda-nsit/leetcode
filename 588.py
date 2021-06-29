class Node:
    def __init__(self, name):
        self.name = name
        self.children = {}

class DirectoryNode(Node):
    def __init__(self, name):
        super().__init__(name)
        

class FileNode(Node):
    def __init__(self, name, content):
        super().__init__(name)
        self.content = content
        
class FileSystem:
    def __init__(self):
        self.root = DirectoryNode('/')
        
    def filterPath(self, path):
        idx = 0
        # to handle the case path = '/'
        while idx < len(path) and path[idx] == '':
            idx += 1
        return path[idx:]
        
    def ls(self, path: str) -> List[str]:
        root = self.root
        if path == '/':
            return sorted([name for name in root.children.keys()])
        path = self.filterPath(path.split('/'))
        for point in path:
            if point not in root.children:
                return []
            else:
                root = root.children[point]
        # if the path is a file that also needs to be printed: https://stackoverflow.com/a/511059/2525417
        if type(root).__name__ == 'FileNode':
            return sorted([name for name in root.children.keys()] + [root.name])
        else:
            return sorted([name for name in root.children.keys()])

    def mkdir(self, path: str) -> None:
        path = self.filterPath(path.split('/'))
        root = self.root
        idx = 0
        while idx < len(path):
            point = path[idx]
            if point in root.children:
                root = root.children[point]
                idx += 1
            else: 
                break
        while idx < len(path):
            point  = path[idx]
            root.children[point] = DirectoryNode(point)
            root = root.children[point]
            idx += 1
        return
        

    def addContentToFile(self, filePath: str, content: str) -> None:
        filePath = self.filterPath(filePath.split('/'))
        root = self.root
        idx = 0
        while idx < len(filePath) - 1:
            point = filePath[idx]
            if point in root.children:
                root = root.children[point]
                idx += 1
            else:
                break
        while idx < len(filePath) - 1:
            point = filePath[idx]
            root.children[point] = DirectoryNode(point)
            root = root.children[point]
            idx += 1
        point = filePath[-1]
        if point in root.children:
            root.children[point].content += content
        else:
            root.children[point] = FileNode(point, content)
        return
        
        
    def readContentFromFile(self, filePath: str) -> str:
        filePath = self.filterPath(filePath.split('/'))
        root = self.root
        for point in filePath:
            # print(root.name, root.children.keys())
            root = root.children[point]
        return root.content


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.ls(path)
# obj.mkdir(path)
# obj.addContentToFile(filePath,content)
# param_4 = obj.readContentFromFile(filePath)
