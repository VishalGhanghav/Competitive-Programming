import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Pairs{
	int left;
	int right;
	Pairs(int left,int right){
		this.left=left;
		this.right=right;
	}
	
}

class Triplet{
	int sum;
	int left;
	int right;
	Triplet(int sum,int left,int right){
		this.sum=sum;
		this.left=left;
		this.right=right;
	}
	
}
public class CountPairs {

	public static void main(String[] args) {
		 int arr[] = { -7, 4, -3, 2, 5, -6, 8, 10 };
	     List<Pairs> result=getPairs(arr,arr.length);
	     System.out.println(result);
	}

	private static List<Pairs> getPairs(int[] arr, int length) {
		int left=0; 
		int right=arr.length-1;

		
		Arrays.sort(arr);
		List<Pairs> resultList=new ArrayList<Pairs>();
		PriorityQueue<Triplet> minhPositive=new PriorityQueue<>((a,b)->a.sum-b.sum);
		PriorityQueue<Triplet> minhNegative=new PriorityQueue<>((a,b)->a.sum-b.sum);
		while(left<=right){
			int sum=0;
			sum+=arr[left]+arr[right];
			if(sum==0){
			//result
				resultList.add(new Pairs(left, right));
				left++;
				right--;
	
			}else if(sum>0){
				right--;
				minhPositive.add(new Triplet(sum, left, right));
				
			}else if(sum<0){
				left++;
				minhNegative.add(new Triplet(-sum, left, right));
			}
		}
		System.out.println(minhNegative);
		int reqSum=minhPositive.peek().sum;
		for(Triplet t:minhPositive) {
			
			
		}
		

		return resultList;
	}

}
