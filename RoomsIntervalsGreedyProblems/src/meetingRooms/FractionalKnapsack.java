package meetingRooms;
/*
 *Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item. 

 

Example 1:

Input:
N = 3, W = 50
values[] = {60,100,120}
weight[] = {10,20,30}
Output:
240.00
Explanation:Total maximum value of item
we can have is 240.00 from the given
capacity of sack. 
Example 2:

Input:
N = 2, W = 50
values[] = {60,100}
weight[] = {10,20}
Output:
160.00
Explanation:
Total maximum value of item
we can have is 160.00 from the given
capacity of sack.
 */
import java.util.Arrays;
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
public class FractionalKnapsack {
	double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Your code here
        //sort by value is to weight
        Arrays.sort(arr,(a,b)->{
           double p=(double)a.value/(double)a.weight;
           double q=(double)b.value/(double)b.weight;
           if(p>q){
               return -1;
           }else{
               return 1;
           }
        });
        double profit=0;
        int currWt=0;
        for(int i=0;i<n;i++){
            if(currWt+arr[i].weight<=W){
                
                currWt+=arr[i].weight;
                profit+=arr[i].value;  
            }else{
                int reqWt=W-currWt;
                profit+=((double)arr[i].value / (double)arr[i].weight)*(double)reqWt;
                break;
            }
                
        }
        return profit;
        
        
    }
}
