class Solution:
    def digArtifacts(self, n: int, artifacts: List[List[int]], dig: List[List[int]]) -> int:
        cellToArtifact = {}
        artifactToNumCells = defaultdict(int)
        for idx, artifact in enumerate(artifacts):
            r1, c1, r2, c2 = artifact
            for r in range(r1, r2 + 1):
                for c in range(c1, c2 + 1):
                    cellToArtifact[(r, c)] = idx
                    artifactToNumCells[idx] += 1
        total = 0
        for d in dig:
            r, c = d
            if (r, c) in cellToArtifact:
                artifactNumber = cellToArtifact[(r, c)]
                artifactToNumCells[artifactNumber] -= 1
        for artifactNumber, val in artifactToNumCells.items():
            if val <= 0:
                total += 1
        return total
