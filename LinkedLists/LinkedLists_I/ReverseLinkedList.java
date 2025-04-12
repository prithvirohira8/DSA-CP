package LinkedLists.LinkedLists_I;

// Problem Link: https://leetcode.com/problems/reverse-linked-list/

// Problem: Reverse a Linked List

// Aproach: The simplest approach to solve would be with O(N) Space Complexity,
// by using a Stack. Traverse the LinkedList once and add all nodes in the stack.
// Then create a new LinkedList and add all the nodes while emptying the stack until the stack is empty.
// Solution for this is available in my submission on Leetcode.

// Optimizning the Space Complexity to O(1). Iterative Solution:
// Example Reversal: 1 -> 2 -> 3. Ans: 3 -> 2 -> 1.
// What do we need to reverse first?
// We need to first break the link from 1 -> 2 & create a new link from 2 -> 1.
// Additionally we need to keep track of the part of the node 2, in order to use it and the part ahead 2 in the traversal.
// So we take a node newHead, that is initially null. We store the next node of head in a variable next.
// Then we make head to point at newHead which breakes the link from 1 -> 2. (Although we have 2 in next).
// That's it Job DONE!.
// We then need to move ahead, newHead = head & thereafter head = next.
// Continue this in an iterative loop
// So now newHead = 1 -> null, Head = 2 -> 3 -> .....
// then again, next = 3, head.next = newHead (therefore 2 -> 1 -> null)
// Updating newHead, head to move ahead: newHead = head (newHead = 2 -> 1 -> null), head = 3 -> 4 -> 5....
// The loop needs to continue till we process the last node and head is null.

// The part that is key here is: newHead is keeping track of the reversed linkedList.
// initially yes it is null, nut it makes the head (node that is after it), point to it, 
// and then head moves to the 'next' node of head.

// Example:
// Before Iteration begins:
// newHead = null
// head: 1 -> 2 -> 3 -> 4....

// Iteration 0:
// new Head = 1 -> null
// head = 2 -> 3 -> 4....

// Iteration 1:
// new Head = 2 -> 1 -> null
// head = 3 -> 4....

// Iteration 2:
// new Head = 3 -> 2 -> 1 -> null
// head = 4 -> 5....

// In short in the loop:
// We are storing the part after head, before breaking the link to the next.
// Then the link needs t be broken and head points to newHead.
// Then both head and new Head need to be updated.
// new Head becomes hhead (moves one node behind since there is another node head that 
// was point to it (head))
// head moves ahead to the node it previously pointed to i.e. next.

//  * Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}

// Time Complexty