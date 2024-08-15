import java.util.ArrayList;
import java.util.*;

public class Knapsack_0_1 {
    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
        int[][] dp = new int[n + 1][w + 1];

        for (int obj = 1; obj < n + 1; obj++) {
            for (int weight = 1; weight < w + 1; weight++) {
                if (weight - weights.get(obj - 1) < 0)
                    dp[obj][weight] = dp[obj - 1][weight];
                else
                    dp[obj][weight] = Math.max(dp[obj - 1][weight],
                            dp[obj - 1][weight - weights.get(obj - 1)] + values.get(obj - 1));
            }
        }
        return dp[n][w];
    }
}