package LabsV2;

/* За дадено бинарно дрво важат следните правила:

    коренот е со вредност 100,

    ако јазол има вредност x и има лево дете, тогаш вредност на левото дете е x/2 – 1 (ако оваа пресметка е позитивен број) 
    или 0 (ако x/2 – 1 е негативен број),
    ако јазол има вредност x и има десно дете, тогаш вредноста на десното дете е x/2 – 2 (ако оваа пресметка е позитивен број)
     или 0 (ако x/2 – 2 е негативен број).

По одреден процес дрвото и неговата содржина се оштетени, така што сите вредности во дрвото се заменети со вредност -1.
Следејќи ги правилата кои важат за бинарното дрво имплементирајте функција за корегирање на дрвото, која како аргумент
ќе добие оштетено дрво и истото ќе го реконструира. Дополнително, напишете и функција за пребарување на одредена вредност
во реконструираното дрво која враќа информација за тоа дали вредноста се наоѓа во дрвото или не.

Да се напише главна програма во која ќе се тестира работата на двете функции. */

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

public class lab2 {
    public static void main(String[] args) {

        BSTree<Integer> tree = new BSTree(100);
        tree.insert(8, tree.root);
        tree.insert(6, tree.root);
        tree.insert(7, tree.root);
        tree.insert(2, tree.root);
        koregiraj(tree);
        tree.inorder(tree.root);
        System.out.println();
        System.out.println(baraj(tree, 5));
    }

    public static BNode<Integer> koregiraj(BSTree<Integer> tree) {
        return koreg(tree, tree.root);
    }

    public static boolean baraj(BSTree<Integer> tree, int i) {
        return search(tree.root, i);
    }

    public static BNode<Integer> koreg(BSTree<Integer> tree, BNode<Integer> r) {
        if (r == null)
            return null;
        if (tree.root == r) {
            r.info = 100;
        }
        if (r.left != null)
            r.left.info = (r.info / 2 - 1 > 0) ? r.info / 2 - 1 : 0;

        if (r.right != null)
            r.right.info = (r.info / 2 - 2 > 0) ? r.info / 2 - 1 : 0;
        koreg(tree, r.left);
        koreg(tree, r.right);

        return r;
    }

    public static boolean search(BNode<Integer> r, int i) {
        if (r == null)
            return false;
        if (r.info == i)
            return true;
        return search(r.left, i) || search(r.right, i);
    }
}