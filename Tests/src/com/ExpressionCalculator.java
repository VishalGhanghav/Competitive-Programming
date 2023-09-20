package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExpressionCalculator {

    public static String evaluateExpression(String expression) throws IOException {
        String baseUrl = "http://api.mathjs.org/v4/";
        //Creating the api url which would be in this format http://api.mathjs.org/v4/?expr=2*(7-3)
        String apiUrl = baseUrl + "?expr=" + expression.replace(" ", "%20");

        StringBuilder result = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression;
        //We will end the Java code once user enters end
        while (!(expression = reader.readLine()).equals("end")) {
            String result = evaluateExpression(expression);
            System.out.println(expression + " = " + result);
        }
    }
}
