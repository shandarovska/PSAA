package ispit02_07_24;

import java.util.*;

/*Да се напише функција која како аргумент добива две единечно поврзани листи и од нив
формира нова листа на тој начин што: Елементите од првата листа се земаат од почетокот на
листата, а елементите од втората листа се земаат од крајот. Притоа, за секој пар земени
елементи (еден од првата, еден од втората листа) се прави проверка дали разликата помеѓу
двата елементи е прост број и доколку тоа е случајот помалиот од двата елементи се сместува
во крајната листа. Доколку разликата меѓу елементите е сложен број во новата листа не се
додава ништо. Листите се изминуваат се додека не се измине помалата од двете листи.
Да се напише главна програма во која од тастатура ќе се внесат две единечно поврзани листи и
ќе се повика функцијата. Во главната програма да се отпечати добиената листа.
 */
class Node<E> {
    protected E data;
    protected Node<E> next;

    public Node() {
        data = null;
        next = null;
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    protected int count;

    public Node<E> getHead() {
        return head;
    }

    public SLinkedList() {
        head = null;
    }

    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
        count++;
    }

    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
            count++;
        } else {
            System.out.println("Error.");
        }
    }

    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                count++;
                return;
            }

            while (tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }

            if (tmp.next == n) {
                Node<E> node = new Node(e, n);
                tmp.next = node;
                count++;
            }
        }
    }

    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }

            Node<E> last = new Node(e, null);
            tmp.next = last;
            count++;
        } else {
            this.insertFirst(e);
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            count--;
        } else {
            System.out.println("Error.");
        }
    }

    public void printList() {
        Node<E> tmp = head;

        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }

    public void reverse() {
        Node<E> tmp = head, next = null, prev = null;

        while (tmp != null) {
            next = tmp.next;
            tmp.next = prev;
            prev = tmp;
            tmp = next;
        }
        head = prev;
    }

}

public class zad1 {
    public static void main(String[] args) {
        SLinkedList<Integer> l1 = new SLinkedList<>();
        SLinkedList<Integer> l2 = new SLinkedList<>();

        int[] niza = { 20, 15, 20, 25, 30, 35, 40, 45, 50, 55 };
        for (int i : niza)
            l1.insertLast(i);
        int[] niza2 = { 5, 12, 18, 30, 7, 14, 22, 13 };
        for (int i : niza2)
            l2.insertLast(i);
        func(l1, l2);
    }

    private static void func(SLinkedList<Integer> l1, SLinkedList<Integer> l2) {
        l2.reverse();
        int m, n;
        Node<Integer> tmp1 = l1.getHead();
        Node<Integer> tmp2 = l2.getHead();
        SLinkedList<Integer> l3 = new SLinkedList<>();

        while (tmp1 != null || tmp2 != null) {
            m = tmp1.data; // zimame
            n = tmp2.data;
            if (prost(Math.abs(m - n))) { // prost e
                if (m < n) { // ako m e pomal
                    l3.insertLast(m);
                } else {
                    l3.insertLast(n);
                }
            }
            tmp1 = tmp1.next;
            tmp1 = tmp2.next;
        }
        System.out.println(l3);
    }

    private static boolean prost(int abs) {
        if (abs <= 1)
            return false;

        for (int i = 2; i < abs; i++)
            if (abs % i == 0)
                return false;

        return true;
    }
}
