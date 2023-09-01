/**
 *
 * @author oytunemreozmel
 */
public class Song {

    String songName;
    String artist;
    String genre;
    int releaseYear;
    int songID;
    Song left;
    Song right;

    public Song(String songName, String artist, int songID, String genre, int releaseYear) {
        this.songName = songName;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.songID = songID;
        left = null;
        right = null;
    }

    public Song() {
        left = null;
        right = null;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getSongID() {
        return songID;
    }
    
   
   
    
    public int songObjKey(){
        int key;
        return key = Song.this.hashCode()+  31 * 17 ;
    }
    
    
    
    public int artistKey(String artist){
        int key;
        return key = artist.hashCode() * 31 + 17;
    }

    
    public int SongKey(String songName){
        int key;
        return key = songName.hashCode() * 31 + 17;
    }
    
     public int GenreKey(String genre){
        int key;
        return key = genre.hashCode() * 31 + 17;
    }


    public String toString() {
        return songName + ";" + artist + ";" + songID + ";" + genre + ";" + releaseYear;
  

}
}