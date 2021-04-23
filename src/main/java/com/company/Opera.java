package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Opera extends Song {
    protected int releaseCentury;
    protected int orchestraCount;
    protected String vocalVoiceTypeRequired;
    public Opera(){
        super();
    }
    public Opera(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key,
                    int releaseCentury, int orchestraCount, String vocalVoiceTypeRequired){
        super(author,name,duration,albumName,listeningStats,key);
        this.releaseCentury = releaseCentury;
        this.orchestraCount = orchestraCount;
        this.vocalVoiceTypeRequired = vocalVoiceTypeRequired;
    }

    public int getReleaseCentury() {
        return releaseCentury;
    }

    public void setReleaseCentury(int releaseCentury) {
        this.releaseCentury = releaseCentury;
    }

    public int getOrchestraCount() {
        return orchestraCount;
    }

    public void setOrchestraCount(int orchestraCount) {
        this.orchestraCount = orchestraCount;
    }

    public String getVocalVoiceTypeRequired() {
        return vocalVoiceTypeRequired;
    }

    public void setVocalVoiceTypeRequired(String vocalVoiceTypeRequired) {
        this.vocalVoiceTypeRequired = vocalVoiceTypeRequired;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Release century: " + releaseCentury + '\n' +
                "Orchestra count: " + orchestraCount + '\n' +
                "Vocal voice type required: " + vocalVoiceTypeRequired + '\n';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + releaseCentury + "," + orchestraCount + ",\"" + vocalVoiceTypeRequired + "\"";
    }

    public static Vector<Song> readCSV(String filename) throws FileNotFoundException {
        Vector<Song> result = new Vector<>();
        File ifile = new File(filename);
        Scanner input = new Scanner(ifile);
        String author;
        String name;
        String duration;
        String albumName;
        String key;
        ListeningStats listeningStats;
        int releaseCentury;
        int orchestraCount;
        String vocalVoiceTypeRequired;

        String valueBuffer = "";

        while(input.hasNextLine()){
            input.useDelimiter(",");

            author = input.next();
            author = author.substring(1, author.length() - 1);

            name = input.next();
            name = name.substring(1, name.length() - 1);

            duration = input.next();
            duration = duration.substring(1, duration.length() - 1);

            albumName = input.next();
            albumName = albumName.substring(1, albumName.length() - 1);

            key = input.next();
            key = key.substring(1, key.length() - 1);

            input.useDelimiter("\n");
            listeningStats = ListeningStats.convertCSVToStats(input.nextLine());
            input.useDelimiter(",");
            valueBuffer = input.next();
            releaseCentury = Integer.parseInt(valueBuffer);

            valueBuffer = input.next();
            orchestraCount = Integer.parseInt(valueBuffer);

            vocalVoiceTypeRequired = input.next();
            vocalVoiceTypeRequired = vocalVoiceTypeRequired.substring(1, vocalVoiceTypeRequired.length() - 1);

            input.useDelimiter("\n");
            result.add(new Opera(author,name,duration,albumName,listeningStats,key, releaseCentury, orchestraCount, vocalVoiceTypeRequired));
        }

        return result;
    }

}
