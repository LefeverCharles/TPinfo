import java.util.*;

public class Main {
    //Variables globales necessaires pour l'exercice 3
    static HashMap<String, ArrayList<String>> bibliotheque = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){
        //Test Exercice 1
        /*String[] test = {"a","bob","radar","kayak","ressasser","mexique","melodrame","marquis"};
        for(String s : test) {
            System.out.println("Le mot "+s+" est un palindrome:");
            System.out.println("D apres la fonction iterative: "+isPalindrome(s));
            System.out.println("D apres la fonction recursive: "+isPalindromeRec(s));
        }*/

        //Test Exercice 2
        /*ArrayList<Integer> liste = new ArrayList<Integer>(Arrays.asList(4, -2, 31, -5, 12));
        ArrayList<Integer> res =  new ArrayList<Integer>(Arrays.asList(-5, 31, -2, 12, 4));
        triListe(liste);
        //Si la liste triee par l algorithme correspond a la liste triee telle que l enonce le donne, le test est reussi
        System.out.println("\nTest de l'exercice 2: \n Réussi?: "+ res.equals(liste)+"\n");*/

        //Test Exercice 3
        int choix = 1;
        while (choix != 0){
            choix = menu();
            switch (choix){

                case 1:
                    ajoutArtiste();
                    break;

                case 2:
                    ajoutAlbum();
                    break;

                case 3:
                    listerArtistes();
                    break;

                case 4:
                    listerAlbums();
                    break;

                case 5:
                    listerAlbumsPourArtiste();
                    break;

                case 6:
                    retirerArtiste();
                    break;

                case 7:
                    retirerAlbum();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Votre saisie n est pas valide, veuillez recommencer!");
            }
        }
    }

    //Exercice 1: Detection d un palindrome

    /**Renvoie true si la chaine de caractere envoye correspond a un palindrome
     *
     * @param s un mot dont on veut verifier si c est un palindrome
     * @return true si s est un palindrome et false sinon
     */

    //Fonction iterative de detection de palindrome
    public static boolean isPalindrome(String s){
        s = s.toLowerCase();
        int len=s.length()/2;
        //Pour chaque element du mot, on verifie que son element oppose est identique
        for (int i = 0; i <= len; i++){
            if (s.charAt(i) != s.charAt(s.length()-1-i)){
                //Si ce n est pas le cas au moins une fois, alors ce n est pas un palindrome
                return false;
            }
        }
        //Sinon c est un palindrome
        return true;
    }

    /**Test permettant de dire si s est un palindrome ou non
     *
     * @param s un mot dont on veut determiner si oui ou non c est un palindrome
     * @return true si s est un palindrome et false sinon
     */
    //Fonction recusrive de detection de palindrome
    public static boolean isPalindromeRec(String s){
        s = s.toLowerCase();
        //Si le mot est de taille 1 ou 0, c est de maniere evidente un palindrome
        if (s.length()<=1){
            return true;
        }
        //Si les deux extremites du mot ne sont pas les memes lettres, ce n est pas un palindrome
        if (s.charAt(0)!=s.charAt(s.length()-1)){
            return false;
        }
        //Sinon, on recommence l etude du mot, prive de ses deux caracteres aux extremites
        return isPalindromeRec(s.substring(1,s.length()-1));
    }

    //Exercice 2: Tri d une liste

    /**Trie la liste donnee selon le schema suivant
     * Le premier element de la liste est son minimum, le second element est son maximum, le troisieme est son second
     * minimum, le quatrieme est son second maximum...
     * @param liste une liste que l on desire trier
     */
    public static void triListe (ArrayList<Integer> liste){
        //A chaque tour de boucle, on cherche l element a placer a l index i
        for (int i = 0; i < liste.size(); i++){
            ArrayList<Integer> subListe = new ArrayList<>(liste.subList(i, liste.size()));
            Integer extremum;
            //Si l index i est pair, on cherche le minimum parmi les elements non tries
            if (i%2 == 0){
                extremum = Collections.min(subListe);
            }
            //Si l index i est impair, on cherche le maximum parmi les elements non tries
            else {
                extremum = Collections.max(subListe);
            }
            //On deplace l element a l index i, c est a dire le premier index non trie
            liste.remove(extremum);
            liste.add(i, extremum);
        }
    }

    //Exercice 3: Gestion d'une bibliotheque musicale

    /**Renvoie le choix d action de l utilisateur
     *
     * @return un entier (entre 0 et 7 si l utilisateur respecte les consignes affichees)
     */
    //Menu de l'application
    public static int menu () {
        System.out.println("Bienvenue dans votre bibliotheque musicale !");
        System.out.println("Tapez 1 pour ajouter un artiste a votre collection.");
        System.out.println("Tapez 2 pour ajouter un album a votre collection.");
        System.out.println("Tapez 3 pour lister tous les artistes presents dans votre collection.");
        System.out.println("Tapez 4 pour lister tous les albums presents dans votre collection.");
        System.out.println("Tapez 5 pour lister tous les albums d un artiste donne.");
        System.out.println("Tapez 6 pour retirer un artiste de votre collection.");
        System.out.println("Tapez 7 pour retirer un album de votre collection.");
        System.out.println("Tapez 0 pour quitter.");
        //On renvoie au traitement du programme  le choix de l utilisateur
        return Integer.parseInt(scanner.nextLine());
    }

    /**Permet d ajouter un artiste a la bibliotheque
     *
     */
    //Ajouter un artiste a sa bibliotheque
    public static void ajoutArtiste () {
        //On demande a l utilisateur l artiste qu il veut ajouter a sa base de donnee
        System.out.println("Veuillez saisir le nom de l artiste que vous voulez ajouter a votre collection.");
        String nomArtiste = scanner.nextLine();
        //On verifie qu il n est pas deja dans la base de donnee avant de l ajouter
        if (bibliotheque.containsKey(nomArtiste)){
            System.out.println("Cet artiste est deja dans votre bibliotheque!");
        }
        else {
            bibliotheque.put(nomArtiste, new ArrayList<>());
        }
    }

    /**Permet d ajouter un album a un artiste dans la bibliotheque
     *
     */
    //Ajouter l album d'un artiste
    public static void ajoutAlbum () {
        //On demande a l utilisateur de rentrer le nom de l artiste ainsi que le nom de l album qu il desire ajouter
        //a sa collection
        System.out.println("Veuillez saisir le nom de l'artiste de l'album que vous voulez ajouter a votre " +
                "collection.");
        String nomArtiste = scanner.nextLine();
        System.out.println("Veuillez saisir le nom de l album que vous voulez ajouter a votre collection.");
        String nomAlbum = scanner.nextLine();
        //On verifie si l artiste est deja dans la base de donnee
        if (bibliotheque.containsKey(nomArtiste)){
            //On verifie alors que l album n est pas present dans la base de donnee avant de l ajouter
            if (bibliotheque.get(nomArtiste).contains(nomAlbum)){
                System.out.println("Votre collection contient deja cet album!");
            }
            else  {
                ArrayList<String> listeAlbum = bibliotheque.get(nomArtiste);
                listeAlbum.add(nomAlbum);
                bibliotheque.put(nomArtiste, listeAlbum);
            }
        }
        //Si l artiste n appartient pas a la base de donnee, alors on ajoute l artiste et l album a la bibliotheque
        else{
            ArrayList<String> listeAlbum = new ArrayList<String>(Collections.singletonList(nomAlbum));
            bibliotheque.put(nomArtiste,listeAlbum);
        }
    }

    /**Permet d afficher les artistes de la bibliotheque dans l ordre alphabetique
     *
     */
    //Lister tous les artistes presents dans la bibliotheque
    public static void listerArtistes(){
        //On recupere la liste des artistes et on la place dans une liste (afin de posseder un ordre des elements
        Set<String> listeArtistes = bibliotheque.keySet();
        ArrayList<String> listeAlphabetique = new ArrayList<>(listeArtistes);
        //On applique la methode du tri a bulle pour trier le nom des artistes
        boolean estTrie = false;
        while (!estTrie){
            estTrie = true;
            for (int i = 0; i<listeAlphabetique.size()-1; i++){
                String art1 = listeAlphabetique.get(i);
                String art2 = listeAlphabetique.get(i+1);
                int j=0;
                while (art1.charAt(j) == art2.charAt(j)){
                    j++;
                }
                if (art1.charAt(j)>art2.charAt(j)){
                    estTrie = false;
                    Collections.swap(listeAlphabetique,i,i+1);
                }
            }
        }
        //On affiche la liste triee
        System.out.println(listeAlphabetique);
    }

    /**Permet d afficher tous les albums de la bibliotheque dans l ordre alphabetique
     *
     */
    //Lister tous les albums presents dans la bibliotheque
    public static void listerAlbums(){
        //On recupere la liste des artistes pour en recuperer la liste des albums
        Set<String> listeArtistes = bibliotheque.keySet();
        ArrayList<String> listeAlphabetique = new ArrayList<>();
        for (String artiste: listeArtistes){
            listeAlphabetique.addAll(bibliotheque.get(artiste));
        }
        //On applique la methode du tri a bulle pour trier le nom des albums
        boolean estTrie = false;
        while (!estTrie){
            estTrie = true;
            for (int i = 0; i<listeAlphabetique.size()-1; i++){
                String alb1 = listeAlphabetique.get(i);
                String alb2 = listeAlphabetique.get(i+1);
                int j=0;
                while (alb1.charAt(j) == alb2.charAt(j)){
                    j++;
                }
                if (alb1.charAt(j)>alb2.charAt(j)){
                    estTrie = false;
                    Collections.swap(listeAlphabetique,i,i+1);
                }
            }
        }
        //On affiche la liste triee
        System.out.println(listeAlphabetique);
    }

    /**Permet d afficher tous les albums dans la bibliotheque d un artiste en particulier
     *
     */
    //Lister tous les albums d un artiste
    public static void listerAlbumsPourArtiste(){
        //On recupere le nom de l artiste voulu par l utilisateur
        System.out.println("Veuillez entrer le nom de l artiste dont vous voulez voir les albums.");
        String artiste = scanner.nextLine();
        //On verifie que l artiste appartient a la bibliotheque
        if (!bibliotheque.containsKey(artiste)){
            System.out.println("Cet artiste n appartient pas a votre bibliotheque!");
        }
        else{
            //On recupere la liste des albums
            ArrayList<String> listeAlphabetique = new ArrayList<>();
            listeAlphabetique.addAll(bibliotheque.get(artiste));
            //On applique la methode du tri a bulle pour trier le nom des albums
            boolean estTrie = false;
            while (!estTrie){
                estTrie = true;
                for (int i = 0; i<listeAlphabetique.size()-1; i++){
                    String alb1 = listeAlphabetique.get(i);
                    String alb2 = listeAlphabetique.get(i+1);
                    int j=0;
                    while (alb1.charAt(j) == alb2.charAt(j)){
                        j++;
                    }
                    if (alb1.charAt(j)>alb2.charAt(j)){
                        estTrie = false;
                        Collections.swap(listeAlphabetique,i,i+1);
                    }
                }
            }
            //On affiche la liste triee
            System.out.println(listeAlphabetique);
        }
    }

    /**Permet de retirer un artiste ainsi que tous ses albums de la bibliotheque
     *
     */
    //Retirer un artiste de la bibliotheque
    public static void retirerArtiste () {
        //On recupere le nom de l artiste voulu par l utilisateur
        System.out.println("Veuillez entrer le nom de l artiste que vous voulez retirer de votre bibliotheque.");
        String artiste = scanner.nextLine();
        //On verifie que l artiste appartient a la bibliotheque
        if (!bibliotheque.containsKey(artiste)){
            System.out.println("Cet artiste n appartient pas a votre bibliotheque!");
        }
        else{
            //S il appartient a la bibliotheque, on le supprime ainsi que tous ses albums
            bibliotheque.remove(artiste);
        }
    }

    /**Permet de retirer un album d un artiste en particulier de la bibliotheque
     *
     */
    //Retirer un album de la bibliotheque
    public static void retirerAlbum () {
        //On recupere le nom de l artiste voulu par l utilisateur ainsi que le nom de l album a supprimer
        System.out.println("Veuillez entrer le nom de l artiste qui a fait l album que vous voulez supprimer.");
        String artiste = scanner.nextLine();
        System.out.println("Veuillez entrer le nom de l album que vous voulez retirer de votre bibliotheque.");
        String album = scanner.nextLine();
        //On verifie que l artiste appartient a la bibliotheque
        if (!bibliotheque.containsKey(artiste)) {
            System.out.println("Cet artiste n appartient pas a votre bibliotheque!");
        }
        //On verifie ensuite que l album appartient a la bibliotheque
        else{
            if (!bibliotheque.get(artiste).contains(album)){
                System.out.println("Cet album n appartient pas a votre bibliotheque!");
            }
            else {
                //On supprime alors l album de la base de donnee
                ArrayList<String> listeAlbum = bibliotheque.get(artiste);
                listeAlbum.remove(album);
                bibliotheque.put(artiste, listeAlbum);
            }
        }
    }
}
