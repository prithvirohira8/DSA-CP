import java.util.*;
import java.lang.*;

class binarySearch {

    // recursive
    static int bs_recursive(List<Integer> lst, int l, int r, int key) {
        if (l > r)
            return -1;
        int m = (l + r) / 2;
        if (lst.get(m) == key)
            return m;
        if (lst.get(l) == key)
            return l;
        if (lst.get(r) == key)
            return r;
        if (lst.get(m) > key)
            return bs_recursive(lst, l, m - 1, key);
        else
            return bs_recursive(lst, m + 1, r, key);
    }

    // iterative
    static int bs_iterative(List<Integer> lst, int l, int r, int key) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (lst.get(m) == key) {
                ans = m;
                break;
            } else if (lst.get(m) < key) {
                l = m + 1;
            } else if (lst.get(m) > key) {
                r = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(10);
        lst.add(20);
        lst.add(110);
        lst.add(19);
        lst.add(199);
        lst.add(22);
        lst.add(110);
        lst.add(202);
        lst.add(11220);
        lst.add(9);
        lst.add(99);
        lst.add(26);
        Collections.sort(lst);
        System.out.println("Recursive Search: " + bs_recursive(lst, 0, lst.size() - 1, 19));
        System.out.println("Iterative Search: " + bs_iterative(lst, 0, lst.size() - 1, 11220));
        System.out.println("Using Library (key: 19): " + (Collections.binarySearch(lst, 19)));
        int upper_bound = Math.abs(Collections.binarySearch(lst, 25) + 1);
        System.out.println(
                "Using Library Upper Bound (key: 25): " + upper_bound);
        System.out.println(lst);
        System.out.println("lst.length = " + lst.size());
    }
}

// Time Complexity to Search for an element is O(log(n))
// Iterative Search is easy to use to search for lower Bound and Upper Bound
// Binary Search Library:

// Binary Search
// for arrays
// Arrays.binarySearch(nums, element);
// return index based on 0 based indexing
// if element is not present in the array then returns -(insertion_point) - 1;
// insertion point is the point at which the element could be inserted in the
// array

// for lists
// Collections.binarySearch(nums, element)
// return index based on 0 based indexing
// if element is not present in the lst then returns -(insertion_point) - 1;
// insertion point is the point at which the element could be inserted in the
// lst

// (Insertion Point is same as Upper Bound)
// if element is not present, upper_bound =
// Math.abs(Collections.binarySearch(lst, 25) + 1)