
/*Дадена е низа од црни и бели топчиња. Бројот на црни и бели топчиња во низата е ист. Да се напише програма која ќе ги поврзе 
секое црно со едно бело топче, при што за поврзувањето ќе се искористи што е можно помалку жица. Програмата да ја прикаже искористената 
должина на жица.*/
import java.util.*;

public class lab9 {

    public static void main(String[] args) {
        int[] balls = { 0, 1, 1, 0, 1, 0, 0, 1 };
        int n = balls.length;

        if (n % 2 != 0) {
            System.exit(0);
        }

        int count0 = 0, count1 = 0;
        for (int ball : balls) {
            if (ball == 0) {
                count0++;
            } else {
                count1++;
            }
        }

        if (count0 != count1) {
            System.out.println("The array doesn't have an equal number of 0s and 1s.");
            return;
        }
        // Kje ja sortiram 0 1 0 1 0 1 0 1
        int[] result = new int[n];
        int zeroIndex = 0;
        int oneIndex = 1;

        for (int ball : balls) {
            if (ball == 0) {
                result[zeroIndex] = 0;
                zeroIndex += 2;
            } else {
                result[oneIndex] = 1;
                oneIndex += 2;
            }
        }
        int wire = 0;
        for (int i = 0; i < n - 1; i += 2) {
            wire += Math.abs(balls[i] - balls[i + 1]);
        }
        System.out.println("Zhica: " + wire);
        System.out.println("Rearranged array: " + Arrays.toString(result));
    }
}
