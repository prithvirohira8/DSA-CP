// Question  Link: https://leetcode.com/problems/median-of-two-sorted-arrays/

// Approach: 
// Simplest Approach (Time Complexity: O((n+m)*log(n+m)))
// Just merge the 2 sorted arrays, and find the median after sorting the array again.@interface

// Merging Sorted Array Approach (Time Complexity: O(n+m))
// The sorted arrays can be merged using 2 pointers on each array while iteration and comparison of elements
// The median can then be returned.

// Most Optimal Approach (Time Complexity: O(min(log a, log b)))
// This is a classical binary search problem. The first hint of binary search is given
// in the question when it is said that the 2 arrays are sorted.
// To understand how binary search can be used here the question needds to be analyzed differently.
// If we need the median, we need to identify the no of elements from array a and the no of elements
// from the left half of array b, that would be present in the left half of the combined array.
// We know both the arrays a and b are sorted. So if we maintain 2 pointers in each array l1,r1 and l2, r2 such that
// l1 and l2 represent the pointers till which the elements will be selected from array a and b to be added in
// the left half of the combined array and r1 and r2 represent the pointers from which the remaining elements of array a and
// b would be added to the right half of the combined array.
// Since the array's are sorted l1 <= r1 and l2 <= r2, however the above condition will prevail if l1 is also <= r2 and 
// l2 is also <= r1.
// Once this condition is reached we can obtain the median.
// if there are odd elements, then the median is a single element, that would be max(a[l1], b[l2])
// else the median would be avg of 2 elements ( max(a[l1], b[l2]) + min(a[r1], b[r2])) / 2.0
// Now how do we make sure that the conditions discussed abover for l1,l2,r1,r2 can be reached?
// The ans is binary search. We can try for each element of the array too that would give us O(n) time complexity.
// However binary search allows us to reduce the search space.
// So we apply binary search on the smaller array, the element to the left of mid is taken as l1, the mid is taken as r1
// We calculate mid2 too by taking into consideration, the remaining elements that need to be added in the left halfo of the 
// combined array. l2 and r2 are calculated using a similar way as l1 and r1. However m2 does not need to be calculated using binary search
// and can be obtained directly.
// We are basically finding m1 (mid1) using binary search and doing the comparisons.
// If we do not get the optimal condition, then we change the value of l and h using binary search.
// if l1 > r2, then the value of m1 needs to decrease so h = m1 - 1;
// if r1 < l2, then the value of m1 needs to increase so h = m1 + 1;

// Now a few Edge cases need to be handled.
// There can be cases when no elemetn from array a or b needs to be added in the left half.
// So l1, l2 are initialized with MIN_VALUEs and r1, r2 are initialized with MAX_VALUEs
// They handle the boundary conditions, addtionally for the median max(l1, l2) and min(r1, r2) are considered.
// Both l1, l2 and r1, r2 will not be MIN_VALUEs or MAX_VALUEs at a particular time, so the edge case gets handled too.

// Note: In this solution h is taken as a.length, usually in binary search we were taking it as a.length - 1
class Solution {
    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length)
            return findMedianSortedArrays(b, a);
        int l = 0, h = a.length;
        while (l <= h) {
            int m1 = (l + h) / 2;
            int m2 = (a.length + b.length + 1) / 2 - m1; // m2 is calculated based on the remaining elements to be added
                                                         // in the left half

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (m1 > 0)
                l1 = a[m1 - 1];
            if (m2 > 0)
                l2 = b[m2 - 1];
            if (m1 < a.length)
                r1 = a[m1];
            if (m2 < b.length)
                r2 = b[m2];

            if (l1 <= r2 && l2 <= r1) {
                if ((a.length + b.length) % 2 == 0) {
                    return (double) ((Math.max(l1, l2) + Math.min(r1, r2)) / 2.0);
                } else {
                    return (double) Math.max(l1, l2);
                }
            } else if (l1 > r2)
                h = m1 - 1;
            else if (l2 > r1)
                l = m1 + 1;
        }
        return 0.0;
    }
}

// Time Complexity: O(min(log a, log b))