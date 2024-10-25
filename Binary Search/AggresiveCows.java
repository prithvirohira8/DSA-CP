// Aggresive Cows

// We have c cows that can be added to n positions in a line.
// We have to seperate the cows in such a way that the minimum distance between any 2 cows
// is maximized.

// Intuition:
// The order of the n positions does not matter, so we can sort them.
// We see that it is easier to sperate the cows for a smaller distance and more diffciult
// to accomadate for a larger distance.
// This can be seen as a monotonic function. Hence we can use binary search with the low being 1 and 
// high being the largest value in N elements.@interface

// Then for each value computed we just have to call a check function and see of the cows could be accomadated.

import java.util.*;
import java.io.*;

public class AggresiveCows {
    static boolean check(int x, int[] arr, int c) {
        int dist_diff = 0, temp = arr[0];
        c--;
        for (int i = 1; i < arr.length && c > 0; i++) {
            dist_diff = arr[i] - temp;
            if (dist_diff >= x) {
                c--;
                temp = arr[i];
            }
        }
        if (c > 0)
            return false;
        else
            return true;
    }

    static void solve(int[] arr, int c) {
        Arrays.sort(arr);
        int low = arr[0], high = arr[arr.length - 1], ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, arr, c)) {
                low = mid + 1;
                ans = Math.max(ans, mid);
            } else
                high = mid - 1;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tt = sc.nextInt();
        while (tt-- > 0) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            solve(arr, c);
        }
    }
}

// Time Complexity: O(n*log n) (for sorting) + O(n* log n)(binary search + check
// function)
// Time complexity: O(n*log n)