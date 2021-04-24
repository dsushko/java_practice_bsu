package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class RapSong extends Song {
    protected int bpm;
    protected String performerRace;
    protected boolean isPerformerAlive;
    protected String performerDeathType;
    public RapSong(){
        super();
    }
    public RapSong(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key, int bpm, String performerRace, boolean isPerformerAlive, String performerDeathType) throws IOException {
        super(author, name, duration, albumName, listeningStats, key);
        this.bpm = bpm;
        this.performerRace = performerRace;
        this.isPerformerAlive = isPerformerAlive;
        this.performerDeathType = performerDeathType;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "BPM: " + bpm + '\n' +
                "Performer race: " + performerRace + '\n' +
                "Is performer alive: " + isPerformerAlive + '\n' +
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

    public boolean isPerformerAlive() {
        return isPerformerAlive;
    }

    public void setPerformerAlive(boolean performerAlive) {
        isPerformerAlive = performerAlive;
    }

    public String getPerformerDeathType() {
        return performerDeathType;
    }

    public void setPerformerDeathType(String performerDeathType) {
        this.performerDeathType = performerDeathType;
    }


}
