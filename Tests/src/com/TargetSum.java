package Tests.src.com;

import java.util.ArrayList;
import java.util.List;

public class TargetSum {

   public static List<Integer> resultList=new ArrayList<>();
    public static  void main(String[] args) {
        int [] a= {1,3,5,6,7,9};
        int sum = 17;
       // result = [1,6,7]

        getTargetSum(a,sum,a.length,false);
        System.out.println(resultList);

    }

    public static Boolean getTargetSum(int[] arr, int sum, int n, Boolean flag) {

        if(sum==0){
            return true;
        }
        if(n==0 && sum>0){
            return false;
        }

        if(arr[n-1]<=sum){
            return getTargetSum(arr,sum-arr[n-1],n-1,resultList.add(arr[n-1])) ||
                   getTargetSum(arr,sum,n-1,flag);
       }else{
           return getTargetSum(arr,sum,n-1,flag);
        }
//        List<Integer> notPick = getTargetSum(arr, sum, n - 1, flag);
//        if(arr[n-1]<=sum) {
//            List<Integer> pick = getTargetSum(arr, sum - arr[n - 1], n - 1, resultList.add(arr[n - 1]));
//        }

//
    }
}
