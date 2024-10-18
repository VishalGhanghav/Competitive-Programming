package SdeSheetGreedyProblems.easy;

public class LemonadeStand {


    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0; // Track the number of Rs. 5 and Rs. 10 bills
        int n = bills.length;  // Length of the bills array

        for (int i = 0; i < n; i++) {
            // If the customer gives Rs. 5, no change is needed, just collect the bill
            if (bills[i] == 5) {
                five += 1;  // Increase the Rs. 5 count
            } else if (bills[i] == 10) {
                // If the customer gives Rs. 10, we need to give Rs. 5 as change
                if (five > 0) {
                    ten += 1;  // Collect the Rs. 10 bill
                    five -= 1;  // Give back one Rs. 5 bill as change
                } else {
                    return false;  // If no Rs. 5 bill is available, return false
                }
            } else if (bills[i] == 20) {
                // If the customer gives Rs. 20, we try to give Rs. 10 + Rs. 5 as change first
                if (ten > 0 && five > 0) {
                    ten -= 1;  // Use one Rs. 10 bill
                    five -= 1; // Use one Rs. 5 bill
                }
                // If no Rs. 10 is available, try to give three Rs. 5 bills as change
                else if (five >= 3) {
                    five -= 3; // Use three Rs. 5 bills
                } else {
                    return false;  // If neither option is available, return false
                }
            }
        }
        return true;  // If we managed to give change for all customers, return true
    }
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(); // Create an instance of LemonadeStand

        // Test case 1: Should return true
        int[] bills1 = {5, 5, 5, 10, 20};
        System.out.println(ls.lemonadeChange(bills1)); // Output: true

        // Test case 2: Should return false (not enough Rs. 5 bills for change)
        int[] bills2 = {5, 5, 10, 10, 20};
        System.out.println(ls.lemonadeChange(bills2)); // Output: false

        // Test case 3: Should return true (enough change available at every step)
        int[] bills3 = {5, 5, 5, 10, 5, 20, 10, 5};
        System.out.println(ls.lemonadeChange(bills3)); // Output: true
    }
}
