// Maximum Sum Combinations

// Question Link: https://www.interviewbit.com/problems/maximum-sum-combinations/

// Approach:
// The brute force approach is take 2 loops, add all elements, sort in descending order, and
// then select the first C elements. However the time complexity of this method is O(n^2), 
// also the memory space will get exhausted in doing so because if both the lists given are of 10^5,
// the resultant list will be of size 2*10^5 which can give the memory runtime error.

// Greedy Approach Intuition:
// If we sort both the lists given in descending order then the sum of the first elements of the list will
// give the largest element, which will be the first element of the answer list to be returned.
// Hence we know that the largest elements will now be returned byt the sume of the first few elements
// of both the lists sorted in descending order.

// Max Heap Intuition:
// Once the sum of the first elements of both lists is taken we know we can go in 2 directions,
// Either keep the first element of list A and take the second element of list B or take the first element of List A 
// second element of list B. 
// The larger element of the 2 will be taken.
// Moving in 2 directions and keeping track simultaneusly for the larger element will become more tedious,
// as the trees will broaden.
// We can hence use a Max Heap.

// Max Heap Approach:
// The Max Heap will always return the largest element present.
// We know the largest element will be sum of the 1st 2 nos in the lists in descending order.
// We add the sums of the first element of B with all the elements in A in the PRIORITY queue (Max Heap).
// This will help initally keep the size restricted to n
// Then we will run a loop till C, to get the C largest elements.
// The first element polled from the queue will be the sum of the 1st 2 nos in the lists in descending order,
// we add the polled element in the ans and then keeping the index of polled element in list A fixed, and  
// taking the next index element of j, we add the sums.
// The priority queue will adjust itself and set the hoghest element as root node.
// The same process continue C times

public class Solution {
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        PriorityQueue<Triplet> maxHeap = new PriorityQueue<Triplet>(new Comparator<Triplet>(){
            public int compare(Triplet t1, Triplet t2){
                if(t1.value < t2.value) return 1;
                else return -1;
            }
        });
        
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());
        
        for(int i = 0; i < A.size(); i++){
            maxHeap.add(new Triplet(i, 0, A.get(i) + B.get(0)));
        }
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while(C > 0){
            Triplet t = maxHeap.poll();
            ans.add(t.value);
            C--;
            if(t.j == B.size() - 1) continue;
            maxHeap.add(new Triplet(t.i, t.j + 1, A.get(t.i) + B.get(t.j + 1)));
        }
        return ans;
    }
}

class Triplet{
    int i;
    int j;
    int value;
    Triplet(int i, int j, int value){
        this.i = i;
        this.j = j;
        this.value = value;
    }
}

// Time Complexity: O(Nlog(N) + O(C)) ~ O(Nlog(N))
// O(N) to traverse the list,
// O(log(N)) to add the element in the maxHeap
// O(C) to get the 'C' highest sum elements
