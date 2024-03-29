import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathFinding {
    // 상수 설정
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}}; // 우측, 위 방향
    private static final int BLOCKED = -1; // X 표시된 지점
    private static final int REQUIRED = 1; // ● 표시된 지점

    public static void main(String[] args) {
        int m = 5, n = 4; // 목표 지점 (5, 4)

        int[][] grid = new int[m + 1][n + 1]; // 좌표계 초기화

        // X 표시된 지점 설정
        grid[1][1] = BLOCKED;
        grid[3][3] = BLOCKED;
        grid[5][3] = BLOCKED;

        int[][] obstacles = {{1,1}, {3,3}, {5,3}};
        // ● 표시된 지점 설정
        int[][] requiredPoints = {{0, 2}, {1, 3}, {2, 1}, {3, 1}, {4, 2}};
        int k = 2;


        // 경로 계산
        int pathCount = calculatePathsWithRequiredPoints(grid, m, n, requiredPoints, k);
        System.out.println("Required paths count: " + pathCount);
    }

    private static int calculatePathsWithRequiredPoints(int[][] grid, int m, int n, int[][] requiredPoints, int k) {
        // ● 지점을 포함하는 경로 수 계산
        // 여기서는 단순화를 위해 모든 ● 지점을 반드시 거치는 경로 수는 계산하지 않고,
        // ● 지점이 하나라도 포함된 경로 수만 계산합니다.
        // 실제 문제의 요구에 따라 이 부분을 수정할 수 있습니다.
        int totalPaths = 0;

        for(int i=k; i<=requiredPoints.length; i++){
            List<List<int[]>> combinations = new ArrayList<>();
            combine(requiredPoints, combinations, new ArrayList<>(), 0, i);

            for(List<int[]> combination : combinations){
                totalPaths += calculatePathsForCombination(grid, m, n, combination);
            }
        }

        return totalPaths;

//        return calculatePaths(grid, m, n); // 전체 경로 수 계산
    }

    private static void combine(int[][] requiredPoints, List<List<int[]>> combinations, List<int[]> temp, int start, int k){
        if(k == 0){
            combinations.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start; i<=requiredPoints.length - k; i++){
            temp.add(requiredPoints[i]);
            combine(requiredPoints, combinations, temp, i+1, k-1);
            temp.remove(temp.size() - 1);
        }
    }

    private static int calculatePathsForCombination(int[][] grid, int m, int n, List<int[]> combination){
        // 구현 필요
        int totalPaths = 1;

        int[][] pathsFromPoints = new int[combination.size()][combination.size() + 1];

        for(int i=0; i<combination.size(); i++){
            int[] startPoint = combination.get(i);

            int[][] tempGrid = new int[m+1][n+1];
            for(int[] row : grid){
                System.arraycopy(row, 0, tempGrid[Arrays.asList(grid).indexOf(row)], 0, row.length);
            }
            tempGrid[startPoint[0]][startPoint[1]] = 0;
            for(int j=0; j<combination.size(); j++){
                if(i == j)
                    continue;
                int[] endPoint = combination.get(j);
                tempGrid[endPoint[0]][endPoint[1]] = 0;
            }

            pathsFromPoints[i][combination.size()] = calculatePaths(tempGrid, m, n);
        }

        return totalPaths;
    }

    private static int calculatePaths(int[][] grid, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 시작 지점 설정
        dp[0][0] = 1;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // X 표시된 지점은 건너뛰기
                if (grid[i][j] == BLOCKED) continue;
                for (int[] dir : DIRECTIONS) {
                    int x = i - dir[0], y = j - dir[1];
                    if (x >= 0 && y >= 0) {
                        dp[i][j] += dp[x][y];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
