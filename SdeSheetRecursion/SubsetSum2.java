package SdeSheetRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Given an integer array nums that may contain duplicates, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class SubsetSum2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        getSubsetWithDup(0,nums,res,new ArrayList<>());
        return res;
    }

    public void getSubsetWithDup(int ind,int[] nums,List<List<Integer>> res,List<Integer> temp){
        //at every level of recursive tree we add in ans
        res.add(new ArrayList<>(temp));

        for(int i=ind;i<nums.length;i++){
            //if same value .skip current check next
            if(i!=ind && nums[i]==nums[i-1]){
                continue;
            }
            temp.add(nums[i]);
            getSubsetWithDup(i+1,nums,res,temp);
            temp.remove(temp.size()-1);
        }
    }
    public static void main(String args[]) {
        int arr[] = {1,2,2,3,2,3};
        List<List<Integer>> ls = new SubsetSum2()
                .subsetsWithDup(arr);
        System.out.println("Combinations are: ");
        System.out.println(ls);
    }
}
