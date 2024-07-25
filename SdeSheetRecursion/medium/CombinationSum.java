package SdeSheetRecursion.medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> temp=new ArrayList<>();
        int n=candidates.length;
        getCombinationSum(res,temp,candidates,target,n);
        return res;
    }

    public void getCombinationSum(List<List<Integer>> res,List<Integer> temp,int[] arr,int target,int n){
        if(target==0){
            res.add(new ArrayList<>(temp));
            return;
        }
        if(n==0 && target>0){
            return;
        }

        if(arr[n-1]>target){
            getCombinationSum(res,temp,arr,target,n-1);
        }else{
            temp.add(arr[n-1]);
            getCombinationSum(res,temp,arr,target-arr[n-1],n);
            temp.remove(temp.size()-1);
            getCombinationSum(res,temp,arr,target,n-1);
        }

    }
    public static void main(String args[]) {
        int arr[] = {2,3,6,7};
        int target = 7;
        List < List < Integer >> ls = new CombinationSum().combinationSum(arr, target);
        System.out.println("Combinations are: ");
        System.out.println(ls);
    }
}
