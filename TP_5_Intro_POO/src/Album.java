import java.util.ArrayList;

public class Album {
    private String title;
    private int year;
    private ArrayList<Song> songs;

    Album() {}

    Album(String title, int year, ArrayList<Song> songs){
        this.title=title;
        this.year=year;
        this.songs=songs;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        String res = "";
        res= res+this.getTitle()+"\n";
        int i = 1;
        for (Song song: this.songs){
            res= res +"\t" + i + " - " + song.getTitle()+ song.convertDuration()+ "\n";
        }
        return res;
    }
}
