import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Euler {
    public static void main(String[] args) throws IOException {
        multiples();
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
