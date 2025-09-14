package SdeSheetRecursion.medium;
import java.util.*;

public class GenerateBinaryStrings {

    public static List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        helper(n, "", '0', ans);  // Start with last = '0' so that '1' can be chosen
        return ans;
    }

    private static void helper(int n, String s, char last, List<String> ans) {
        if (s.length() == n) {
            ans.add(s);
            return;
        }

        // Always can put '0'
        helper(n, s + "0", '0', ans);

        // Put '1' only if last is not '1'
        if (last != '1') {
            helper(n, s + "1", '1', ans);
        }
    }

    public static void main(String[] args) {
        int N = 3;
        List<String> result = generateBinaryStrings(N);
        System.out.println(result); // [000, 001, 010, 100, 101]
    }
}
