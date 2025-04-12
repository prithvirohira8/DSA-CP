package Dynamic_Programming;

import java.util.ArrayList;

public class Knapsack_0_1 {
    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
        // dp[i][j] state denotes the maximum profit considering 'i' items & for weight
        // <= 'j'
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) { // j = 0 is considering knapsack capacity as 0
                int curr_weight = weights.get(i - 1); // lst is 0 based
                if (j >= curr_weight) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curr_weight] + values.get(i - 1));
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][w];
    }
}

// Time Complexity: O(N^2)
// If we did not use DP then the time complexity to generate all subsets to calc
// max profit with
// boundary for not exceeding weight would be O(2^N)