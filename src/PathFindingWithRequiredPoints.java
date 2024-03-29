import java.util.ArrayList;
import java.util.List;

public class PathFindingWithRequiredPoints {

    public static void main(String[] args) {
        int m = 5, n = 4; // 목표 지점 (5, 4)
        int[][] obstacles = {{1, 1}, {3, 3}, {5, 3}}; // X 표시된 지점
        int[][] requiredPoints = {{0, 2}, {1, 3}, {2, 1}, {3, 1}, {4, 2}}; // ● 표시된 지점

        int pathCount = calculatePathsWithAtLeastTwoRequiredPoints(m, n, obstacles, requiredPoints);
        System.out.println("Paths count passing through at least two required points: " + pathCount);
    }

    private static int calculatePathsWithAtLeastTwoRequiredPoints(int m, int n, int[][] obstacles, int[][] requiredPoints) {
        // ● 지점을 2개 이상 포함하는 경로의 수 계산
        int count = 0;
        for(int i = 0; i < requiredPoints.length; i++) {
            for(int j = i + 1; j < requiredPoints.length; j++) {
                // 각 ● 지점 쌍에 대해, 해당 지점을 포함하는 경로 계산
                int pathsFromStartToFirst = calculatePaths(m, n, obstacles, new int[]{0, 0}, requiredPoints[i]);
                int pathsBetweenPoints = calculatePaths(m, n, obstacles, requiredPoints[i], requiredPoints[j]);
                int pathsFromSecondToEnd = calculatePaths(m, n, obstacles, requiredPoints[j], new int[]{m, n});
                // 경로 수 곱셈
                count += pathsFromStartToFirst * pathsBetweenPoints * pathsFromSecondToEnd;
            }
        }
        return count;
    }

    private static int calculatePaths(int m, int n, int[][] obstacles, int[] start, int[] end){
        boolean[][] grid = new boolean[m+1][n+1];
        for(int[] obs : obstacles){
            if(obs[0] <= m && obs[1] <= n)
                grid[obs[0]][obs[1]] = true;
        }

        if(grid[start[0]][start[1]] || grid[end[0]][end[1]])
            return 0;

        int[][] dp = new int[m+1][n+1];
        dp[start[0]][start[1]] = 1;

        for(int i=start[0]; i<=m; i++){
            for(int j=start[1]; j<=n; j++){
                if(i == start[0] && j == start[1])
                    continue;
                if(grid[i][j])
                    continue;

                int fromLeft = (j>0) ? dp[i][j-1] : 0;
                int fromTop = (i>0) ? dp[i-1][j] : 0;

                dp[i][j] = fromTop + fromLeft;
            }
        }

        return dp[end[0]][end[1]];
    }
}
