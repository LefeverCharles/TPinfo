import java.util.ArrayList;

public class Artist implements Comparable{
    private String name;
    private boolean isActive;
    private ArrayList<Album> albums=new ArrayList<>();

    Artist(){}

    Artist(String name, boolean isActive){
        this.name=name;
        this.isActive=isActive;
    }

    public ArrayList<Album> getAlbums() {
        return this.albums;
    }

    public String getName() {
        return this.name;
    }

    public void addAlbum(Album album){
        if (getAlbumByName(album.getTitle())==null) {
            this.albums.add(album);
        }
    }

    public void removeAlbum(String albumName){
        if (getAlbumByName(albumName)!=null) {
            this.albums.remove(this.getAlbumByName(albumName));
        }
    }

    private Album getAlbumByName(String albumName){
        for(Album album: this.albums) {
            if (albumName.equals(album.getTitle())) {
                return album;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Artist other = (Artist) o;
        return this.getName().compareTo(other.getName());
    }
}
