// Question Link: https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1

// Approach: Exactly same as median of sorted array, only difference here is that
// we ned to find the kth element and not the median, so the left half will be created
// with respect to the kth element.
// Additionally, if k < size of 'a' (smaller array), then h will be k as we do not need more than k elements,
// Similarly if k > size of 'b' (larger array), then we will require atleas k - b.length no of elmeents.

class Solution {
    public long kthElement(int k, int a[], int b[]) {
        if (a.length > b.length)
            return kthElement(k, b, a);

        int l = Math.max(0, k - b.length), h = Math.min(k, a.length);
        while (l <= h) {
            int m1 = l + (h - l) / 2;
            int m2 = k - m1; // m2 is calculated based on the remaining elements that can be added in the
                             // left half

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
                return Long.valueOf(Math.max(l1, l2));
            } else if (l1 > r2)
                h = m1 - 1;
            else if (r1 < l2)
                l = m1 + 1;
        }
        return Long.valueOf(-1);
    }
}

// Time Complexity: O(min(log a, log b))