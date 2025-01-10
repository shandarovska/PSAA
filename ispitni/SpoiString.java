package ispitni;

import java.util.*;

/* Imeme SLinkedList koja izgleda vaka: c -> a -> t -> , -> d -> o -> g
 * sakame da dobieme: cat -> , -> dog
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

public class SpoiString {
    public static void main(String[] args) {
        SLinkedList<String> lista = new SLinkedList<>();
        String[] e = { "c", "a", "t", ",", "d", "o", "g" };
        for (String i : e)
            lista.insertLast(i);
        lista.printList();
        izmeni(lista);
    }

    private static void izmeni(SLinkedList<String> lista) {
        SLinkedList<String> list1 = new SLinkedList<>();
        Node<String> tmp = lista.getHead();
        String s = "";
        while (tmp != null) {

            while (tmp != null && !tmp.data.equals(",")) {
                s += tmp.data;
                tmp = tmp.next;
            }
            if (!s.isEmpty()) {
                list1.insertLast(s);
                s = "";
            }
            if (tmp != null && tmp.data.equals(",")) {
                list1.insertLast(",");
                tmp = tmp.next;
            }

        }
        list1.printList();
    }
}
