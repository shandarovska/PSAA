package Labaratoriski;

/*
 *Да се креира хиерархија на класи за водење на евиденција за лаборатории во еден факултет. За секоја лабораторија е познато името на лабораторијата, 
името на институтот на кој припаѓа и бројот на работни места во неа. Почетната вредност на лабораторијата е 120000 денари, а се зголемува за 5% за
секое работно место. Лабораториите може да бидат софтверски или хардверски.

За софтверска лабораторија дополнително се чува бројот на софтверите кои се дел од лабораторијата (максимум 15), низа со насловите на секој софтвер и
низа од индикатори која кажува дали софтверот е лиценциран или слободен. Вкупната вредност на софтверската лабораторија се одредува со тоа што почетната
вредност лабораторијата се зголемува 5000 денари по работно место ако софтверот е лиценциран и 5 денари по работно место ако софтверот е слободен.

За хардверска лабораторија дополнително се чува информација за типот на хардверска лабораторија (високострујна или нискострујна), бројот на парчиња
хардвер кои се дел од лабораторијата (максимум 30) и низа со цените на секое парче хардвер. Вредноста на лабораторијата со алати се добива со тоа што 
на почетната вредност и се додава 100000 ако е високострујна или 2000 ако е нискострујна а потоа на сумата се додава цената на секое од парчињата хардвер 
што се дел од лабораторијата.

Во секоја од класите да се дефинира конструктор со параметри, методи за печатење на сите информации за објект од соодветната класа и метода за пресметка
на вредноста на објект од соодветната класа.
 */
class Laboratorii {
    protected String imeNaLab;
    protected String imeNaInst;
    protected int brRab;
    protected double vrednost = 120000;

    public Laboratorii() {
        imeNaLab = null;
        imeNaInst = null;
        brRab = 0;
        vrednost = 0;
    }

    public Laboratorii(String ime, String imeI, int brvrab) { // set
        this.imeNaLab = ime;
        this.imeNaInst = imeI;
        this.brRab = brvrab;
    }

    public Laboratorii(Laboratorii l) { // copy constructor
        this.imeNaLab = l.imeNaLab;
        this.imeNaInst = l.imeNaInst;
        this.brRab = l.brRab;
    }

    private double vrednost() {
        vrednost += 0.05 * brRab;
        return vrednost;
    }

    public void print() {
        System.out.println("Ime na laboratorija: " + imeNaLab);
        System.out.println("Ime na Institut: " + imeNaInst);
        System.out.println("Broj vraboteni: " + brRab);
        System.out.println("Vrednost na LAB: " + vrednost());
    }
}

class Softverski extends Laboratorii {
    protected int brSoft; // max 15
    protected String[] niza;
    protected boolean Indikator; // true - slobodno, false - licenciran
    protected double vrednostS;

    public Softverski() {
        this.brSoft = 0;
        this.niza = null;
        this.Indikator = false;
        this.vrednostS = 0;
    }

    public Softverski(int brSoft, String[] niza, boolean Indikator, Laboratorii l) {
        super(l); // se povrzuva so parent class
        this.brSoft = brSoft;
        this.niza = niza;
        this.Indikator = Indikator;
    }

    private double vrednostS() {
        vrednostS = super.vrednost;
        if (Indikator == true)
            vrednostS += 5;
        else
            vrednostS += 5000;
        return vrednostS;
    }

    public void print() {
        super.print();
        int i = 0;
        System.out.println("Broj softveri: " + brSoft);
        System.out.println("Vrednost na SOFT: " + vrednostS());
        if (Indikator == true)
            System.out.println("Softverot e sloboden.");
        else
            System.out.println("Softverot e licenciran.");
        for (i = 0; i < niza.length - 1; i++)
            System.out.print(niza[i] + ", ");
        System.out.print(niza[i] + ".");
    }
}

class Hardverski extends Laboratorii {
    protected boolean tip; // true - visokstrujna, false - niskostrujna
    protected int parchinja; // max 30
    protected int[] niza;
    protected double vrednostH;

    // istite identichni konstruktori i tuka

    private double vrednostH() {
        vrednostH = super.vrednost;
        if (tip == true)
            vrednostH += 100000;
        else
            vrednostH += 2000;

        for (int i = 0; i < niza.length; i++)
            vrednostH += niza[i];
        return vrednostH;
    }
}

public class lab1 {
    public static void main(String[] args) {
        Laboratorii lab = new Laboratorii("Lab1", "FEIT", 5);
        lab.print();
        String[] niza = { "ime1", "ime2", "ime3" };
        Softverski sof = new Softverski(6, niza, false, lab);
        sof.print();
    }
}
