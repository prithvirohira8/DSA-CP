// Find the kth largest Element in an array

// Question Link: https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/

// Approach 1: Simple sorting

// This approach involves simply sorting the array and returning the (n-k)th element from 
// the array

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Using Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i = 0; i < nums.length; i++){
            maxHeap.add(nums[i]);
        }
        int ans = 0;
        while(k != 0){
            ans = maxHeap.poll();
            k--;
        }
        return ans;
    }
}

// Time Complexity: O(nlog(n))
// The time complexity to sort is O(nlog(n))

// Approach 2: Using max Heap (more optimized)

// We can use create a max heap from the array of elements presented and then keep polling k times
// the last element returned after polling will be the answer

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Using Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i = 0; i < nums.length; i++){
            maxHeap.add(nums[i]);
        }
        int ans = 0;
        while(k != 0){
            ans = maxHeap.poll();
            k--;
        }
        return ans;
    }
}

// Time Complexity: O(log(n)) + O(k)
// the time complexity to create a heap from an array of elements is O(log(n)) and O(k) the time comlexity to poll k times

// Approach 3: Using Min heap

// We can create a minHeap of size k of the array (traversing from index 0 to k - 1). Now from index k to n,
// we can check if nums[i] > k. If it is greater than k then we will pop the minheap and add nums[i] in the min Heap.
// If we logically think then kth largest actually can be found if we have a minHeap of the k largest elements in the 
// array. The root of the minHeap in that case will represent the kth largest element in the array. So if we first create a minHeap of the first k elements,
// the root of the minHeap will represent the smallest element from the forst k elements, then as we traverse from k to n, for every element that is bigger than the root,
// the root is polled and the element is added with a time complexity of O(log(n)) (TC to add a element in the heap), and the root node will be the smallest element
// from the new heap formed

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Using Min Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for(int i = 0; i < k; i++){
            minHeap.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++){
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.peek();
    }
}

// Time Complexity(O(log(k)) + O(n - k))
// O(log(k)) to create a heap from the elements available and O(n - k) for the rest of the traversals