package java_rt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Euler {
    public static void main(String[] args) throws IOException {
        //multiples();
        int fibonacciSum = evenFibonacciSum(4000000);
        System.out.println(fibonacciSum);
    }

    public static int evenFibonacciSum(int limit) {
        int sum = 0;
        int fibonacciNumber = 0;
        int n = 0;

        while (fibonacciNumber <= limit) {
            fibonacciNumber = fibonacci(n);
            if (fibonacciNumber <= limit && fibonacciNumber % 2 == 0) {
                sum += fibonacciNumber;
            }
            n++;
        }
        
        return sum;
    }

    public static void printFibonacciSequence(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static void multiples() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        int n = Integer.parseInt(firstLine);

        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            int testCase = Integer.parseInt(line);
            int sum = findMultipleSum(testCase);
            System.out.println(sum);
        }
    }

    public static int findMultipleSum(int a) {
        int sum = 0;

        for (int test = 5; test < a; test += 5) {
            if (test >= a) 
                break;
            sum += test;
        }

        for (int test = 3; test < a; test += 3) {
            if (test >= a)
                break;
            if (test % 5 == 0)
                continue;
                
            sum += test;
        }

        return sum;
    }
}
