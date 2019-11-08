package fr.isep;


import java.time.*;
import java.util.*;
import java.util.Random;

public class Main {
    static int Size = Integer.MAX_VALUE / 100000;
    static int[] tableau = new int[Size];
    public static void main(String[] args) {
        InitialiserTableau();
        int [] TableauSelection = new int[Size];
        System.arraycopy(tableau, 0, TableauSelection, 0, Size);
        int [] TableauTrie = TriSelection(TableauSelection);

        int [] TableauInsertion = new int[Size];
        System.arraycopy(tableau, 0, TableauInsertion, 0, Size);
        Instant start2 = Instant.now();
        int [] TableauInsere = TriInsertion(TableauInsertion);
        Instant end2 = Instant.now();
        long duration2 = Duration.between(start2, end2).toMillis();
        System.out.println("le tri par insertion a duré "+ duration2 + "ms");

        int [] TableauBulle = new int[Size];
        System.arraycopy(tableau, 0, TableauBulle, 0, Size);
        Instant start3 = Instant.now();
        int [] TableauBulleres = TriBulle(TableauBulle);
        Instant end3 = Instant.now();
        long duration3 = Duration.between(start3, end3).toMillis();
        System.out.println("le tri par methode des bulles a duré "+ duration3 + "ms");

        int [] TableauQuick = new int[Size];
        System.arraycopy(tableau, 0, TableauQuick, 0, Size);
        Instant start4 = Instant.now();
        quickSort(TableauQuick, 0,Size-1 );
        Instant end4 = Instant.now();
        long duration4 = Duration.between(start4, end4).toMillis();
        System.out.println("le tri par methode quicksort a duré "+ duration4 + "ms");

        int [] TableauNatif = new int[Size];
        System.arraycopy(tableau, 0, TableauNatif, 0, Size);
        Instant start5 = Instant.now();
        Arrays.sort(TableauNatif);
        Instant end5 = Instant.now();
        long duration5 = Duration.between(start5, end5).toMillis();
        System.out.println("le tri par methode native a duré "+ duration5 + "ms");


    }
public static void InitialiserTableau() {
        Instant start =Instant.now();
        System.out.println("Debut d'initialisation ...");
        Random random= new Random();
        for (int i=0; i< tableau.length ;i++){
            tableau[i]=random.nextInt(Size);
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L'initialisation a pris "+ duration +"ms");
}

    public static int[]TriSelection (int []tableau){
        Instant start = Instant.now();
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
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("Le tri par selection à pris "+ duration+ "ms");
        return (tableau);
    }
    public static int[] TriInsertion (int [] tableau){
        for (int i=1; i< tableau.length; i++){
            int elementATrier = tableau[i];
            int j=i;
            while( j>0 && tableau [j-1]> elementATrier) {
                tableau[j]= tableau[j-1];
                j--; }
            tableau[j]= elementATrier;
        }
        return( tableau); }

    public static int[] TriBulle (int [] tableau) {
        boolean estTrie= false;
        while (!estTrie) {
            estTrie= true;
            for (int i=1; i< tableau.length; i++) {
                if (tableau[i-1]> tableau[i]){
                    int swap = tableau [i-1];
                    tableau [i-1]= tableau[i];
                    tableau[i]= swap;
                    estTrie= false; } }
        }
        return( tableau);
    }
    public static void quickSort (int [] tableau, int indGauche, int indDroite){
        if (indGauche< indDroite){
            int indicePartition = partition(tableau, indGauche, indDroite);
            quickSort(tableau, indGauche, indicePartition);
            quickSort(tableau, indicePartition + 1, indDroite); }
        }

    public static int partition(int[] tableau, int indiceGauche, int indiceDroit) {
        int elementPivot = tableau[indiceGauche + (indiceDroit - indiceGauche) / 2];
        int left = indiceGauche - 1;
        int right = indiceDroit + 1;
        while (true) {
            do { left++; }
                while (tableau[left] < elementPivot);
            do { right--; }
                while (tableau[right] > elementPivot);
                if (left >= right) { return right; }
                int tmp = tableau[left];
                tableau[left] = tableau[right];
                tableau[right] = tmp; }
    }
}
