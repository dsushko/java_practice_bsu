package com.company;

import java.io.IOException;

public class RapSong extends Song {
    protected int bpm;
    protected String performerRace;
    protected boolean performerAlive;
    protected String performerDeathType;
    public RapSong(){
        super();
    }
    public RapSong(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key, int bpm, String performerRace, boolean PerformerAlive, String performerDeathType) throws IOException {
        super(author, name, duration, albumName, listeningStats, key);
        this.bpm = bpm;
        this.performerRace = performerRace;
        this.performerAlive = PerformerAlive;
        this.performerDeathType = performerDeathType;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "BPM: " + bpm + '\n' +
                "Performer race: " + performerRace + '\n' +
                "Is performer alive: " + performerAlive + '\n' +
                "Performer death type: " + performerDeathType + '\n';
    }


    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getPerformerRace() {
        return performerRace;
    }

    public void setPerformerRace(String performerRace) {
        this.performerRace = performerRace;
    }

    public boolean getPerformerAlive() {
        return performerAlive;
    }

    public void setPerformerAlive(boolean performerAlive) {
        this.performerAlive = performerAlive;
    }

    public String getPerformerDeathType() {
        return performerDeathType;
    }

    public void setPerformerDeathType(String performerDeathType) {
        this.performerDeathType = performerDeathType;
    }

}
