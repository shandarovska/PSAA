package LabsV2;
/*
 * Да се напише функција која како аргументи добива бинарно пребарувачко дрво и
 * единечно поврзана листа. Функцијата треба да ги одреди и
 * врати сите елементи од бинарното пребарувачко дрво кои се лево дете на својот
 * родител и кои во единечно поврзаната листа имаат непарен претходник.
 * 
 * Да се напише главна програма во која ќе се тестира работата на функцијата. Во
 * главната програма да се испечати резултатот добиен од функцијата.
 */

class Node<E> {
    protected E data;
    protected Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        data = null;
        next = null;
    }
}

class SLList<E> {
    private Node<E> head;
    private int size;

    public SLList() {
        size = 0;
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public void insertFirst(E el) {
        Node<E> first = new Node(el, head);
        head = first;
    }

    public void insertAfter(E el, Node<E> n) {
        if (n != null) {
            Node<E> nnode = new Node(el, n.next);
            n.next = nnode;
        } else {
            System.out.println("Error.");
        }
    }

    public void insertBefore(E el, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(el);
                return;
            }
            while (tmp.next != n && tmp.next != null) {
            }
            tmp = tmp.next;
            if (tmp.next == n) {
                this.insertAfter(el, tmp);
            }
        }
    }

    public void insertLast(E el) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null)
                tmp = tmp.next;
            Node<E> last = new Node(el, null);
            tmp.next = last;
        } else
            this.insertFirst(el);
    }

    public void deleteFirst() {
        if (head != null)
            head = head.next;
        else
            System.out.println("Error.");
    }

    public void printList() {
        Node<E> tmp = head;
        while (tmp.next != null) {
            System.out.print("|" + tmp.data + "|->");
            tmp = tmp.next;
        }
        System.out.println("|" + tmp.data + "|");
    }
}

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;

    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;

    public BSTree() {
        root = null;
    }

    public BSTree(E info) {
        root = new BNode(info);
    }

    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }

    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode(info);
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) { // left
            r.left = insert(info, r.left);
        } else if (comp > 0) { // right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
        }

        return r;
    }

    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }

    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // brishi go jazolot info
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }

        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }
}

public class lab4 {

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree(10);
        tree.insert(5, tree.root);
        tree.insert(15, tree.root);
        tree.insert(3, tree.root);
        tree.insert(7, tree.root);
        tree.insert(16, tree.root);
        tree.insert(13, tree.root);

        SLList<Integer> lista = new SLList();

        lista.insertLast(1);
        lista.insertLast(6);
        lista.insertLast(3);
        lista.insertLast(5);
        lista.insertLast(7);
        lista.insertLast(10);
        lista.insertLast(11);
        lista.insertLast(13);
        func(tree, lista);
    }

    private static void func(BSTree<Integer> tree, SLList<Integer> lista) {
        getroot(tree.root, lista);
    }

    private static void getroot(BNode<Integer> root, SLList<Integer> lista) {
        if (root == null)
            return;

        if (root.left != null) { // da ima levo dete
            proveri(root.left.info, lista);
        }

        getroot(root.left, lista);
        getroot(root.right, lista);
    }

    private static void proveri(Integer info, SLList<Integer> lista) {
        Node<Integer> broj = lista.getHead();
        Node<Integer> pozadi = null;
        while (broj != null) {
            if (broj.data.compareTo(info) == 0) {
                if (pozadi != null && (pozadi.data % 2) != 0) { // da ima prethodnik so neparen broj
                    System.out.print(info + ", ");
                }
            }
            pozadi = broj;
            broj = broj.next;
        }
    }
}