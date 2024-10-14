package meetingRooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TrainMinimumPlatforms {
	//Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        //put in priority queue and create minHeap to get least end time on top
        
        List<Train> list=new ArrayList<>();
        for(int i=0;i<n;i++){
           list.add(new Train(arr[i],dep[i]));
        }
        Collections.sort(list,(a,b)->{
                if(a.start<b.start){
                    return -1;
                }else{
                    return 1;
                }
            });
        PriorityQueue<Train> minH=new PriorityQueue<>(
            (a,b)->{
                if(a.end<b.end){
                    return -1;
                }else{
                    return 1;
                }
            });
            
            minH.add(list.get(0));
            for(int i=1;i<n;i++){
             int sp2=list.get(i).start;
             int ep2=list.get(i).end;
             
             int ep1=minH.peek().end;
             if(ep1<sp2){
                 minH.poll();
                 minH.add(new Train(sp2,ep2));
             }else{
                 minH.add(new Train(sp2,ep2));
             }
            }
        return minH.size();
        
    }
    
}

class Train{
    int start;
    int end;
    Train(int start,int end){
        this.start=start;
        this.end=end;
    }
}

