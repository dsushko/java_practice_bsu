package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
    public RapSong(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key, int bpm, String performerRace, boolean isPerformerAlive, String performerDeathType) {
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

    @Override
    public String toCSV() {
        return super.toCSV() + "," + bpm + ",\"" + performerRace + "\"," + isPerformerAlive + ",\"" + performerDeathType +"\"";
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
        int bpm;
        String performerRace;
        boolean isPerformerAlive;
        String performerDeathType;

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
            bpm = Integer.parseInt(valueBuffer);

            performerRace = input.next();
            performerRace = performerRace.substring(1, performerRace.length() - 1);

            valueBuffer = input.next();
            isPerformerAlive = Boolean.parseBoolean(valueBuffer);

            performerDeathType = input.next();
            performerDeathType = performerDeathType.substring(1, performerDeathType.length() - 1);


            result.add(new RapSong(author,name,duration,albumName,listeningStats,key, bpm, performerRace, isPerformerAlive, performerDeathType));
        }

        return result;
    }
/*

    public static Vector<Song> readXML(String filename) throws IOException, ParserConfigurationException, SAXException {
        Vector<Song> result = new Vector<>();
        File ifile = new File(filename);
        Scanner input = new Scanner(ifile);

        String author;
        String name;
        String duration;
        String albumName;
        String key;
        ListeningStats listeningStats;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        builder = factory.newDocumentBuilder();

        Document document = null;
        document = builder.parse(new File(filename));
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).hasChildNodes()) {

                author = nodeList.item(i).getChildNodes().item(1).getTextContent();
                name = nodeList.item(i).getChildNodes().item(3).getTextContent();
                duration = nodeList.item(i).getChildNodes().item(5).getTextContent();
                albumName = nodeList.item(i).getChildNodes().item(7).getTextContent();
                key = nodeList.item(i).getChildNodes().item(9).getTextContent();
                listeningStats = new ListeningStats(
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(1).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(3).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(5).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(7).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(9).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(11).getTextContent()),
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(11).getChildNodes().item(13).getTextContent())
                );
                result.add(new Song(author, name, duration,albumName, listeningStats, key));
            }
        }
        return result;
    }
*/

}
