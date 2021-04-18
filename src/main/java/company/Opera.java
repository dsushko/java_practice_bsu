package company;

import java.io.File;
import java.io.FileNotFoundException;
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
                    int releaseCentury, int orchestraCount, String vocalVoiceTypeRequired) throws IOException {
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

}
