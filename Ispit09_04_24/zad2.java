/*Да се напише функција која како аргумент добива магацин. Во магацинот се сместени топчиња
со различни четири тежини, нумерирани од 1 до 4. Топчиња со поголема тежина можат да ги
поништат сите топчиња со помала тежина кои се наоѓаат под нив во магацинот. Дополнително,
две топчиња со иста тежина кои се наоѓаат едно до друго можат да се здружат во едно топче
со тежина 5. Две топчиња со тежина 5 не можат да се здружат во едно топче. Топче со тежина
5 може да поништи само две топчиња со строго помала тежина кои се директно под него во
магацинот. Функцијата треба да го врати променетиот магацин.
Да се напише главна програма во која ќе се внесе еден магацин и во која ќе се повика и тестира
работата на функцијата.  */
import java.util.*;



public class zad2 {
    
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(3);
        s.push(2);
        s.push(4);
        s.push(3);
        s.push(3);
        s.push(2);
        s.push(1);
        s.push(1);
        s.push(2);
        s.push(3);
        func(s);
        }
    private static void func(Stack<Integer> s) {
        Stack<Integer> s1 = new Stack<>();
        int m, n;
        while(!s.isEmpty()) {
            m = s.pop(); //go vadime prviot
            if(s.isEmpty()) {
                s1.push(m);
                break;
             } // ako go izvadime posledniot element
            n = s.peek(); //da dzirneme za da gi sporedime
            if(m == 5) { // ako topot ni e 5-ka, treba da gi izbrishe dve pod nego
                if(n != 5) //dva elementa gi otstanuva
                    s.pop();
                n = s.peek();
                if(n != 5)  
                    s.pop(); 
            }
            if(m == n && m != 5) { //dali imaat ista tezhina, m != 5 bidejkji ne mozhe topche so tezhina 5 da se spoi
                s.pop(); //toj sho e na peek da se izvadi
                s.push(5); // na nivno mesto 5ka,
            }
            else {
                s1.push(m); // da go vratime nazad
            }
        
        }
        while(!s1.isEmpty())
            s.push(s1.pop());   
        System.out.println(s); 
    }
}