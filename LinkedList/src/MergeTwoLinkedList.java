/*
 * You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

 

Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
 

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeTwoLinkedList {
	/*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        
        ListNode currL1 = l1;
        ListNode currL2 = l2;
        
        while(currL1 != null && currL2 != null){
            //choose the smaller value,
            //append to list
            if(currL1.val <= currL2.val){
                curr.next = currL1;
                
                //move the list1 pointer forward
                currL1 = currL1.next;
            }else{
                curr.next = currL2;
                
                //move the list2 pointer forward
                currL2 = currL2.next;
            }  
            
            //move the curr pointer forward
            curr = curr.next;
            
        }
        
        //append the remaining item list 1
        if(currL2 == null && currL1 != null){
            curr.next = currL1;
        }
        
        //append the remaining item list 2
        if(currL1 == null && currL2 != null){
            curr.next = currL2;
        }
        
        
        return dummyNode.next;
    }*/
}


