
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author oytunemreozmel
 */
public class main {

    static BST genreBst = new BST();
    static BST songNamesBst = new BST();
    static BST artistBst = new BST();
    static BST idBst = new BST();
    static BST releaseYearBst = new BST();
    static BST allSongBst = new BST();
    static HashMap<Object, BST> allSongHashMap = new HashMap<>();
    static HashMap<String, BST> songNameHashMap = new HashMap<>();
    static HashMap<String, BST> genreHashMap = new HashMap<>();
    static HashMap<String, BST> artistHashMap = new HashMap<>();
    static HashMap<String, Integer> artist2IDHashMap = new HashMap<>();
    static HashMap<Integer, BST> idHashMap = new HashMap<>();
    static HashMap<Integer, String> id2SongHashMap = new HashMap<>();
    static HashMap<String, String> genre2SongshHashMap = new HashMap<>();
    static HashMap<String, Integer> artist2SongshHashMap = new HashMap<>();
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException {

        recordSystem();

    }

    public static void File() {
        try {
            File file = new File("/Users/oytunemreozmel/NetBeansProjects/COMP2112_Summer_Project2/src/SongName.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] songInfo = line.split(";");

                if (songInfo.length == 5) {
                    String songName = songInfo[0];
                    String artist = songInfo[1];

                    int songID = Integer.parseInt(songInfo[2]);
                    String genre = songInfo[3];
                    int releaseYear = Integer.parseInt(songInfo[4].trim());

                    Song song = new Song(songName, artist, songID, genre, releaseYear);

                    int songClassHash = song.songObjKey();
                    int songNameHash = song.SongKey(songName);
                    int artistHash = song.artistKey(artist);
                    int genreHash = song.GenreKey(genre);

                    allSongBst.insert(songClassHash);
                    songNamesBst.insert(songNameHash);
                    artistBst.insert(artistHash);
                    genreBst.insert(genreHash);
                    releaseYearBst.insert(releaseYear);
                    idBst.insert(songID);

                    if (genre2SongshHashMap.containsKey(genre)) {
                        String existingSongs = genre2SongshHashMap.get(genre);
                        existingSongs += ";" + songName;
                        genre2SongshHashMap.put(genre, existingSongs);
                    } else {
                        genre2SongshHashMap.put(genre, songName);
                    }

                    allSongHashMap.put(song, allSongBst);
                    songNameHashMap.put(songName, songNamesBst);
                    genreHashMap.put(genre, genreBst);
                    artistHashMap.put(artist, artistBst);
                    idHashMap.put(songID, idBst);
                    id2SongHashMap.put(songID, songName);
                    artist2IDHashMap.put(artist, songID);
                    artist2SongshHashMap.put(artist, songID);

                }

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    public static void welcome() {
        System.out.println("------------ Welcome to Binary Search Tree Map based Music Song record system ------------");

        System.out.println("In this system you allowed to do insert ,search and remove musical records of the system");

        System.out.println("Here we have list of all song in the record system");

        System.out.println("=========================================================");
    }

    public static void insert() {

        System.out.println("Please insert data of the song you want to add format should like"
                + " this (Song name;Artist;ID;Genre;Year) ");

        System.out.print("Name of the song: ");
        String  nameOfTheSong = scn.nextLine();
        System.out.print("Artist of the song : ");
        String artistOfTheSong = scn.nextLine();      
        System.out.print("ID of the song: ");
        int songID = scn.nextInt();
        System.out.print("Genre of the song: ");
        String genreOfTheSong = scn.next();

        System.out.print("Release year of the song: ");
        int releaseYearOfTheSong = scn.nextInt();

        scn.nextLine();

        // creating song object
        Song song = new Song(nameOfTheSong, artistOfTheSong, songID, genreOfTheSong, releaseYearOfTheSong);

        // creating hashcode
        int songClassHash = song.songObjKey();
        int songNameHash = song.SongKey(genreOfTheSong);
        int artistHash = song.artistKey(artistOfTheSong);
        int genreHash = song.GenreKey(genreOfTheSong);

        // insert tree
        allSongBst.insert(songClassHash);
        songNamesBst.insert(songNameHash);
        artistBst.insert(artistHash);
        idBst.insert(songID);
        genreBst.insert(genreHash);
        releaseYearBst.insert(releaseYearOfTheSong);

        // insert hashmap
        allSongHashMap.put(song, allSongBst);
        songNameHashMap.put(nameOfTheSong, songNamesBst);
        genreHashMap.put(genreOfTheSong, genreBst);
        artistHashMap.put(artistOfTheSong, artistBst);
        genre2SongshHashMap.put(genreOfTheSong, nameOfTheSong);
        idHashMap.put(songID, idBst);
        id2SongHashMap.put(songID, nameOfTheSong);
        artist2SongshHashMap.put(artistOfTheSong, songID);

        System.out.println("------------------------------------");
        System.out.println("The songs are : ");
        for (Map.Entry<String, BST> entry : songNameHashMap.entrySet()) {
            Object key = entry.getKey();
            System.out.println(key);

        }
        System.out.println("------------------------------------");

    }

    public static void search() {
        // search case 

        System.out.println("You can search the song by name of the song, artist who sang it "
                + ", you can type genre of the song , or you can search by id of the song :)\n"
                + " 1) Search by song name \n 2) Search by artist \n 3) Search by id  \n 4) Search by id intervals \n 5) Search by genre");
        System.out.print("Please select an option for searching: ");
        int option4case2 = scn.nextInt();

        switch (option4case2) {
            case 1:
                // search by song name
                System.out.print("Name of the song : ");
                String searchNameOfTheSong = scn.next();
                scn.useDelimiter("\n");
                if (songNameHashMap.containsKey(searchNameOfTheSong)) {
                    System.out.println("The song is in the system: " + searchNameOfTheSong);
                } else {
                    System.out.println("The song is not in the system");
                }

                break;

            case 2:
                // search by artist 

                System.out.print("Artist of the song : ");

                String SearchartistOfTheSong = scn.next();
                if (artistHashMap.containsKey(SearchartistOfTheSong)) {
                    System.out.println("The artist is in the system: " + SearchartistOfTheSong);
                    break;
                } else {
                    System.out.println("The artist is not in the system - Do you want to add song to the system (Yes/No)");
                }

                String choice2 = scn.next();
                if (choice2.equals("Yes") || choice2.equals("yes") || choice2.equals("Y") || choice2.equals("y")) {
                    insert();
                }

                break;

            case 3:
                // search by id 
                System.out.print("ID of the song: ");
                int SearchsongID = scn.nextInt();
                if (id2SongHashMap.containsKey(SearchsongID)) {
                    Song id = new Song();
                    id.songName = id2SongHashMap.get(SearchsongID);

                    System.out.println("The song id is  " + SearchsongID + " song is " + id.songName);
                } else {
                    System.out.println("Song is not on the list - Do you want to add song to the system (Yes/No)");

                    String choice3 = scn.next();
                    if (choice3.equals("Yes") || choice3.equals("yes") || choice3.equals("Y") || choice3.equals("y")) {
                        insert();
                    }

                    System.out.println("The ID's in the list are ");
                    for (Map.Entry<Integer, String> entry : id2SongHashMap.entrySet()) {
                        System.out.println("The ID's:" + entry.getKey() + " --------> " + entry.getValue());
                    }
                }

                break;

            case 4:
                // search by id - intervals 
                System.out.println("Enter upper and lower bounds of the values you're looking for (Only IDs): ");

                int max = 0;
                int min = Integer.MAX_VALUE;

                for (Map.Entry<Integer, BST> entry : idHashMap.entrySet()) {

                    if (entry.getKey() > max) {
                        max = entry.getKey();
                    }
                }

                for (Map.Entry<Integer, BST> entry : idHashMap.entrySet()) {
                    int value = entry.getKey();
                    if (value < min) {
                        min = value;
                    }
                }

                System.out.println("The lower and upper bounds in the list are ");
                System.out.println("lower bound: " + min);
                System.out.println("Upper bound: " + max);
                System.out.println("Enter the lower and upper bound");
                System.out.print("Upper bound: ");
                int upperBound = scn.nextInt();
                System.out.print("Lower bound: ");
                int lowerBound = scn.nextInt();

                for (Map.Entry<Integer, String> entry : id2SongHashMap.entrySet()) {
                    int key = entry.getKey();
                    if (key >= lowerBound && key <= upperBound) {
                        String songs = entry.getValue();
                        System.out.println("ID of the song: " + key + " -----> " + songs);

                    }
                }

                System.out.println("If you cannot find song between ID's you looked for you can add to the system.");
                System.out.println("Do you want to add (Yes / No)");

                String choice4 = scn.next();
                if (choice4.equals("Yes") || choice4.equals("yes") || choice4.equals("Y") || choice4.equals("y")) {
                    insert();
                }

                break;

            case 5:
                System.out.print("Genre of the song: ");
                String SearchgenreOfTheSong = scn.next();
                scn.useDelimiter("\n");
                System.out.println("The songs are in this " + SearchgenreOfTheSong);

                if (!genre2SongshHashMap.containsValue(SearchgenreOfTheSong)) {
                    System.out.println("The genre is you looking for is not found do you want to insert the song you searched for ? (Yes/No)");
                    String choice5 = scn.next();
                    if (choice5.equals("Yes") || choice5.equals("yes") || choice5.equals("Y") || choice5.equals("y")) {
                        insert();
                    }
                }

                for (Map.Entry<String, String> entry : genre2SongshHashMap.entrySet()) {
                    String key = entry.getKey();
                    if (key.equals(SearchgenreOfTheSong)) {
                        String songs = entry.getValue();
                        String[] songArray = songs.split(";");
                        for (String genreArr : songArray) {
                            System.out.println(genreArr);
                        }
                    }
                }

                break;

            default:
                System.out.println("Invalid error ");
                throw new AssertionError();

        }

    }

    public static void remove() {

        System.out.print("Enter ID of the song for removing the song int system (you can enter only the ID number): ");
        int removeID = scn.nextInt();
        if (idHashMap.containsKey(removeID)) {
            System.out.println("Song with " + removeID + " is removed from system");
            idHashMap.remove(removeID);
            id2SongHashMap.remove(removeID);

            System.out.println("---------------------------------------------------------");
            for (Map.Entry<Integer, String> entry : id2SongHashMap.entrySet()) {
                System.out.println("The ID's:" + entry.getKey() + " --------> " + entry.getValue());
            }

        } else {

            System.out.println("Song is not on the list ");
            System.out.println("The ID's in the list are ");
            for (Map.Entry<Integer, String> entry : id2SongHashMap.entrySet()) {
                System.out.println("The ID's:" + entry.getKey() + " --------> " + entry.getValue());
            }

        }
    }

    public static void recordSystem() {

        welcome();
        File();

        while (true) {
            System.out.println("============================================================");
            System.out.print("Please select an operation:"
                    + "\n 1) Insert song to the system \n 2) Search song from system \n 3) remove song from the system\n");
            Scanner scn = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Option : ");
            int option = scn.nextInt();

            switch (option) {
                case 1:
                    insert();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    System.exit(0);
                    break;

            }

        }
    }
}
