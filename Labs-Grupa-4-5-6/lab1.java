/*Да се напише функција во класата бинарно дрво која ќе провери колку пара од внатрешни јазли во 
дрвото кои се во врска родител-дете го задоволуваат условот и двата да немаат десно дете. 
Функцијата да врати бројот на такви парови.
Да се напише главна програма во која ќе тестира работата на функцијата */

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

    // da nemaat desno dete
    public Integer parovi(BNode<E> root) {
        if (root == null)
            return 0;
        int count = 0;
        if (root.left != null && root.right == null && root.left.right == null)
            count++;
        Integer levo = parovi(root.left);
        Integer desno = parovi(root.right);

        return count;
    }
}

public class lab1 {
    public static void main(String[] args) {

        BSTree<Integer> tree = new BSTree(10);
        tree.insert(8, tree.root);
        tree.insert(6, tree.root);
        tree.insert(7, tree.root);
        tree.insert(2, tree.root);
        System.out.println(tree.parovi(tree.root)); // 1
    }
}
