package AdityaVermaApproach.BinarySearch.src.com.BinarySearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BInarySearchOnReverseSortedArray
{
    
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        //2 is the size as we pass n and k
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
        	//enter n and k
            String s[] = read.readLine().trim().split("\\s+");
            
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int arr[] = new int[n];
            //enter the array
            String st[] = read.readLine().trim().split("\\s+");
            
            for(int i = 0; i < n; i++)
            {
                arr[i] = Integer.parseInt(st[i]);
            }
            
            Answer obj = new Answer();
            
            System.out.println(obj.searchInSorted(arr, n, k));
        }
    }
}
// } Driver Code Ends
class Answer{
    static int searchInSorted(int arr[], int N, int K)
    {
        
        // Your code here
        int start=0;
        int end=N-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            if(arr[mid]==K){
                return 1;
            }else if(arr[mid]<K){
            	end=mid-1;
                
            }else if(arr[mid]>K){
            	start=mid+1;
            }
        }
        return -1;
    }
}