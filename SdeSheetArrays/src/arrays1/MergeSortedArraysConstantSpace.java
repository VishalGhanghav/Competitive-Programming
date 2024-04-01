package arrays1;

import java.util.Arrays;

public class MergeSortedArraysConstantSpace {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left=m-1;
        int right=0;
        while(left>=0 && right<n){
            if(nums1[left]<=nums2[right]){
                left--;
                right++;
            }else if(nums1[left]>nums2[right]){
                    int temp=nums1[left];
                    nums1[left]=nums2[right];
                    nums2[right]=temp;
                    left--;
                right++;
            }
        }

        for(int i=0;i<nums2.length;i++){
            nums1[m+i]=nums2[i];
        }
        Arrays.sort(nums1);

    }
}
