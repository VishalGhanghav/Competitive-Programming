package AdityaVermaApproach.Recursion.src.com.Recursion;

import java.util.ArrayList;
import java.util.List;

public class SortAnArrayUsingRecursion {


public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(0);
    list.add(5);
    list.add(2);

    System.out.println("Array before sorting:");
    for (Integer i : list) {
      System.out.print(i + " ");
      
    }
    System.out.println();

    
    List<Integer> sorted = sortArray(list);
    System.out.println("\nArray after sorting:");
    for (Integer s : sorted) {
      System.out.print(s + " ");
      
    }
}

private static List<Integer> sortArray(List<Integer> list) {
	if(list.size()==1) {
		return list;
	}
	//store last element
	int temp=list.get(list.size()-1);
	list.remove(list.size()-1);
	System.out.println(list);
	sortArray(list);
	System.out.println(list);
	//Now add removed element
	return insertAt(list,temp);
}

private static List<Integer> insertAt(List<Integer> list, int key) {
	if(list.size()==0 || list.get(list.size()-1)<=key) {
		list.add(key);
		return list;
	}
	int temp=list.get(list.size()-1);
	list.remove(list.size()-1);
	insertAt(list, key);
	System.out.println("insert temp"+temp);
	list.add(temp);
	return list;
}
}
