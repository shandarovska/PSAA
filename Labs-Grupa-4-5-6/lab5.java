
import java.util.*;

/* Да се креираат две класи Автокомпанија и Летови, информациите за двете класи се надевени во структурите соодветно, треба да се хешираат истите.
 * Да се пополнат информациите за 2 автокомпании со неколку летови. На крај да ни се испечати кои летови имаат лет помалку од 5 часа и со рејтинг на автокомпанијата поголема
 * од 3.5!
 */
class Map<K extends Comparable<K>, E> {

    public K key;
    public E value;

    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {

    public E info;
    public SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {

    private SLLNode<Map<K, E>>[] htable;

    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }

    private int hash(K key) {
        return (Integer) key % htable.length;
    }

    public int getSize() {
        return htable.length;
    }

    public SLLNode<Map<K, E>> getHtable(int i) {
        return htable[i];
    }

    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }

        return null;
    }

    public void insert(K key, E value) {
        Map<K, E> entry = new Map(key, value);

        int h = hash(key);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key) {
        int h = hash(key);

        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }
    }

    public LinkedList<Map<K, E>> getAllEntries() {
        LinkedList<Map<K, E>> entries = new LinkedList<>();
        for (SLLNode<Map<K, E>> bucket : htable) {
            for (SLLNode<Map<K, E>> node = bucket; node != null; node = node.next) {
                entries.add(node.info);
            }
        }
        return entries;
    }
}

class Avtokompanija {

    public String ime, zemja;
    public int ID;
    public double rating;

    public Avtokompanija(String ime, String zemja, int id, double rating) {
        this.ime = ime;
        this.zemja = zemja;
        this.ID = id;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Avtokompanijata " + this.ime + " so zemja na poteklo: " + this.zemja + " i ID " + this.ID
                + " ima rating: " + this.rating + ".";
    }

}

class Letovi {

    public String from_where, to_where;
    public int vreme, broj_patnici;
    public Avtokompanija avtokomp;
    public int flightCode; // сама ставив вештачки клуч

    public Letovi(String od_kade, String do_kade, int time, int br_pat, Avtokompanija avt, int kod) {
        this.from_where = od_kade;
        this.to_where = do_kade;
        this.vreme = time;
        this.broj_patnici = br_pat;
        this.avtokomp = avt;
        this.flightCode = kod;
    }

    @Override
    public String toString() {
        return "Letot poletuva od " + this.from_where + ", sletuva do " + this.to_where + " za " + this.vreme
                + " minut, so broj na patnici "
                + this.broj_patnici + ". So avtokompanijata: " + this.avtokomp + ".";
    }

}

public class lab5 {
    public static void main(String[] args) {
        Avtokompanija[] avtokomp = new Avtokompanija[2];
        avtokomp[0] = new Avtokompanija("WizzAir", "Skopje", 1, 4.5);
        avtokomp[1] = new Avtokompanija("RyanAir", "Tetovo", 2, 3.4);

        Letovi[] letovi = new Letovi[4];
        letovi[0] = new Letovi("Gevgelija", "Kumanovo", 350, 5, avtokomp[0], 6);
        letovi[1] = new Letovi("Shtip", "Ohrid", 250, 5, avtokomp[0], 7);
        letovi[2] = new Letovi("Probishtip", "Kichevo", 200, 5, avtokomp[1], 8);
        letovi[3] = new Letovi("Veles", "Delchevo", 300, 5, avtokomp[1], 9);

        SLLHT<Integer, Avtokompanija> avtokompHashed = new SLLHT<>(2);
        avtokompHashed.insert(avtokomp[0].ID, avtokomp[0]);
        avtokompHashed.insert(avtokomp[1].ID, avtokomp[1]);

        SLLHT<Integer, Letovi> letoviHashed = new SLLHT<>(4);
        letoviHashed.insert(letovi[0].flightCode, letovi[0]);
        letoviHashed.insert(letovi[1].flightCode, letovi[1]);
        letoviHashed.insert(letovi[2].flightCode, letovi[2]);
        letoviHashed.insert(letovi[3].flightCode, letovi[3]);

        validni(letoviHashed);
    }

    private static void validni(SLLHT<Integer, Letovi> letoviHashed) {

        for (int i = 0; i < letoviHashed.getSize(); i++) {
            for (SLLNode<Map<Integer, Letovi>> node = letoviHashed.getHtable(i); node != null; node = node.next) {
                if (node.info.value.vreme <= 300 && node.info.value.avtokomp.rating >= 3.5) {
                    System.out.println(node.info.value);
                }
            }
        }
    }

}
