import java.util.Scanner;

public class Knapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Objects:");
        int n = sc.nextInt();

        int[] p = {1, 2, 5, 6};  // Profits
        int[] w = {2, 3, 4, 5};  // Weights
        int m = 8;  // Capacity of Knapsack

        knapsack(m, w, p, n);
    }

    public static void knapsack(int m, int[] w, int[] p, int n) {
        int[][] k = new int[n + 1][m + 1];

        // Building the DP table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    k[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    k[i][j] = Math.max(k[i - 1][j], k[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    k[i][j] = k[i - 1][j];
                }
            }
        }

        // Backtracking to find selected items
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (k[i][j] == k[i - 1][j]) {
                System.out.println("index " + i + " 0");  // Item not included
                i--;
            } else {
                System.out.println("index " + i + " 1");  // Item included
                i--;
                j -= w[i];
            }
        }

        System.out.println("Maximum profit: " + k[n][m]);
    }
}
