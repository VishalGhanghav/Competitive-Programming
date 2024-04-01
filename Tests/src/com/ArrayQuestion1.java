package Tests.src.com;
/*Given sorted array can contain duplicate and remove duplicates from array and
output should be in same order as input:
arr={1,1,1,2,2,2,3,3,3}
result={1,2,3}
*/

// 1


import java.net.StandardSocketOptions;

public class ArrayQuestion1 {
    public static void main(String[] args) {
        int[] arr={1,1,1,2,2,2,3,3,3};

       int k=getResultInplace(arr);
        for(int i=0;i<=k;i++){
            System.out.println(arr[i]+" ");
        }
    }

    private static int getResultInplace(int[] arr) {

        int k=0;
        int curr=arr[k];
        for(int i=1;i<arr.length;i++){
            if(curr!=arr[i]){
                k++;
                arr[k]=arr[i];
                curr=arr[i];
            }
        }
        return k;
    }
}
