//За цел број N (внесен од тастатура) да се генерираат првите N редици од Паскаловиот триаголни

import java.util.*;

public class lab8 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        System.out.println("Vnesi red: ");
        n = s.nextInt();
        pascal(n);
        s.close();
    }

    private static void pascal(int n) {
        int[][] matrix = new int[n][]; // davame vrednost samo kolku reda ke ima

        for (int i = 0; i < n; i++) {
            matrix[i] = new int[i + 1]; // red i kje ima i + 1 elementi

            matrix[i][0] = 1; // prvite elementi se sekogash 1
            matrix[i][i] = 1; // i poslednite, tehnichki dijagonalata

            // j = 1, prva kolona, bidejkji 0tata ni e vekje popolneta so 1
            for (int j = 1; j < i; j++) // ide do i bidejkji tolku elemetni ima toj red
                matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
            // [i - 1] - red nad nego, [j - 1] kolokna levo od nego
            // [i - 1] - red nad nego, [j] ista kolona
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
