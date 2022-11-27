import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestNumbers {
	public static void main(String[] args) {
		int arr[]=new int[] {5,6,7,8,9};
		int k=3;
		int x=7;
		List<Integer> res=new ce().findClosestElements(arr,k,x);
		System.out.println(res);
	}

	
}

class ce{
class Pair{
    int key;
    int value;
    
    Pair(int key, int value){
        this.key = key;
        this.value = value;
    }

    
}

public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int n = arr.length;
    List<Integer> list = new ArrayList<>();
    
    // make max priority queue
    int cnt=0;
    PriorityQueue<Pair> maxH = new PriorityQueue<>(
    		(a,b)->{
    			if(a.key==b.key) {
    				return b.value-a.value;
    			}else {
    				return b.key-a.key;
    			}
    		});
    
    for(int i=0;i<arr.length;i++) {
    	maxH.add(new Pair(Math.abs(arr[i]-x),arr[i]));
    	
    	if(maxH.size()>k) {
    		maxH.poll();
    	}
    }
    while(!maxH.isEmpty()) {
    	list.add(maxH.poll().value);
    }
    //we are sorting a list of size k .so klogk time complexity
    Collections.sort(list);
    return list;
}
}
