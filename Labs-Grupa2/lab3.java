/*Корисникот од тастатура внесува квадратна матрица со димензија N која преставува слика (т.е. е пополнета со вредности од 0 до 255).
 Програмата треба да ја ротира сликата за 90 степени во лево.

Бонус: Задачата да се реши без употреба на нова матрица. */

import java.util.Scanner;

public class lab3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vnesi n");
        int N = scanner.nextInt();
        System.out.println("vnesi ja matricata");
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("Enter matrix at pos [" + i + "][" + j + "] ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        rotirajBonus(matrix, N);
        rotirajBonus1(matrix, N);
        rotiraj(matrix, N);
        scanner.close();
    }

    private static void rotirajBonus1(int[][] matrix, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N / 2; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - i][j];
                matrix[N - 1 - i][j] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void rotiraj(int[][] matrix, int N) {
        int[][] mat1 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat1[N - 1 - j][i] = matrix[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void rotirajBonus(int[][] matrix, int N) {
        // prvichno ja transponirame matricata
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // zamenuvame redovi
        for (int i = 0; i < N / 2; i++) { // mora do pola
            int k = N - 1, p = 0;

            while (p <= k) {
                int temp = matrix[i][p];
                matrix[i][p] = matrix[k][p];
                matrix[k][p] = temp;

                p++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
