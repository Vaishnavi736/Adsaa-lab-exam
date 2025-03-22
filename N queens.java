import java.util.Scanner;

public class NQueens {
    static int[] x;

    // Checks if the queen can be placed at row k, column i
    public static boolean place(int k, int i) {
        for (int j = 1; j < k; j++) {
            if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k)) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to solve N-Queens problem
    public static void nqueens(int k, int n) {
        if (k > n) {
            // Print the solution
            for (int i = 1; i <= n; i++) {
                System.out.print(x[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                if (place(k, i)) {
                    x[k] = i;
                    nqueens(k + 1, n); // Recursive call
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of queens: ");
        int n = scanner.nextInt();
        scanner.close();

        x = new int[n + 1]; // Initialize position array
        nqueens(1, n);
    }
}
