
/* Tribonacci Numbers е дефинирана така што првите три члена се дефинирани со вредности 0, 1 и 1. 
Секој следен број се пресметува како сума од претходните три членови. Да се напише програма која ќе го пресметува N-тиот член на секвенцата, 
каде N е внесено од тастатура.
 */
import java.util.*;

public class lab7 {

    public static void main(String[] args) {
        int n, l = 0, m = 1, r = 1, ans = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vnesi n: ");
        n = scanner.nextInt();
        for (int i = 3; i < n; i++) {
            ans = l + m + r;
            l = m;
            m = r;
            r = ans;
        }
        System.out.println(ans);
        scanner.close();
    }
}
