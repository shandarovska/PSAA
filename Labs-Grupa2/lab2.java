
/*
Да се напише функција која за две текстуални низи (внесени од тастатура и дадени како аргументи на функцијата) ќе провери дали се анаграми. 
Анаграм е збор или фраза кој може да се добие од буквите на друг збор или фраза (во случај на фраза бројот на празни места не мора да е ист).
 */

import java.util.Scanner;
import java.util.Arrays;

public class lab2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of strings you want to input for array 1: ");
        int size1 = scanner.nextInt();
        scanner.nextLine(); // For the enter

        String[] stringArray1 = new String[size1]; // String niza

        for (int i = 0; i < size1; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            stringArray1[i] = scanner.nextLine();
        }

        System.out.print("Enter the number of strings you want to input for array 2: ");
        int size2 = scanner.nextInt();
        scanner.nextLine();

        String[] stringArray2 = new String[size2]; // String niza

        for (int i = 0; i < size2; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            stringArray2[i] = scanner.nextLine();
        }

        boolean bol = anagram(stringArray1, stringArray2);
        if (bol)
            System.out.println("Da, anagram e!");
        else
            System.out.println("Ne, ne e anagram :(");

        /*
         * Print the string
         * System.out.println("You entered:");
         * for (String str : stringArray) {
         * System.out.println(str);
         * }
         */

        scanner.close();
    }

    private static boolean anagram(String[] stringArray1, String[] stringArray2) {
        if (stringArray1.length != stringArray2.length) {
            return false;
        }
        for (int i = 0; i < stringArray1.length; i++) {
            String sorted1 = sortString(stringArray1[i]);
            String sorted2 = sortString(stringArray2[i]);

            if (!sorted1.equals(sorted2)) {
                return false;
            }
        }

        return true;
    }

    private static String sortString(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
