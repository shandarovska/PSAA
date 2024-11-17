
/*За внесена редица од цели броеви и цел број N, програмата треба да ја измине редица така што ќе креира нова редица при што 
секој елемент од редицата ќе се замени со N елементи чија вредност ќе биде data/N, каде data е оригиналната вредност на елементот 
кој се заменува (ако целобројно делење не е можно, тогаш елементот што се сместува е долна граница од делењето).
 */
import java.util.*;

public class lab6 {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<Integer>();
        int N;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vnesi go N: ");
        N = scanner.nextInt();

        q.add(2);
        q.add(3);
        q.add(5);
        q.add(6);

        promena(q, N);
        scanner.close();
    }

    private static void promena(Queue<Integer> q1, int N) {
        Queue<Integer> q2 = new LinkedList<Integer>();
        int m, el, i;
        while (!q1.isEmpty()) {
            i = 0;
            m = q1.remove(); // зима елемент
            el = m / N;
            while (i < N) {
                q2.add(el);
                i++;
            }
        }
        System.out.println(q2);
    }
}
