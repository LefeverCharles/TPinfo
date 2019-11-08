
import java.util.Scanner;

public class Main {
    public static String[] messages = {
            "bonjour le monde",
            "comme un lundi",
            "pas de bras pas de chocolat",
            "vous ne passerez pas",
            "que la force soit avec toi",
            "avec la pate vous faites une crepe et puis vous mettez du sucre dessus",
            "the quick brown fox jumps over the lazy dog"
    };

    public static String[] messagesEncodes = {
            "yrkgrxo ih prkah",
            "frpph xk ixkal",
            "mdv ah yodv mdv ah ferfridq",
            "srxv kh mdvvhohw mdv",
            "txh id crofh vrlq dshf qrl",
            "dshf id mdqh srxv cdlqhv xkh fohmh hq mxlv srxv phqqhw ax vxfoh ahvvxv",
            "qeh txlfn yorzk cru gxpmv rsho qeh idwb arj"
    };

    public static char[][] plateau;
    public static Scanner scanner = new Scanner(System.in);
    public static char joueur1 = 'O';
    public static char joueur2 = 'X';
    public static boolean estJoueur1 = true;

    public static void main(String[] args) {
        boolean reussite = true;
        for(int i = 0; i<7; i++){
            if (!messageEncodeur(messages[i]).equals(messagesEncodes[i])){
                reussite = false;
            }
        }
        System.out.println(reussite);
        morpion();
    }

    /**
     * Cette fonction prend un message comme paramètre afin de lui appliquer
     * l'algorithme d'encodage proposé dans le sujet. Le message encodé est
     * ensuite retourné par cette fonction.
     *
     * @param message	Le message à encoder
     * @return		Le message encodé avec l'algorithme fourni dans le sujet
     */
    public static String messageEncodeur(String message) {
        int tailleMessage = message.length();
        String encode = new String();
        for (int i = 0; i<tailleMessage; i++) {
            char lettre = message.charAt(i);
            if (lettre != ' ') {
                //On vérifie que l'élément n'est pas un espace avant de le modifier
                int valeur = (int) lettre;
                if (valeur % 2 == 0) {
                    //On applique la transformation si l'élément est représenté par un nombre pair
                    valeur -= 3;
                    if (valeur < 97){
                        //On vérifie qu'il reste dans les éléments de l'alphabet et on décale au besoin
                        valeur += 26;
                    }
                }
                else {
                    //Même principe si l'élément est représenté par un nombre impair
                    valeur += 3;
                    if (valeur > 122) {
                        valeur -= 26;
                    }
                }
                lettre = (char) valeur;
            }
            encode += lettre;
        }
        return encode;
    }


    /**
     * Cette fonction va lancer le jeu du morpion.
     * Attention, lisez bien la Section 2.2.3 de l'énoncé qui décrit la logique
     * du jeu. C'est cette logique que vous devrez implémenter ici.
     */
    public static void morpion() {
        int choix = 1;
        while (choix == 1) {
            //Menu (succint) du morpion
            System.out.println("Voulez-vous jouer au morpion?\nSi oui, tapez 1, sinon tapez tout autre nombre entier.");
            choix = scanner.nextInt();
            if (choix == 1 ) {
                //Si les joueurs veulent jouer, on initialise la partie
                initialiserPlateau();
                while (true){
                    //Au début de chaque tour, on affiche le plateau
                    afficherPlateau();
                    int[] saisie = new int[2];
                    do {
                        //Tant que la saisie du joueur n'est pas valide, on continue à lui demander des coordonnées
                        saisie=saisieJoueur()
                    } while (!saisieValide(saisie[0], saisie [1]) );
                    //On met le plateau à jour
                    updatePlateau(saisie);
                    if (! finDuJeu()){
                        //On vérifie si la partie s'arrête ou on procède au changement de tour
                        if (estJoueur1){
                            estJoueur1= false;
                        }
                        else {
                            estJoueur1 = true;
                        }
                    }
                    else {
                        //Si la partie est finie, on sort de la boucle
                        break;
                    }
                }
            }
        }
    }

