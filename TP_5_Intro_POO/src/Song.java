public class Song {
    private String title;
    private int duration;

    Song(){}

    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString() {
        return this.title +
                " (" + this.convertDuration() +
                ')';
    }

    public String convertDuration(){
        return String.format("%d:%d", this.duration / 60, this.duration % 60);
    }
}
