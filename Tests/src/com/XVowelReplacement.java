package com;

import java.util.Scanner;

import java.util.Scanner;

public class XVowelReplacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String input = scanner.nextLine();
        scanner.close();

        String output = replaceXVowels(input);
        System.out.println("Output: " + output);
    }

    public static String replaceXVowels(String input) {
        StringBuilder result = new StringBuilder();
        char prevChar = '\0';
        int count = 0;

        for (char c : input.toCharArray()) {
            char lowercaseC = Character.toLowerCase(c);
            if ("aeimoxy".indexOf(lowercaseC) != -1) {
                if (prevChar == '\0' || prevChar != lowercaseC) {
                    result.append(Character.toUpperCase(c));
                    prevChar = lowercaseC;
                    count = 1;
                } else if (count == 1) {
                    result.append(Character.toUpperCase(c));
                    count++;
                }
            } else {
                result.append(c);
                prevChar = '\0';
                count = 0;
            }
        }

        return result.toString();
    }
}

