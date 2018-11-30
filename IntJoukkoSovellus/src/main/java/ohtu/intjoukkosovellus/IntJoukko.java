package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(OLETUSKAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 1 || kasvatuskoko < 1) {
            throw new IndexOutOfBoundsException();
        }
        joukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (!onJoukossa(luku)) {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == joukko.length) {
                kasvataKapasiteettia();
            }
            return true;
        }
        return false;
    }
    
    public boolean onJoukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    private void kasvataKapasiteettia() {
        int[] uusiJoukko = new int[alkioidenLkm + kasvatuskoko];
        for (int i = 0; i < joukko.length; i++) {
            uusiJoukko[i] = joukko[i];
        }
        joukko = uusiJoukko;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                joukko[i] = joukko[alkioidenLkm - 1];
                joukko[alkioidenLkm - 1] = 0;
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuloste = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuloste += joukko[i] + (i < alkioidenLkm - 1 ? ", " : "");
        }
        return tuloste + "}";
    }

    public int[] toIntArray() {
        return Arrays.copyOfRange(joukko, 0, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko(a.alkioidenLkm + b.alkioidenLkm);
        yhdiste.joukko = Arrays.copyOf(a.joukko, a.alkioidenLkm + b.alkioidenLkm);
        yhdiste.alkioidenLkm = a.alkioidenLkm;
        for (int i = 0; i < b.alkioidenLkm; i++) {
            yhdiste.lisaa(b.joukko[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko(Math.max(a.alkioidenLkm, b.alkioidenLkm));
        Arrays.sort(a.joukko);
        Arrays.sort(b.joukko);
        return leikkaus(leikkaus, a.joukko, b.joukko);
    }
    
    private static IntJoukko leikkaus(IntJoukko leikkaus, int[] a, int[] b) {
        int laskuri = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = laskuri; j < b.length; j++) {
                if (a[i] == b[j]) {
                    leikkaus.lisaa(b[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko(a.alkioidenLkm);
        Arrays.sort(a.joukko);
        Arrays.sort(b.joukko);
        erotus.joukko = Arrays.copyOf(a.joukko, a.alkioidenLkm);
        for (int i = 0; i < b.alkioidenLkm; i++) {
            erotus.poista(b.joukko[i]);
        }
        return erotus;
    }
    
}
