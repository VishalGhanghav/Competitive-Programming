package SdeSheetBinarySearch.BinarySearchOnAnswer;

import java.util.PriorityQueue;

public class MinimizeMaxDistanceBetweenGasStations {

        static class Pair {
            double first;
            int second;

            Pair(double first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        public static void main(String[] args) {
            int[] stations = {1, 2, 3, 6, 7};
            int k = 2;

            System.out.println("Brute Force: " + findSmallestMaxDistBrute(stations.clone(), k));
            System.out.println("Better PriorityQueue: " + findSmallestMaxDistBetter(stations.clone(), k));
            System.out.println("Optimal Binary Search: " + minimiseMaxDistance(stations.clone(), k));
        }

        public static double findSmallestMaxDistBrute(int[] stations, int k) {
            // code here
            //brute
            //create the howmany array
            int n = stations.length;
            int[] howmany = new int[n - 1];

            for (int gas = 1; gas <= k; gas++) {
                double maxValue = -1;
                int maxInd = -1;
                for (int i = 0; i < n - 1; i++) {
                    double diff = stations[i + 1] - stations[i];
                    double sectionLength = diff / (double) (howmany[i] + 1);
                    //If current section is the biggest then that should be
                    //considered for reduction
                    if (sectionLength > maxValue) {
                        maxValue = sectionLength;
                        maxInd = i;
                    }
                }
                //Add the gas station with maxValue in howmany arr
                howmany[maxInd]++;
            }

            //How many array is ready.Now we already have placed gas stations optimally
            //such that maximum distance between adj stations is minimized
            double maxAns = -1;

            for (int i = 0; i < n - 1; i++) {
                double diff = stations[i + 1] - stations[i];
                double sectionLength = diff / (double) (howmany[i] + 1);
                maxAns = Math.max(maxAns, sectionLength);
            }
            return maxAns;
        }

        public static double findSmallestMaxDistBetter(int[] stations, int k) {
            //Better
            //I will pass sectionLength and sectionIndex as ip
            int n = stations.length;
            int[] howmany = new int[n - 1];

            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
                if (b.first > a.first) return 1;
                else if (b.first < a.first) return -1;
                return 0;
            });

            for (int i = 0; i < n - 1; i++) {
                int diff = stations[i + 1] - stations[i];
                pq.add(new Pair(diff, i));
            }

            for (int gas = 1; gas <= k; gas++) {
                //Each time I get maxSection at peak
                Pair top = pq.poll();
                int sectionInd = top.second;
                //add the station
                howmany[sectionInd]++;
                //calculate new sectionLength
                double diff = stations[sectionInd + 1] - stations[sectionInd];
                double newSectionLength = diff / (double) (howmany[sectionInd] + 1);
                pq.add(new Pair(newSectionLength, sectionInd));
            }

            return pq.peek().first;
        }

        public static double minimiseMaxDistance(int[] arr, int k) {
            int n = arr.length; // size of the array
            double low = 0;
            double high = 0;

            //Find the maximum distance:
            for (int i = 0; i < n - 1; i++) {
                high = Math.max(high, (double) (arr[i + 1] - arr[i]));
            }

            //When you're binary searching over real numbers (like distances, money, precision),
            //the key point is: You can’t use mid + 1 or mid - 1 because there is no next number like
            //that — floats are not discrete like integers. So instead, we check for small enough difference:
            //while (end - start > EPSILON)
            double diff = 1e-6;
            double ans =-1;
            while (high - low > diff) {
                double mid = (low + high) / 2.0;
                int cnt = numberOfGasStationsRequired(mid, arr);
                if (cnt > k) {
                    low = mid;
                } else {
                    ans = mid;
                    high = mid;
                }
            }
            return (double) Math.round(ans * 100) /100;
        }

        public static int numberOfGasStationsRequired(double dist, int[] arr) {
            int n = arr.length; // size of the array
            int cnt = 0;
            for (int i = 1; i < n; i++) {
                int numberInBetween = (int) ((arr[i] - arr[i - 1]) / dist);
                if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                    numberInBetween--;
                }
                cnt += numberInBetween;
            }
            return cnt;
        }

}
