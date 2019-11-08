import java.time.*;
import java.util.Random;

public class Main    {
    static int SIZE = Integer.MAX_VALUE/10000;
    static int[] tableau = new int[SIZE];

    public static void main (String[] args) {
        initialiserTableau();
        int[] tableauSelection = new int[SIZE];
        // arraycopy(src, startIndex, dest, startIndex, size)
        System.arraycopy(tableau, 0, tableauSelection, 0, SIZE);
        Instant start = Instant.now();
        System.out.println("Debut du tri...");
        TriSelection(tableauSelection);
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("Le tri a pris " + duration + " ms");
        int[] tableauInsertion = new int[SIZE];
        System.arraycopy(tableau, 0, tableauInsertion, 0, SIZE);
        Instant startb = Instant.now();
        System.out.println("Debut du tri...");
        triInsertion(tableauInsertion);
        Instant endb = Instant.now();
        long durationb = Duration.between(startb, endb).toMillis();
        System.out.println("Le tri a pris " + durationb + " ms");
        int[] tableauBulle = new int[SIZE];
        System.arraycopy(tableau, 0, tableauBulle, 0, SIZE);
        Instant startc = Instant.now();
        System.out.println("Debut du tri...");
        triBulles(tableauBulle);
        Instant endc = Instant.now();
        long durationc = Duration.between(startc, endc).toMillis();
        System.out.println("Le tri a pris " + durationc + " ms");
    }
    public static void initialiserTableau() {
        Instant start = Instant.now();
        System.out.println("Debut d’initialisation...");
        Random random = new Random();
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = random.nextInt(SIZE);
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
    }

    public static int[] TriSelection (int []tableau){ // algorithme de tri par selection
        for (int i = 0; i <tableau.length-1;i++) {
            int indiceMin = i;
            for (int j = i; j < tableau.length; j++) {
                if (tableau[j] < tableau[indiceMin]) {
                    indiceMin = j;
                }
            }
            int swap = tableau[i];
            tableau[i] = tableau[indiceMin];
            tableau[indiceMin] = swap;
        }
        return (tableau);
    }
    public static void triInsertion(int [] tableau) {
        for (int i = 1; i < tableau.length; i++) {
            int elementATrier = tableau[i];
            int j = i;
            while (j > 0 && tableau[j - 1] > elementATrier) {
                tableau[j] = tableau[j - 1];
                j--;
            }
            tableau[j] = elementATrier;
        }
    }
    public static void triBulles (int [] tableau) {
        boolean estTrie = false;
        while (!estTrie) {
            estTrie = true;
            for (int i = 1; i < tableau.length; i++) {
                if (tableau[i - 1] > tableau[i]) {
                    int swap = tableau[i - 1];
                    tableau[i - 1] = tableau[i];
                    tableau[i] = swap;
                    estTrie = false;
                }
            }
        }
    }
    public static void quickSort (int [] tableau) {
        
    }
}
