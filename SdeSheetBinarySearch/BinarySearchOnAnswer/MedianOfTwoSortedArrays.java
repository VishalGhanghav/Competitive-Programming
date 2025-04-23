package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class MedianOfTwoSortedArrays {

        // Brute
        public double findMedianSortedArraysBrute(int[] nums1, int[] nums2) {
            //I create third merged array and then find median.
            //For odd median = (n1+n2)/2
            //for even median = (n/2 + (n/2-1))
            int n1 = nums1.length;
            int n2 = nums2.length;
            int i=0,j=0,k=0;
            int[] res = new int[n1+n2];
            while(i<n1 && j<n2) {
                if(nums1[i] <= nums2[j]) {
                    res[k] = nums1[i];
                    k++;
                    i++;
                } else {
                    res[k] = nums2[j];
                    k++;
                    j++;
                }
            }

            while(i<n1) {
                res[k++] = nums1[i];
                i++;
            }

            while(j<n2) {
                res[k++] = nums2[j];
                j++;
            }
            //now I have res array
            int n=n1+n2;
            if(n%2==1) {
                return (double) res[n/2];
            } else {
                return (double)(res[n/2]+res[n/2-1]) / 2d;
            }
        }

        // Better
        public double findMedianSortedArraysBetter(int[] nums1, int[] nums2) {
            //I will try to eliminate extra space
            //If i calculate n1+n2 at start itself and
            //for odd:n/2.When we get n/2 thats result.
            //for even:n/2+(n/2-1)/2.0.FInd both n/2 and n/2-1;
            int n1 = nums1.length;
            int n2 = nums2.length;
            int n = n1+n2;
            int ind2 = n/2;
            int ind1 = ind2 -1;
            int i=0,j=0;
            int cnt=0;
            double ind1ele = -1;
            double ind2ele = -1;

            while(i<n1 && j<n2) {
                int val;
                if(nums1[i] <= nums2[j]) {
                    val = nums1[i++];
                } else {
                    val = nums2[j++];
                }

                if(cnt == ind1) ind1ele = val;
                if(cnt == ind2) ind2ele = val;
                cnt++;
            }

            while(i<n1) {
                if(cnt == ind1) ind1ele = nums1[i];
                if(cnt == ind2) ind2ele = nums1[i];
                i++;
                cnt++;
            }

            while(j<n2) {
                if(cnt == ind1) ind1ele = nums2[j];
                if(cnt == ind2) ind2ele = nums2[j];
                j++;
                cnt++;
            }

            if(n%2==1) {
                return ind2ele;
            } else {
                return (double)(ind1ele+ind2ele) / 2d;
            }
        }

        // Optimal : Binary Search
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            //Plan is to somehow get left half and then right half will be achieved auto
            //To get proper left half I will apply binary search on shorter array
            int n1 = nums1.length;
            int n2 = nums2.length;
            //if n1 is bigger swap the arrays:
            if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

            int start = 0;
            int end = n1;
            int leftHalfLength = (n1+n2+1)/2; //odd: 5+4=9 9+1/2=5.5th is median
            int n = n1+n2;
            while(start<=end) {
                //mid1:elmts from arr1 in left half
                //mid2:elmts from arr2 in left half
                int mid1 = (start+end) /2;
                int mid2 = leftHalfLength - mid1;

                //calculate l1,r1,l2,r2
                //Check book for reference.
                //Compare l1<=r2 .So take min_value if l1 not present and max_value if r2 not present
                int l1 = (mid1>0) ? nums1[mid1-1] : Integer.MIN_VALUE;
                int r1 = (mid1<n1) ? nums1[mid1] : Integer.MAX_VALUE;
                int l2 = (mid2>0) ? nums2[mid2-1] : Integer.MIN_VALUE;
                int r2 = (mid2<n2) ? nums2[mid2] : Integer.MAX_VALUE;

                if(l1<=r2 && l2<=r1) {
                    //if odd
                    if(n%2 == 1) {
                        //l1 is max in 1st array and l2 is max in 2nd array for left half
                        return Math.max(l1,l2);
                    } else {
                        //even
                        return (double) (Math.max(l1,l2) + Math.min(r1,r2))/ 2d;
                    }
                } else if (l1>r2) {
                    //There are more elements in 1st array in left half,try for lesser
                    end = mid1-1;
                } else {
                    //l2>r1
                    //There are more elements in 2nd array in left half
                    start = mid1+1;
                }
            }
            return 0;
        }

        // Main method for testing
        public static void main(String[] args) {
            MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();

            int[] nums1 = {1, 3};
            int[] nums2 = {2};

            System.out.println("Brute Median: " + sol.findMedianSortedArraysBrute(nums1, nums2));
            System.out.println("Better Median: " + sol.findMedianSortedArraysBetter(nums1, nums2));
            System.out.println("Optimal Median: " + sol.findMedianSortedArrays(nums1, nums2));
        }

}
