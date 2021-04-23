package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Song {
    protected String duration;
    protected String author;
    protected String albumName;
    protected String name;
    protected ListeningStats listeningStats;
    protected String key;
    public Song(){

    };
    public Song(String author, String name, String duration, String albumName, ListeningStats listeningStats, String key){
        this.author = author;
        this.name = name;
        this.duration = duration;
        this.albumName = albumName;
        this.listeningStats = listeningStats;
        this.key = key;
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

    public String toCSV(){
        return "\"" +author + "\"," +
                "\"" + name + "\"," +
                "\"" + duration + "\"," +
                "\"" + albumName+ "\"," +
                "\""+ key + "\",{"
                + listeningStats.toCSV() + "}\n";
    }
/*
    public static void saveCSV(Vector<Song> songs, String filename) throws IOException {
        FileWriter ofile = new FileWriter(filename, false);
        for (Song s:
             songs) {
             ofile.write(s.toCSV());
        }
        ofile.flush();
    }

    public static void saveXML(Vector<Song> songs, String filename) throws IOException, ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("Songs");
        document.appendChild(root);

        RockSong rockS = new RockSong();
        Opera o = new Opera();
        RapSong rapS = new RapSong();

        for (Song s:
                songs) {

            Element songField;

            if(s.getClass() == rockS.getClass()){
                songField = document.createElement("RockSong");
            } else
            if(s.getClass() == o.getClass()){
                songField  = document.createElement("Opera");
            } else
            if(s.getClass() == rapS.getClass()){
                songField  = document.createElement("RapSong");
            } else {
                songField = document.createElement("Song");
            }

            root.appendChild(songField);

            Song currSong = s;

            Element author = document.createElement("author");
            Element name = document.createElement("name");
            Element duration = document.createElement("duration");
            Element albumName = document.createElement("albumName");
            Element key = document.createElement("key");
            Element listeningStats = s.listeningStats.toXML("listeningStats", document);


            Text textAuthor = document.createTextNode(s.author);
            Text textName = document.createTextNode(s.name);
            Text textDuration = document.createTextNode(s.duration);
            Text textAlbumName = document.createTextNode(s.albumName);
            Text textKey = document.createTextNode(s.key);

            author.appendChild(textAuthor);
            name.appendChild(textName);
            duration.appendChild(textDuration);
            albumName.appendChild(textAlbumName);
            key.appendChild(textKey);

            songField.appendChild(author);
            songField.appendChild(name);
            songField.appendChild(duration);
            songField.appendChild(albumName);
            songField.appendChild(key);
            songField.appendChild(listeningStats);

            if(s.getClass() == rockS.getClass()){
                Element subGenre = document.createElement("subGenre");
                Element hasExtremeVocals = document.createElement("hasExtremeVocals");
                Element guitarEffects = document.createElement("guitarEffects");

                Text textSubGenre = document.createTextNode(((RockSong) s).subGenre);
                Text textHasExtremeVocals = document.createTextNode(String.valueOf(((RockSong) s).hasExtremeVocals));

                subGenre.appendChild(textSubGenre);
                hasExtremeVocals.appendChild(textHasExtremeVocals);

                for(int i = 0; i < ((RockSong) s).guitarEffects.length; i++){
                    Element effect = document.createElement("effect");
                    Text textEffect = document.createTextNode(((RockSong) s).guitarEffects[i]);
                    effect.appendChild(textEffect);
                    guitarEffects.appendChild(effect);
                }
                songField.appendChild(subGenre);
                songField.appendChild(hasExtremeVocals);
                songField.appendChild(guitarEffects);

            } else
            if(s.getClass() == o.getClass()){
                Element releaseCentury = document.createElement("releaseCountry");
                Element orchestraCount = document.createElement("orchestraCount");
                Element vocalVoiceTypeRequired = document.createElement("vocalVoiceTypeRequired");

                Text textReleaseCentury = document.createTextNode(String.valueOf(((Opera) s).releaseCentury));
                Text textOrchestraCount = document.createTextNode(String.valueOf(((Opera) s).orchestraCount));
                Text textVocalVoiceTypeRequired = document.createTextNode(String.valueOf(((Opera) s).vocalVoiceTypeRequired));

                releaseCentury.appendChild(textReleaseCentury);
                orchestraCount.appendChild(textOrchestraCount);
                vocalVoiceTypeRequired.appendChild(textVocalVoiceTypeRequired);

                songField.appendChild(releaseCentury);
                songField.appendChild(orchestraCount);
                songField.appendChild(vocalVoiceTypeRequired);
            } else
            if(s.getClass() == rapS.getClass()){
                Element bpm = document.createElement("bpm");
                Element performerRace = document.createElement("performerRace");
                Element isPerformerAlive = document.createElement("isPerformerAlive");
                Element performerDeathType = document.createElement("performerDeathType");

                Text textBpm = document.createTextNode(String.valueOf(((RapSong) s).bpm));
                Text textPerformerRace = document.createTextNode(((RapSong) s).performerRace);
                Text textIsPerformerAlive = document.createTextNode(String.valueOf(((RapSong) s).isPerformerAlive));
                Text texPerformerDeathType = document.createTextNode(((RapSong) s).performerDeathType);

                bpm.appendChild(textBpm);
                performerRace.appendChild(textPerformerRace);
                isPerformerAlive.appendChild(textIsPerformerAlive);
                performerDeathType.appendChild(texPerformerDeathType);

                songField.appendChild(bpm);
                songField.appendChild(performerRace);
                songField.appendChild(isPerformerAlive);
                songField.appendChild(performerDeathType);
            }
        }
        Transformer t = null;
        t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filename)));

    }

    public static void saveJSON(Vector<Song> songs, String filename) throws IOException {

        RockSong rockS = new RockSong();
        Opera o = new Opera();
        RapSong rapS = new RapSong();

        FileWriter ofile = new FileWriter(filename, false);
        ofile.write("[\n");
        for (int i = 0; i < songs.size(); i++){
            ofile.write("{\n");
            ofile.write("\"author\":\"" + songs.elementAt(i).author+"\",\n");
            ofile.write("\"name\":\"" + songs.elementAt(i).name+"\",\n");
            ofile.write("\"duration\":\"" + songs.elementAt(i).duration+"\",\n");
            ofile.write("\"albumName\":\"" + songs.elementAt(i).albumName+"\",\n");
            ofile.write("\"key\":\"" + songs.elementAt(i).key+"\",\n");
            ofile.write("\"listeningStats\":" + songs.elementAt(i).listeningStats.toJSON()+"\n");

            if(songs.elementAt(i).getClass() == rockS.getClass()){
                ofile.write("\"subGenre\":\"" + ((RockSong)songs.elementAt(i)).subGenre+"\",\n");
                ofile.write("\"hasExtremeVocals\":" + ((RockSong)songs.elementAt(i)).hasExtremeVocals+",\n");
                ofile.write("\"guitarEffects\":[\n");
                for(int j = 0; j < ((RockSong)songs.elementAt(i)).guitarEffects.length; j++){
                    ofile.write(((RockSong)songs.elementAt(i)).guitarEffects[i]);
                    if(j != ((RockSong)songs.elementAt(i)).guitarEffects.length - 1)
                         ofile.write(",");
                }
                 ofile.write("\n]\n");
            } else
            if(songs.elementAt(i).getClass() == o.getClass()){
                ofile.write("\"releaseCentury\":" + ((Opera)songs.elementAt(i)).releaseCentury+",\n");
                ofile.write("\"orchestraCount\":" + ((Opera)songs.elementAt(i)).orchestraCount+",\n");
                ofile.write("\"vocalVoiceTypeRequired\":\"" + ((Opera)songs.elementAt(i)).vocalVoiceTypeRequired + "\"\n");
            } else
            if(songs.elementAt(i).getClass() == rapS.getClass()){
                ofile.write("\"bpm\":" + ((RapSong)songs.elementAt(i)).bpm+",\n");
                ofile.write("\"performerRace\":\"" + ((RapSong)songs.elementAt(i)).performerRace+"\",\n");
                ofile.write("\"isPerformerAlive\":" + ((RapSong)songs.elementAt(i)).isPerformerAlive+",\n");
                ofile.write("\"performerDeathType\":" + ((RapSong)songs.elementAt(i)).performerDeathType+"\"\n");
            }

            ofile.write("}");
            if(i != songs.size() - 1){
                ofile.write(",");
            }
            ofile.write("\n");
        }
        ofile.write("]");

        ofile.flush();
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
            result.add(new Song(author,name,duration,albumName,listeningStats,key));
        }

        return result;
    }

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
            if(nodeList.item(i).getNodeName().equals("Song")) {
                result.add(new Song(author, name, duration, albumName, listeningStats, key));
            } else if(nodeList.item(i).getNodeName().equals("RockSong")){
                StringBuilder effects = new StringBuilder();
                for(int j = 0; j < nodeList.item(i).getChildNodes().item(17).getChildNodes().getLength()/2; j++){
                    effects.append(nodeList.item(i).getChildNodes().item(17).getChildNodes().item(2*j+1)).append(",");
                }
                result.add(
                        new RockSong(author,
                                name,
                                duration,
                                albumName,
                                listeningStats,
                                key,
                                nodeList.item(i).getChildNodes().item(13).getTextContent(), Boolean.parseBoolean(nodeList.item(i).getChildNodes().item(15).getTextContent()), effects.toString().split(",")));
            } else if(nodeList.item(i).getNodeName().equals("RapSong")){
                result.add( new RapSong(author,
                                name,
                                duration,
                                albumName,
                                listeningStats,
                                key,
                        Integer.parseInt(nodeList.item(i).getChildNodes().item(13).getTextContent()),
                        nodeList.item(i).getChildNodes().item(15).getTextContent(),
                        Boolean.parseBoolean(nodeList.item(i).getChildNodes().item(17).getTextContent()),
                        nodeList.item(i).getChildNodes().item(19).getTextContent()
                        )
                );
            } else if(nodeList.item(i).getNodeName().equals("Opera")){
                result.add( new Opera (author,
                                name,
                                duration,
                                albumName,
                                listeningStats,
                                key,
                                Integer.parseInt(nodeList.item(i).getChildNodes().item(13).getTextContent()),
                                Integer.parseInt(nodeList.item(i).getChildNodes().item(15).getTextContent()),
                                nodeList.item(i).getChildNodes().item(17).getTextContent()
                        )
                );
            }
        }
        return result;
    }

    public static Vector<Song> readJSON(String filename) throws FileNotFoundException {
        Vector<Song> result = new Vector<>();
        File ifile = new File(filename);
        Scanner input = new Scanner(ifile);

        String author = "";
        String name= "";
        String duration= "";
        String albumName= "";
        String key= "";
        ListeningStats listeningStats = new ListeningStats();
        String subGenre = "";
        boolean hasExtremeVocals = false;
        String[] guitarEffects = {};
        int bpm = 0;
        String performerRace = "";
        boolean isPerformerAlive = false;
        String performerDeathType = "";
        int releaseCentury = 0;
        int orchestraCount = 0;
        String vocalVoiceTypeRequired = "";


        StringBuffer currLine = new StringBuffer();

        int objectBracketsCount = 0;
        boolean endOfObject = false;
        boolean fileIsOk = true;
        String songType = null;
        while(input.hasNextLine()){

            currLine = new StringBuffer(input.nextLine());
            for(int i = 0; i < currLine.length(); i++){
                if(currLine.charAt(i) == '{') {
                    objectBracketsCount++;
                    endOfObject = false;
                }
                if(!endOfObject) {
                    if (currLine.charAt(i) == '}') {
                        objectBracketsCount--;
                        if(objectBracketsCount == 0)
                            endOfObject = true;
                    }
                    if (currLine.charAt(i) == '\"') {
                        for (int j = i + 1; j < currLine.length(); j++) {
                            if(currLine.charAt(j) == '\"') {
                                String currVar = currLine.substring(i + 1, j);
                                i = j;
                                switch (currVar) {
                                    case ("author"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String authorValue = currLine.substring(i + 1, j);
                                        author = authorValue;
                                        break;
                                    case ("name"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String nameValue = currLine.substring(i + 1, j);
                                        name = nameValue;
                                        break;
                                    case ("duration"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String durationValue = currLine.substring(i + 1, j);
                                        duration = durationValue;
                                        break;
                                    case ("albumName"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String albumNameValue = currLine.substring(i + 1, j);
                                        albumName = albumNameValue;
                                        break;
                                    case ("key"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String keyValue = currLine.substring(i + 1, j);
                                        key = keyValue;
                                        break;
                                    case ("listeningStats"):
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '{')
                                            i++;
                                        listeningStats = ListeningStats.readJSONFromScanner(input);
                                        boolean innerObjectEnd = false;
                                        while(!innerObjectEnd){
                                            currLine = new StringBuffer(input.nextLine());
                                            for(int k = 0; k < currLine.length(); k++){
                                                if(currLine.charAt(k) == '}'){
                                                    innerObjectEnd = true;
                                                    j=k;
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    case("releaseCentury"):
                                        if(songType == null || songType.equals("Opera")){
                                            if(songType == null)
                                                songType = "Opera";

                                        } else {
                                            fileIsOk = false;
                                        }
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
                                        String releaseCenturyValue = currLine.substring(i + 1,j);
                                        releaseCentury = Integer.parseInt(releaseCenturyValue);
                                        break;
                                    case("orchestraCount"):
                                        if(songType == null || songType.equals("Opera")){
                                            if(songType == null)
                                                songType = "Opera";

                                        } else {
                                            fileIsOk = false;
                                        }
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
                                        String orchestraCountValue = currLine.substring(i + 1,j);
                                        orchestraCount = Integer.parseInt(orchestraCountValue);
                                        break;
                                    case("vocalVoiceTypeRequired"):
                                        if(songType == null || songType.equals("Opera")){
                                            if(songType == null)
                                                songType = "Opera";

                                        } else {
                                            fileIsOk = false;
                                        }
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String vocalVoiceTypeRequiredValue = currLine.substring(i + 1,j);
                                        vocalVoiceTypeRequired = vocalVoiceTypeRequiredValue;
                                        break;
                                    case("bpm"):
                                        if(songType == null || songType.equals("RapSong")){
                                            if(songType == null)
                                                songType = "RapSong";

                                        } else {
                                            fileIsOk = false;
                                        }
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
                                        String bpmValue = currLine.substring(i + 1,j);
                                        bpm = Integer.parseInt(bpmValue);
                                        break;
                                    case("performerRace"):
                                        if(songType == null || songType.equals("RapSong")){
                                            if(songType == null)
                                                songType = "RapSong";

                                        } else {
                                            fileIsOk = false;
                                        }
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String performerRaceValue = currLine.substring(i + 1,j);
                                        performerRace = performerRaceValue;
                                        break;
                                    case("isPerformerAlive"):
                                        if(songType == null || songType.equals("RapSong")){
                                            if(songType == null)
                                                songType = "RapSong";

                                        } else {
                                            fileIsOk = false;
                                        }
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
                                        String isPerformerAliveValue = currLine.substring(i + 1,j);
                                        isPerformerAlive = Boolean.parseBoolean(isPerformerAliveValue);
                                        break;
                                    case("performerDeathType"):
                                        if(songType == null || songType.equals("RapSong")){
                                            if(songType == null)
                                                songType = "RapSong";

                                        } else {
                                            fileIsOk = false;
                                        }
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String performerDeathTypeValue = currLine.substring(i + 1,j);
                                        performerDeathType = performerDeathTypeValue;
                                        break;
                                    case("subGenre"):
                                        if(songType == null || songType.equals("RockSong")){
                                            if(songType == null)
                                                songType = "RockSong";
                                        } else {
                                            fileIsOk = false;
                                        }
                                        while (currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '\"')
                                            i++;
                                        j = i + 1;
                                        while (currLine.charAt(j) != '\"')
                                            j++;
                                        String subGenreValue = currLine.substring(i + 1,j);
                                        subGenre = subGenreValue;
                                        break;
                                    case("hasExtremeVocals"):
                                        if(songType == null || songType.equals("RockSong")){
                                            if(songType == null)
                                                songType = "RockSong";
                                        } else {
                                            fileIsOk = false;
                                        }
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
                                        String hasExtremeVocalsValues = currLine.substring(i + 1,j);
                                        hasExtremeVocals = Boolean.getBoolean(hasExtremeVocalsValues);
                                        break;
                                    case("guitarEffects"):
                                        if(songType == null || songType.equals("RockSong")){
                                            if(songType == null)
                                                songType = "RockSong";

                                        } else {
                                            fileIsOk = false;
                                        }
                                        if(songType == null || songType.equals("RockSong")){
                                            if(songType == null)
                                                songType = "RockSong";
                                        } else {
                                            fileIsOk = false;
                                        }
                                        while(currLine.charAt(i) != ':')
                                            i++;
                                        while (currLine.charAt(i) == ' ')
                                            i++;
                                        while (currLine.charAt(i) != '[')
                                            i++;
                                        boolean innerArrayEnd = false;
                                        StringBuilder array = new StringBuilder();
                                        while(!innerArrayEnd){
                                            currLine = new StringBuffer(input.nextLine());
                                            for(int k = 0; k < currLine.length(); k++){
                                                while (currLine.charAt(k) == ' ')
                                                    k++;
                                                i = k;
                                                while (i < currLine.length() && currLine.charAt(i) != ',')
                                                    i++;
                                                if(i == currLine.length()){
                                                    array.append(currLine.substring(k + 1, i - 1));
                                                    innerArrayEnd = true;
                                                    currLine = new StringBuffer(input.nextLine());
                                                    i = 0;
                                                    j = 0;
                                                    k = 0;
                                                    guitarEffects = array.toString().split(",");
                                                    break;
                                                } else {
                                                    array.append(currLine.substring(k + 1, i - 1)).append(",");
                                                }
                                                k=i;
                                                if(currLine.charAt(k) == ']'){
                                                    guitarEffects = array.toString().split(",");
                                                    innerArrayEnd = true;
                                                    j=k;
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        throw (new InputMismatchException("JSON file is wrong"));
                                }
                                i = j;
                                while (i < currLine.length() && currLine.charAt(i) != '}' && currLine.charAt(i) != ',' && currLine.charAt(i) != '{')
                                    i++;
                                if (i == currLine.length()) {
                                    objectBracketsCount--;
                                    if(objectBracketsCount == 0)
                                        endOfObject = true;
                                    i--;
                                }
                                if(currLine.charAt(i) == '{'){
                                    objectBracketsCount++;
                                }
                                if(fileIsOk){

                                } else {
                                    throw new InputMismatchException("JSON file is wrong");
                                }
                            }
                        }
                    }
                }
                if(endOfObject && fileIsOk) {
                    if(songType == null){
                        result.add(new Song(author,name,duration,albumName,listeningStats,key));
                    } else
                    if(songType.equals("Opera")){
                        result.add(new Opera(author,name,duration,albumName,listeningStats,key, releaseCentury,orchestraCount,vocalVoiceTypeRequired));
                    } else
                    if(songType.equals("RockSong")){
                        result.add(new RockSong(author,name,duration,albumName,listeningStats,key, subGenre, hasExtremeVocals, guitarEffects));
                    } else
                    if(songType.equals("RapSong")){
                        result.add(new RapSong(author,name,duration,albumName,listeningStats,key, bpm, performerRace, isPerformerAlive, performerDeathType));
                    }
                    songType = null;
                }
            }
        }


        return result;
    }*/
}

