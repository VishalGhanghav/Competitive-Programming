package com.LinkedList;
/*
 Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

 

Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false
 

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
 
 */
public class PalindromeInALinkedList {
/*
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null){
            return true;
        }
        //Find the middle of the linked list
        ListNode fast=head;
        ListNode slow=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            
        }
        //Slow is my middle of the linked list
       
         //Reverse the second half of linked list(after slow)
        slow.next=reverseList(slow.next);
        //Move slow by 1
        slow=slow.next;
        
        //Traverse dummy and slow at saame time if slow.val=dummy.val till end.It's palindrome
        ListNode dummy=head;
        while(slow!=null){
            if(slow.val!=dummy.val){
                return false;
            }
            slow=slow.next;
            dummy=dummy.next;
        }
       return true;
        
    }
    public ListNode reverseList(ListNode head){
        ListNode dummy=null;
        ListNode next=null;
        while(head!=null){
            next=head.next;
            head.next=dummy;
            dummy=head;
            head=next;
        }
        return dummy;
    }
}
 */
}
