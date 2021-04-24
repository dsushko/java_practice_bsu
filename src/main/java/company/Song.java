package company;

import java.io.*;
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
        int fileId;
        try (Scanner scanner = new Scanner (new File ("c:/testlab3/test_lab3/id.db"))){
            while(scanner.hasNext() && scanner.hasNextInt()){
                fileId = scanner.nextInt();
                this.id = fileId;
            }
        }
        try(PrintWriter fout = new PrintWriter("c:/testlab3/test_lab3/id.db")){
            fout.println( Integer.toString(id+1));
        }

    }
    public Song(int id, String author, String name, String duration, String albumName, ListeningStats listeningStats, String key) throws IOException {
        this.author = author;
        this.name = name;
        this.duration = duration;
        this.albumName = albumName;
        this.listeningStats = listeningStats;
        this.key = key;
        this.id = id;
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

    public int getId(){
        return id;
    }
    void setId(int id){
        this.id = id;
    }
}