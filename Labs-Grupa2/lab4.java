
/*Да се напише функција која како аргумент го добива head јазолот на единечно поврзана листа и вредност N (цел број). 
Функцијата треба да ја измине листата и да го отстрани N-тиот јазол од крајот на листата. Задачата да се реши 
и со класата SLinkedList и со класата LinkedList. */
import java.util.LinkedList;

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
    protected int counter = 0;

    public Node<E> getHead() {
        return head;
    }

    public SLinkedList() {
        head = null;
    }

    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
        counter++;
    }

    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error.");
        }
        counter++;
    }

    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                counter++;
                return;
            }

            while (tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }

            if (tmp.next == n) {
                Node<E> node = new Node(e, n);
                tmp.next = node;
                counter++;
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
            counter++;
        } else {
            this.insertFirst(e);
        }

    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            counter--;
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
        System.out.println("counter" + counter);
    }

    public void izbrishi(Node<E> head, int N) {
        if (N > counter) // ako e nadvor od opseg
            return;
        if (counter == N) { // prviot da go izbrisheme od nazad
            deleteFirst();
            printList();
            return;
        }
        Node<E> tmp = head;
        Node<E> prev = null;
        int i = 0;
        while (i <= counter - N - 2) { // da dojdeme do prethodniot na toj koj sakame da go izbrisheme
            tmp = tmp.next;
            i++;
        }
        prev = tmp;
        tmp = tmp.next;
        prev.next = tmp.next;
        counter--;
        printList();
    }
}

public class lab4 {

    public static void main(String[] args) {
        // SLinkedList
        SLinkedList lista = new SLinkedList();

        lista.insertLast(1);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(4);
        lista.insertLast(5);
        lista.insertLast(6);
        lista.insertLast(7);
        Node headd = new Node();
        headd = lista.getHead();

        lista.izbrishi(headd, 7);

        // LinkedList
        LinkedList<Integer> lista2 = new LinkedList();
        lista2.add(1);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);
        lista2.add(5);
        lista2.add(6);
        lista2.add(7);
        izbrishi(lista2, 6);
    }

    private static void izbrishi(LinkedList<Integer> lista, int i) {
        if (i > lista.size())
            return;
        if (i == lista.size()) {
            lista.removeFirst();
            System.out.println(lista);
            return;
        }
        int index = lista.size() - i;
        lista.remove(index);
        System.out.println(lista);
    }
}
