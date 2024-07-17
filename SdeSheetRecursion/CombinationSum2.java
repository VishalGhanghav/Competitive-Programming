package SdeSheetRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]


Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> tempList=new ArrayList<>();
        getCombinationSum(res,tempList,candidates,target,0);
        return res;
    }

    public void getCombinationSum(List<List<Integer>> res,List<Integer> tempList,int[] arr,int target,int ind){
        if(target==0){
            res.add(new ArrayList<>(tempList));
            return;
        }

        for(int i=ind;i<arr.length;i++){
            //if same as prev element ,break
            if(i>ind && arr[i]==arr[i-1]){
                continue;
            }
            if(arr[i]>target){
                break;
            }
            tempList.add(arr[i]);
            getCombinationSum(res,tempList,arr,target-arr[i],i+1);
            tempList.remove(tempList.size()-1);
        }
    }
    public static void main(String args[]) {
        int arr[] = {1,1,1,2,2};
        int target = 4;
        List < List < Integer >> ls = new CombinationSum2().combinationSum2(arr, target);
        System.out.println("Combinations are: ");
        System.out.println(ls);
    }
}
