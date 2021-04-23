package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    public String toCSV(){
        return childrenUnder10Count + ","
                + teenagers10To18Count + ","
                + youth18To35Count + ","
                + people35To60Count + ","
                + oldsAbove60Count + ","
                + malesCount + ","
                + femalesCount;
    }

    public Element toXML(String name, Document document) {

        org.w3c.dom.Element result =  document.createElement(name);

        Element eChildrenUnder10Count = document.createElement("childrenUnder10Count");
        Element eTeenagers10To18Count = document.createElement("teenagers10To18Count");
        Element eYouth18To35Count = document.createElement("youth18To35Count");
        Element ePeople35To60Count = document.createElement("people35To60Count");
        Element eOldsAbove60Count = document.createElement("oldsAbove60Count");
        Element eMalesCount = document.createElement("malesCount");
        Element eFemalesCount = document.createElement("femalesCount");

        Text textChildrenUnder10Count = document.createTextNode(String.valueOf(childrenUnder10Count));
        Text textTeenagers10To18Count = document.createTextNode(String.valueOf(teenagers10To18Count));
        Text textYouth18To35Count = document.createTextNode(String.valueOf(youth18To35Count));
        Text textPeople35To60Count = document.createTextNode(String.valueOf(people35To60Count));
        Text textOldsAbove60Count = document.createTextNode(String.valueOf(oldsAbove60Count));
        Text textMalesCount = document.createTextNode(String.valueOf(malesCount));
        Text textFemalesCount = document.createTextNode(String.valueOf(femalesCount));

        eChildrenUnder10Count.appendChild(textChildrenUnder10Count);
        eTeenagers10To18Count.appendChild(textTeenagers10To18Count);
        eYouth18To35Count.appendChild(textYouth18To35Count);
        ePeople35To60Count.appendChild(textPeople35To60Count);
        eOldsAbove60Count.appendChild(textOldsAbove60Count);
        eMalesCount.appendChild(textMalesCount);
        eFemalesCount.appendChild(textFemalesCount);

        result.appendChild(eChildrenUnder10Count);
        result.appendChild(eTeenagers10To18Count);
        result.appendChild(eYouth18To35Count);
        result.appendChild(ePeople35To60Count);
        result.appendChild(eOldsAbove60Count);
        result.appendChild(eMalesCount);
        result.appendChild(eFemalesCount);

        return result;
    }

    public String toJSON(){
        StringBuilder jsonResult = new StringBuilder();
        jsonResult.append("{\n");
        jsonResult.append("\"childrenUnder10Count\":" + childrenUnder10Count+",\n");
        jsonResult.append("\"teenagers10To18Count\":" + teenagers10To18Count+",\n");
        jsonResult.append("\"youth18To35Count\":" + youth18To35Count+",\n");
        jsonResult.append("\"people35To60Count\":" + people35To60Count+",\n");
        jsonResult.append("\"oldsAbove60Count\":" + oldsAbove60Count+",\n");
        jsonResult.append("\"malesCount\":" + malesCount+",\n");
        jsonResult.append("\"femalesCount\":" + femalesCount+"\n");
        jsonResult.append("}");
        return jsonResult.toString();
    }

    public static ListeningStats convertCSVToStats(String csv){
        StringBuffer buffer = new StringBuffer(csv);
        while(!Character.isDigit(buffer.charAt(0)))
            buffer.deleteCharAt(0);
        while(!Character.isDigit(buffer.charAt(buffer.length()-1)))
            buffer.deleteCharAt(buffer.length() - 1);
        String formatted = buffer.toString();
        String[] params = formatted.split(",");
        int childrenUnder10Count = Integer.parseInt(params[0]);
        int teenagers10To18Count= Integer.parseInt(params[1]);
        int youth18To35Count= Integer.parseInt(params[2]);
        int people35To60Count= Integer.parseInt(params[3]);
        int oldsAbove60Count= Integer.parseInt(params[4]);
        int malesCount= Integer.parseInt(params[5]);
        int femalesCount= Integer.parseInt(params[6]);
        ListeningStats result = new ListeningStats(childrenUnder10Count,
                teenagers10To18Count,
                youth18To35Count,
                people35To60Count,
                oldsAbove60Count,
                malesCount,
                femalesCount
        );
        return result;
    }

    public static ListeningStats readJSONFromScanner(Scanner input){
        int childrenUnder10Count=0;
        int teenagers10To18Count=0;
        int youth18To35Count=0;
        int people35To60Count=0;
        int oldsAbove60Count=0;
        int malesCount=0;
        int femalesCount=0;

        StringBuffer currLine;
        boolean endOfObject = false;

        while (input.hasNextLine()){
            currLine = new StringBuffer(input.nextLine());
            for(int i = 0; i < currLine.length(); i++) {
                if(currLine.charAt(i) == '}') {
                    endOfObject = true;
                    break;
                }
                if(currLine.charAt(i) == '\"'){
                    for(int j = i + 1; j < currLine.length(); j++){
                        if(currLine.charAt(j) == '\"'){
                            String currVar = currLine.substring(i + 1,j);
                            i = j;
                            switch (currVar) {
                                case ("childrenUnder10Count"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String childrenUnder10CountValue = currLine.substring(i + 1,j);
                                    childrenUnder10Count = Integer.parseInt(childrenUnder10CountValue);
                                    break;
                                case ("teenagers10To18Count"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String teenagers10To18CountValue = currLine.substring(i+1,j);
                                    teenagers10To18Count = Integer.parseInt(teenagers10To18CountValue);
                                    break;
                                case ("youth18To35Count"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String youth18To35CountValue = currLine.substring(i+1,j);
                                    youth18To35Count = Integer.parseInt(youth18To35CountValue);
                                    break;
                                case ("people35To60Count"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String people35To60CountValue = currLine.substring(i+1,j);
                                    people35To60Count = Integer.parseInt(people35To60CountValue);
                                    break;
                                case ("oldsAbove60Count"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String oldsAbove60CountValue = currLine.substring(i+1,j);
                                    oldsAbove60Count = Integer.parseInt(oldsAbove60CountValue);
                                    break;
                                case ("malesCount"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String malesCountValue = currLine.substring(i+1,j);
                                    malesCount = Integer.parseInt(malesCountValue);
                                    break;
                                case ("femalesCount"):
                                    while(currLine.charAt(i) != ':')
                                        i++;
                                    while (currLine.charAt(i) == ' ')
                                        i++;
                                    j = i;
                                    while( j < currLine.length() && currLine.charAt(j) != ',')
                                        j++;
                                    if(j == currLine.length()){
                                        endOfObject = true;
                                    }
                                    String femalesCountValue = currLine.substring(i+1,j);
                                    femalesCount = Integer.parseInt(femalesCountValue);
                                    break;
                                default:
                                    throw(new InputMismatchException("JSON file is wrong"));
                            }
                            i = j;
                            while(i < currLine.length() && currLine.charAt(i) != '}' && currLine.charAt(i) != ',')
                                i++;
                            if( i == currLine.length() || currLine.charAt(i) == '}'){
                                endOfObject = true;
                                break;
                            }
                        }
                    }
                }
            }
            if(endOfObject)
                break;
        }

        return new ListeningStats(childrenUnder10Count,teenagers10To18Count,youth18To35Count,people35To60Count,oldsAbove60Count,malesCount,femalesCount);
    }
}
