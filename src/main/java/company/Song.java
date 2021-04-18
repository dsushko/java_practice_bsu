package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Song {
    protected String duration;
    protected String author;
    protected String albumName;
    protected String name;
    protected ListeningStats listeningStats;
    protected String key;
    protected int id;
    public Song(){

    };
    public Song(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key) throws IOException {
        this.author = author;
        this.name = name;
        this.duration = duration;
        this.albumName = albumName;
        this.listeningStats = listeningStats;
        this.key = key;
        File iddb = new File("db_id.txt");
        if(iddb.exists()) {
            Scanner read = new Scanner("db_id.txt");
            id = read.nextInt();
            FileWriter writer = new FileWriter("db_id.txt", false);
            writer.write(id+1);
        } else {
            id = 1;
            iddb.createNewFile();
            FileWriter writer = new FileWriter("db_id.txt");
            writer.write("1");
        }
    }

    @Override
    public String toString() {
        return  "Author: " + author + '\n' +
                "Name: " + name + '\n' +
                "Duration: " + duration + '\n' +
                "Album name: " + albumName + '\n' +
                "Key: " + key + "\n" +
                "Listening stats: {\n" + listeningStats.toString() + "}";
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        if(duration.matches("^\\d{1,3}:[0-5]\\d$"))
            this.duration = duration;
        else
            this.duration = null;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListeningStats getListeningStats() {
        return listeningStats;
    }

    public void setListeningStats(ListeningStats listeningStats) {
        this.listeningStats = listeningStats;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        if(key.matches("^([A-H]|[a-h])(#|b|)(m|)$"))
            this.key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
        else
            this.key = null;
    }
}

