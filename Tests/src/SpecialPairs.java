
import java.sql.SQLOutput;
import java.util.*;
import java.util.*;

public class SpecialPairs {
    

    public static void main(String[] args) {
    	int [][] intervals= {{1,2,10},{3,5,15},{2,3,12}};
		int []newInterval= {4,8};
		int noOfTrains=3;

		Map<Character,Integer> map=new HashMap<>();
		map.put('a',1);
		map.put('b',2);
		System.out.println("map:"+map);
		int arr[][]=new int[3][5];
		
		List<Train> list=new ArrayList<Train>();
		for(int[] interval:intervals) {
			list.add(new Train(interval[0],interval[1],interval[2]));
		}
		int k=0;
		for(Train t:list) {
				for(int j=t.start-1;j<t.end;j++) {
					arr[k][j]=t.val;
				}
			k++;
		}
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		int finalSum=0;
		for(int i=0;i<arr[0].length;i++) {
			int colsum=0;
			for(int j=0;j<arr.length;j++) {
				colsum+=arr[j][i];
			}
			System.out.println("colsum"+colsum);
			finalSum+=colsum;
		}
		System.out.println("finalSum"+finalSum);
		/*int output[][]=countSpecialPairs(intervals);
		for(int i=0;i<output.length;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(output[i][j]+ " ");
			}
			System.out.println();
		}
        int specialPairs = countSpecialPairs(a);*/ // Output: 6
    }
}

class Train{
	int start;
	int end;
	int val;
	Train(int start,int end,int val){
		this.start=start;
		this.end=end;
		this.val=val;
	}
}


