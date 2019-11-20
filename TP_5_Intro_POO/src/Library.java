import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Library {
    private TreeSet<Artist> lib;

    public void run (Scanner scanner) {
        TreeSet<Artist> lib = new TreeSet<>();
        while (true){
            this.displayMenu(scanner);
        }
    }

    private void displayMenu(Scanner scanner) {
        System.out.println("Bienvenue sur votre bibliotheque musicale!");
        System.out.println("Tapez 1 pour ajouter un artiste a votre collection.");
        System.out.println("Tapez 2 pour supprimer un artiste de votre collection.");
        System.out.println("Tapez 3 pour lister tous les artistes.");
        System.out.println("Tapez 4 pour ajouter un album a un artiste.");
        System.out.println("Tapez 5 pour retirer un album a un artiste.");
        System.out.println("Tapez 6 pour lister tous les albums pour un artiste donné.");
        int choix = Integer.parseInt(scanner.nextLine());
        switch(choix){
            case 1:
                this.addArtist(scanner);
                break;
            case 2:
                this.removeArtist(scanner);
                break;
            case 3:
                this.listArtists();
                break;
            case 4:
                this.addAlbum(scanner);
                break;
            case 5:
                this.removeAlbum(scanner);
                break;
            case 6:
                this.listAlbumsForArtist(scanner);
        }
    }

    private void addArtist(Scanner scanner){
        boolean adding = true;
        System.out.println("Tapez le nom de l artiste que vous voulez ajouter:");
        String artistName = scanner.nextLine();
        System.out.println("Est-il encore actif?(y/n)");
        boolean isActive;
        char active = scanner.nextLine().charAt(0);
        if (active == 'y'){
            isActive= true;
        }
        else{
            isActive= false;
        }
        for (Artist artist: lib){
            if (artistName.equals(artist.getName())){
                adding=false;
            }
        }
        if (adding){
            lib.add(new Artist(artistName,isActive));
        }
    }

    private void removeArtist(Scanner scanner){
        String artistName = scanner.nextLine();
        for (Artist artist: lib) {
            if (artistName.equals(artist.getName())) {
                lib.remove(artist);
            }
        }
    }

    private void listArtists(){
        Iterator<Artist> liste = lib.iterator();
        for (Artist artist: lib){
            System.out.println(artist);
        }
    }

    private void addAlbum(Scanner scanner){
        ArrayList<Song> songs = new ArrayList<>();
        System.out.println("Tapez le nom de l'artiste à qui vous voulez ajouter un album.");
        String nameArtist = scanner.nextLine();
        Artist artist= new Artist();
        boolean isPresent =false;
        for (Artist artistTest: lib){
            if(nameArtist==artistTest.getName()){
                isPresent=true;
                artist= artistTest;
            }
        }
        if (isPresent){
            System.out.println("Tapez le nom de l'album que vous voulez ajouter.");
            String titleAlbum = scanner.nextLine();
            System.out.println("Tapez l'année de sortie de l'album.");
            int year = Integer.parseInt(scanner.nextLine());
            boolean again;
            do{
                System.out.println("Tapez le nom d'une chanson de l'album.");
                String songName = scanner.nextLine();
                System.out.println("Tapez la durée de la chanson.");
                int duree = Integer.parseInt(scanner.nextLine());
                System.out.println("Voulez vous encore ajouter des chansons?(y/n)");
                Song song = new Song(songName,duree);
                songs.add(song);
                char choice = scanner.nextLine().charAt(0);
                if (choice == 'y'){
                    again= true;
                }
                else{
                    again= false;
                }
            }while(again);
            artist.addAlbum(new Album(titleAlbum,year,songs));
        }
    }

    private void removeAlbum(Scanner scanner){
        System.out.println("Tapez le nom de l'artiste à qui vous voulez retirer un album.");
        String nameArtist = scanner.nextLine();
        Artist artist=new Artist();
        boolean isPresent =false;
        for (Artist artistTest: lib){
            if(nameArtist==artistTest.getName()){
                isPresent=true;
                artist= artistTest;
            }
        }
        if (isPresent) {
            System.out.println("Tapez le nom de l'album que vous voulez retirer.");
            String titleAlbum = scanner.nextLine();
            artist.removeAlbum(titleAlbum);
        }
    }

    private void listAlbumsForArtist(Scanner scanner){
        System.out.println("Tapez le nom de l'artiste dont vous voulez afficher les albums.");
        String nameArtist = scanner.nextLine();
        Artist artist=new Artist();
        boolean isPresent =false;
        for (Artist artistTest: lib){
            if(nameArtist==artistTest.getName()){
                isPresent=true;
                artist= artistTest;
            }
        }
        if (isPresent) {
            for(Album album : artist.getAlbums()){
                System.out.println(album.toString());
            }
        }
    }
}
