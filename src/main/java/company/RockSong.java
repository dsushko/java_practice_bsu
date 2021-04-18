package company;

import java.io.IOException;

public class RockSong extends Song {
    protected String subGenre;
    protected boolean hasExtremeVocals;
    protected String guitarEffects;

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public boolean isHasExtremeVocals() {
        return hasExtremeVocals;
    }

    public void setHasExtremeVocals(boolean hasExtremeVocals) {
        this.hasExtremeVocals = hasExtremeVocals;
    }

    public String getGuitarEffects() {
        return guitarEffects;
    }

    public void setGuitarEffects(String guitarEffects) {
        this.guitarEffects = guitarEffects;
    }

    public RockSong(){
        super();
    }

    public RockSong(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key,
                    String subGenre, boolean hasExtremeVocals, String guitarEffects) throws IOException {
        super(author,name,duration,albumName,listeningStats,key);
        this.subGenre = subGenre;
        this.hasExtremeVocals = hasExtremeVocals;
        this.guitarEffects = guitarEffects;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Subgenre: " + subGenre + '\n' +
                "Has extreme vocals: " + hasExtremeVocals + '\n' +
                "Guitar effects: " + guitarEffects + '\n';
    }
}
