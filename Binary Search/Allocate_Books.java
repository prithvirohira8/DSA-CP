// Allocate Books

// Problem Link: https://www.interviewbit.com/problems/allocate-books/
// The problem has asked us to distribute a list of books to n students such that:
// - each student gets atleast one book
// - the cost of the student with the most pages is minimum
// We need to return the most pages that a particular student could have in the above case.@interface

// Intuition
// Let's assume there are k books and n students (k >= n). The total no fo ways to distrbute
// k books to n children would be (k - 1) C (n - 1) (almost like k C n but not exactly ecause each
// student should get atleast 1 book) 
// This will yeild a very high time complexity.
// We need to reduce the search space. We can do that by an observation.
// We know that the lowest answer possible would be when each student gets 1 book and the ans is the max
// no of pages of a particular student. Similarly the largest ans possible would be when n = 1 and the 
// entire sum needs to be considered. So the ans lies between max(lst) and sum(lst).
// Now the brute force approach would be to loop in between this inteval and implement a check for each value
// The very first value that passes the check function would be the ans.
// While this would be good for average cases this yeilds an additional time complexity of O(sum(lst) - max(lst)),
// Thereby making the entire time complexity O(N * (sum(lst) - max(lst))) because of the check function (I have later explained how the check function is implemented)
// Can we decrease the O(sum(lst) - max(lst)), yes we can.
// See this O(sum(lst) - max(lst)) loop is nothing but a monotonic function, above a particular value for pages,
// the check function would work for all cases, however below that value it would not work.
// Hence we can use binary search and converge to that point making the entire time complexity: O(N * log(sum(lst) - max(lst)))

// Check Function:
// The check function basically takes a threshold value (x) representing the pages.
// It returns true if the books can be distributed in a way that the threshold value cannot be exceeded.
// If a particular way does not exist, it returns false.
// So basically we keep addding the books to a particular student, until the threshold is about to get exceeded on that
// addition. If so we do increment the students, and start adding for the next student.
// If the total students required excceds the given n, we no it isnt possible and return false.

// Boundary case:
// If books < students, return -1

import java.util.*;
import java.lang.*;

class Allocate_Books {
    static boolean check(int x, List<Integer> lst, int n) {
        int pages = 0, students = 1;
        for (int i = 0; i < lst.size(); i++) {
            if (pages + lst.get(i) <= x) {
                pages += lst.get(i);
            } else {
                students++;
                pages = lst.get(i);
            }
        }
        if (students > n)
            return false;
        else
            return true;
    }

    static int books(List<Integer> lst, int n) {
        if (n > lst.size()) {
            return -1;
        }
        int max = 0, sum = 0;
        for (int i = 0; i < lst.size(); i++) {
            max = Math.max(max, lst.get(i));
            sum += lst.get(i);
        }

        int low = max, high = sum, ans = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (check(mid, lst, n)) {
                high = mid - 1;
                ans = Math.min(ans, mid);
            } else
                low = mid + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> lst = Arrays.asList(97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94,
                57, 1, 53, 8, 44, 68, 90, 24);
        int n = 26;
        System.out.println(books(lst, n));
    }
}

// Time Complexity: O(N * log(sum(lst) - max(lst)))