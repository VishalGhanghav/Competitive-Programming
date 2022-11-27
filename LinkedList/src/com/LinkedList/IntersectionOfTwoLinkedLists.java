package com.LinkedList;
/*

 */
public class IntersectionOfTwoLinkedLists {

}

/*
Approach I - Brute Force
Time - O(mn), Space - O(1)

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while(headA!=null)
        {
            ListNode temp = headB;
            while(temp!=null)
            {
                if(temp==headA)
                    return headA;
                temp = temp.next;
            }
            headA = headA.next;
        }
        return null;
    }
Approach II - Using Hashing
Time - O(m+n), Space - O(m)

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> st=new HashSet<>();
        while(headA != null)
        {
            st.add(headA);
            headA = headA.next;
        }
        
        while(headB != null)
        {
            if(st.contains(headB)) 
                return headB;
            headB = headB.next;
        }
        return null;
    }
Approach III - (Using difference of length between lists)
Time - O((m+n)+(l1-l2)+min(m,n), space - O(1)

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = length(headA);
        int l2 = length(headB);
        int d = 0;
        ListNode ptr1;
        ListNode ptr2;
    
        if (l1>l2)
        {
            d = l1-l2;
            ptr1 = headA;
            ptr2 = headB;
        }
        else
        {
            d = l2-l1;
            ptr1 = headB;
            ptr2 = headA;
        }
    
        while(d>0)
        {
            if (ptr1==null)
            {
                return null;
            }
            ptr1 = ptr1.next;
            d--;
        }
        while (ptr1!=null && ptr2!=null)
        {
            if (ptr1==ptr2)
                return ptr1;
            
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return null;
    }
    
    public int length(ListNode head)
    {
        int i = 0;
        while(head!=null)
        {
            i++;
            head = head.next;
        }
        return i;
    }
Approach IV - Optimised
Time - O(2max(m,n)), space - O(1)

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode d1 = headA, d2 = headB;
        while(d1!=d2)
        {
            if(d1==null)
                d1 = headB;
            else
                d1 = d1.next;
            
            if(d2==null)
                d2 = headA;
            else
                d2 = d2.next;
        }
        return d1;
    }*/