    /**
     * Cette fonction permet d'initialiser le plateau avec des espaces (' ')
     */
    public static void initialiserPlateau() {
        plateau = new char[][] {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    }

    /**
     * Cette fonction permet d'afficher le contenu du plateau en console.
     * Attention on voudra aussi afficher un cadre autour du plateau pour
     * en améliorer sa lisibilité.
     */
    public static void afficherPlateau() {
        // Exemple d'affichage avec les indices de ligne et colonne :
        //   012
        //  +---+
        // 0|O  |
        // 1|XO |
        // 2| XO|
        //  +---+
        String ligne1 = new String();
        String ligne2 = new String();
        String ligne3 = new String();
        //On compose des chaînes de caractères contenant les caractères des trois lignes afin de correspondre
        //à l'affichage demandé
        for (int i = 0; i < 3; i++){
            ligne1 += plateau[0][i];
            ligne2 += plateau[1][i];
            ligne3 += plateau[2][i];
        }
        System.out.println("   012 ");
        System.out.println("  +---+");
        System.out.println(" 0|"+ ligne1 +"|");
        System.out.println(" 1|"+ ligne2 +"|");
        System.out.println(" 2|"+ ligne3 +"|");
        System.out.println("  +---+");
    }

    /**
     * Cette fonction demande au joueur en cours de saisir deux entiers. Le
     * premier entier représente l'indice de ligne du plateau et le second
     * entier représente l'indice de colonne du plateau.
     * Cette fonction va faire répéter au joueur la saisie de ces deux indices
     * tant que celle-ci n'est pas valide. On va donc faire appel à la fonction
     * saisieValide() ici.
     * @return	Un tableau d'entiers de taille 2, où le premier élément
     *		correspond à l'indice de ligne et le second élément à 
     *		l'indice de colonne
     */
    public static int[] saisieJoueur() {
        //On annonce quel joueur doit jouer maintenant
        if (estJoueur1){
            System.out.println("Joueur 1:");
        }
        else {
            System.out.println("Joueur 2:");
        }
        int [] saisie = new int [2];
        //On lui demande les coordonnées de son prochain coup
        System.out.println("Choisissez la ligne sur laquelle vous allez jouer");
        saisie [0] = scanner.nextInt();
        System.out.println("Choisissez la colonne sur laquelle vous allez jouer");
        saisie [1] = scanner.nextInt();
        return saisie;
    }

    /**
     * Cette fonction vérifie que la saisie du joueur est valide. Il y a trois
     * vérifications à faire :
     *   1. Vérifier que : 0 <= indiceLigne <= 2
     *   2. Vérifier que : 0 <= indiceColonne <= 2
     *   3. plateau[indiceLigne][indiceColonne] n'est pas déjà occupée
     *
     * @param indiceLigne   L'indice de ligne saisi par le joueur en cours
     * @param indiceColonne L'indice de colonne saisi par le joueur en cours
     * @return		    Vrai si la saisie est valide, faux sinon
     */
    public static boolean saisieValide(int indiceLigne, int indiceColonne) {
        //On vérifie que la ligne indiquée appartient au cadre du jeu
        if (indiceLigne < 3 && indiceLigne >= 0){
            //On vérifie que la colonne indiquée appartient au cadre du jeu
            if (indiceColonne < 3 && indiceColonne >= 0){
                //On vérifie que la case indiquée est libre
                if (plateau [indiceLigne] [indiceColonne] == ' '){
                    return true;
                }
            }
        }
        //Si au moins un des tests est faux, la saisie n'est pas valide
        System.out.println("Votre saisie n'est pas valide. Veuillez recommencer.");
        return false;
    }

    /**
     * Cette fonction met à jour le contenu du plateau après que le joueur ait
     * saisi son coup. Cette fonction fera appel à la variable estJoueur1 pour
     * déterminer quel symbole ajouter.
     *
     * @param saisie	Tableau d'entiers de taille 2 qui représente la saisie
     *			du joueur
     */
    public static void updatePlateau(int[] saisie) {
        if (estJoueur1){
            plateau[saisie[0]][saisie[1]]= joueur1;
        }
        else{
            plateau[saisie[0]][saisie[1]]= joueur2;
        }
    }

    /**
     * Cette fonction détermine si le jeu est fini ou non. Il existe deux façons
     * pour que le jeu se termine :
     *	- Le plateau est rempli ;
     *	- Un joueur a créé un aligné trois pions identiques.
     *
     * @return	Vrai si le jeu est terminé, faux sinon.
     */
    public static boolean finDuJeu() {
        //On vérifie d'abord si quelqu'un a gagné et on précise qui gagne la partie
        if (alignementGagnant()){
            if (estJoueur1){
                System.out.println("Joueur 1 gagne cette partie.");
            }
            else {
                System.out.println("Joueur 2 gagne cette partie.");
            }
            return true;
        }
        //On vérifie le cas de match nul
        if (plateauEstComplet()) {
            System.out.println("Match nul, personne ne gagne cette partie.");
            return true;
        }
        //Si les tests précédents ont échoués, on peut continuer la partie
        return false;
    }

    /**
     * Cette fonction détermine si le plateau est rempli ou non.
     *
     * @return	Vrai si le plateau est complet, faux sinon.
     */
    public static boolean plateauEstComplet() {
        //On prélève un à un chaque élément du plateau et on vérifie s'ils correspondent à un espace
        //Si au moins une case contient un caractère espace, il reste des places libres sur le plateau
        //Si aucune case ne contient un caractère espace, alors le plateau est complet
        for (char[] x : plateau) {
            for (char y : x) {
                if (y == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cette fonction détermine si un joueur a crée un alignement de trois pions
     * identiques d'une des trois manières suivantes :
     *	- En créant un alignement sur une ligne ;
     *	- En créant un alignement sur une colonne ;
     *	- En créant un alignement sur une diagonale.
     *
     * @return	Vrai si un joueur a créé un alignement gagnant, faux sinon.
     */
    public static boolean alignementGagnant() {
        //Vérification des lignes
        for (int i = 0; i < 3 ; i++){
            if (plateau[i][0] == plateau[i][1] && plateau[i][0] == plateau[i][2] && plateau[i][0]!= ' '){
                return true;
            }
        }
        //Vérification des colonnes
        for (int i = 0; i < 3 ; i++){
            if (plateau[0][i] == plateau[1][i] && plateau[0][i] == plateau[2][i] && plateau[0][i]!= ' '){
                return true;
            }
        }
        //Vérification des diagonales
        if (plateau[0][0] == plateau[1][1] && plateau[0][0] == plateau[2][2] && plateau[0][0]!=' '){
            return true;
        }
        if (plateau[0][2] == plateau[1][1] && plateau[0][2] == plateau[2][0] && plateau[0][2]!=' '){
            return true;
        }
        //Si tous les tests ont échoués, il n'y a pas d'alignement gagnant
        return false;
    }
}