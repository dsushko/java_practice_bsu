package company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class ListeningStats {
    protected int childrenUnder10Count;
    protected int teenagers10To18Count;
    protected int youth18To35Count;
    protected int people35To60Count;
    protected int oldsAbove60Count;
    protected int malesCount;
    protected int femalesCount;

    public ListeningStats(){

    };

    public ListeningStats(int childrenUnder10Count, int teenagers10To18Count, int youth18To35Count, int people35To60Count, int oldsAbove60Count, int malesCount, int femalesCount) {
        this.childrenUnder10Count = childrenUnder10Count;
        this.teenagers10To18Count = teenagers10To18Count;
        this.youth18To35Count = youth18To35Count;
        this.people35To60Count = people35To60Count;
        this.oldsAbove60Count = oldsAbove60Count;
        this.malesCount = malesCount;
        this.femalesCount = femalesCount;
    }

    public static void SortByListenersAgeGroup(Vector<Song> songs, String group){
        if(group == "<10"){
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.listeningStats.childrenUnder10Count, o2.listeningStats.childrenUnder10Count);
                }
            });
        } else
        if(group == "10-18"){
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.listeningStats.teenagers10To18Count, o2.listeningStats.teenagers10To18Count);
                }
            });
        } else
        if(group == "18-35"){
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.listeningStats.youth18To35Count, o2.listeningStats.youth18To35Count);
                }
            });
        } else
        if(group == "35-60"){
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.listeningStats.people35To60Count, o2.listeningStats.people35To60Count);
                }
            });
        } else
        if(group == ">60"){
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.listeningStats.oldsAbove60Count, o2.listeningStats.oldsAbove60Count);
                }
            });
        }
    }

    public static double AverageListenersByAgeGroupAndGenre(Vector<Song> songs, String group, String genre){
        int count = 0;
        int totalSum = 0;
        if(genre.equals("Rock")){
            for (Song s:
                 songs) {
                RockSong rs = new RockSong();
                if(s.getClass() == rs.getClass()){
                    count++;
                    if(group == "<10"){
                        count+=s.listeningStats.childrenUnder10Count;
                    } else
                    if(group == "10-18"){
                        count+=s.listeningStats.teenagers10To18Count;
                    } else
                    if(group == "18-35"){
                        count+=s.listeningStats.youth18To35Count;
                    } else
                    if(group == "35-60"){
                        count+=s.listeningStats.people35To60Count;
                    } else
                    if(group == ">60"){
                        count+=s.listeningStats.oldsAbove60Count;
                    }
                }
                if(count!=0){
                    return ((double)totalSum)/count;
                } else return 0;
            }
        } else if(genre.equals("Rap")){
            for (Song s:
                    songs) {
                RapSong rs = new RapSong();
                if(s.getClass() == rs.getClass()){
                    count++;
                    if(group == "<10"){
                        count+=s.listeningStats.childrenUnder10Count;
                    } else
                    if(group == "10-18"){
                        count+=s.listeningStats.teenagers10To18Count;
                    } else
                    if(group == "18-35"){
                        count+=s.listeningStats.youth18To35Count;
                    } else
                    if(group == "35-60"){
                        count+=s.listeningStats.people35To60Count;
                    } else
                    if(group == ">60"){
                        count+=s.listeningStats.oldsAbove60Count;
                    }
                }
                if(count!=0){
                    return ((double)totalSum)/count;
                } else return 0;
            }
        } else if(genre.equals("Opera")){
            for (Song s:
                    songs) {
                Opera rs = new Opera();
                if(s.getClass() == rs.getClass()){
                    count++;
                    if(group == "<10"){
                        count+=s.listeningStats.childrenUnder10Count;
                    } else
                    if(group == "10-18"){
                        count+=s.listeningStats.teenagers10To18Count;
                    } else
                    if(group == "18-35"){
                        count+=s.listeningStats.youth18To35Count;
                    } else
                    if(group == "35-60"){
                        count+=s.listeningStats.people35To60Count;
                    } else
                    if(group == ">60"){
                        count+=s.listeningStats.oldsAbove60Count;
                    }
                }
                if(count!=0){
                    return ((double)totalSum)/count;
                } else return 0;
            }
        }
        return 0;
    }

    public int getChildrenUnder10Count() {
        return childrenUnder10Count;
    }

    public void setChildrenUnder10Count(int childrenUnder10Count) {
        this.childrenUnder10Count = childrenUnder10Count;
    }

    public int getTeenagers10To18Count() {
        return teenagers10To18Count;
    }

    public void setTeenagers10To18Count(int teenagers10To18Count) {
        this.teenagers10To18Count = teenagers10To18Count;
    }

    public int getYouth18To35Count() {
        return youth18To35Count;
    }

    public void setYouth18To35Count(int youth18To35Count) {
        this.youth18To35Count = youth18To35Count;
    }

    public int getPeople35To60Count() {
        return people35To60Count;
    }

    public void setPeople35To60Count(int people35To60Count) {
        this.people35To60Count = people35To60Count;
    }

    public int getOldsAbove60Count() {
        return oldsAbove60Count;
    }

    public void setOldsAbove60Count(int oldsAbove60Count) {
        this.oldsAbove60Count = oldsAbove60Count;
    }

    public int getMalesCount() {
        return malesCount;
    }

    public void setMalesCount(int malesCount) {
        this.malesCount = malesCount;
    }

    public int getFemalesCount() {
        return femalesCount;
    }

    public void setFemalesCount(int femalesCount) {
        this.femalesCount = femalesCount;
    }

    @Override
    public String toString() {
        return  "Children under 10 count: " + childrenUnder10Count +"\n" +
                "Teenagers 10 to 18 count: " + teenagers10To18Count +"\n" +
                "Youth 18 to 35 count: " + youth18To35Count +"\n" +
                "People 35 to 60 count: " + people35To60Count +"\n" +
                "Olds above 60 count: " + oldsAbove60Count +"\n" +
                "Males count: " + malesCount +"\n" +
                "Females count: " + femalesCount + "\n";
    }

}
