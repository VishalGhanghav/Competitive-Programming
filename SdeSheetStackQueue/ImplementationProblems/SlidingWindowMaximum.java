package SdeSheetStackQueue.ImplementationProblems;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans=new int[nums.length-k+1];
        int i=0,j=0;
        //I will be adding and removing from both ends .SO lets use deque
        Deque<Integer> dq=new LinkedList<>();
        //Consider deque like a queue with ability to add and remove from both ends
        //deque functions addFirst addLast removeFirst removeLast
        while(j<nums.length){
            //basic calculation
            //if first time add in dq
            if(dq.isEmpty()){
                dq.addLast(nums[j]);
            }else{
                //if new element greater than curr in dq .current can be popped
                while(!dq.isEmpty() && dq.peekLast()<nums[j]){
                    dq.pollLast();
                }
                dq.addLast(nums[j]);
            }
            //till window hit size j++
            if(j-i+1<k){
                j++;
            }else if(j-i+1==k){
                //biggest elmt will come at first
                ans[i]=dq.peekFirst();
                //changes in i before slide
                //dq=[3,-1] and in start of array  ie.nums[i] we have same element.
                //It means this was max for previous calculation.So pop this
                if(nums[i]==dq.peekFirst()) {
                    dq.pollFirst();
                }
                i++;
                j++;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);

        // Print the result
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
