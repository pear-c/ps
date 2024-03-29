package assignDoc4;

import java.util.Arrays;

public class grid {

    // 장애물을 지나지 않는 전체 최단경로 개수
    public static int countAllPaths(int m, int n, int[][] obstacles){
        return countPaths(m, n, obstacles);
    }

    // 장애물 X, 5개 좌표 중 하나의 좌표만 지나는 최단경로 개수
    public static int countPathsWithOnePassThrough(int m, int n, int[][] obstacles, int[][] passThroughPoints){
        int totalPaths = 0;
        for(int[] pass : passThroughPoints){
            int[][] tempObs = new int[obstacles.length + passThroughPoints.length - 1][2];
            int idx = 0;
            for(int[] ob : obstacles)
                tempObs[idx++] = ob;

            for(int[] pt : passThroughPoints){
                if(!Arrays.equals(pt, pass))
                    tempObs[idx++] = pt;
            }

            totalPaths += countPaths(m, n, tempObs);
        }
        return totalPaths;
    }

    // 장애물 X, 5개 좌표 중 하나의 좌표도 지나지 않는 최단경로 개수
    public static int countPathsWithoutPassingThrough(int m, int n, int[][] obstacles, int[][] passThroughPoints){
        int[][] combinedObstacles = Arrays.copyOf(obstacles, obstacles.length + passThroughPoints.length);
        System.arraycopy(passThroughPoints, 0, combinedObstacles, obstacles.length, passThroughPoints.length);
        return countPaths(m, n, combinedObstacles);
    }

    // 기본 경로 계산 함수
    private static int countPaths(int m, int n, int[][] obstacles){
        int[][] dp = new int[m][n];

        for(int[] obstacle : obstacles)
            dp[obstacle[0]][obstacle[1]] = -1;

        if(dp[0][0] == -1)
            return 0;

        dp[0][0] = 1;

        for(int i=1; i<m; i++){
            if(dp[i][0] == -1)
                break;
            dp[i][0] = 1;
        }

        for(int j=1; j<n; j++){
            if(dp[0][j] == -1)
                break;
            dp[0][j] = 1;
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(dp[i][j] == -1)
                    continue;

                if(dp[i-1][j] > 0)
                    dp[i][j] += dp[i-1][j];

                if(dp[i][j-1] > 0)
                    dp[i][j] += dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 5;
        int[][] obs = {{1,1}, {3,3}, {5,3}};
        int[][] pointsToPass = {{0,2}, {1,3}, {2,1}, {3,1}, {4,2}};

        int allPath = countAllPaths(m, n, obs);
        int onePath = countPathsWithOnePassThrough(m, n, obs, pointsToPass);
        int noPath = countPathsWithoutPassingThrough(m, n, obs, pointsToPass);

        System.out.println("특정 좌표 중 2개 이상을 지나가는 경로의 개수 : " + (allPath - onePath - noPath));
    }
}
