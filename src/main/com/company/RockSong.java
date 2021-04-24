package com.company;

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

/*    public static Vector<Song> readCSV(String filename) throws FileNotFoundException {
        Vector<Song> result = new Vector<>();
        File ifile = new File(filename);
        Scanner input = new Scanner(ifile);
        String author;
        String name;
        String duration;
        String albumName;
        String key;
        ListeningStats listeningStats;
        String subGenre;
        boolean hasExtremeVocals;
        String[] guitarEffects;

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

            subGenre = input.next();
            subGenre = subGenre.substring(1, subGenre.length() - 1);

            valueBuffer = input.next();
            hasExtremeVocals = Boolean.parseBoolean(valueBuffer);

            input.useDelimiter("\\[");
            valueBuffer = input.next();
            input.useDelimiter("]");
            valueBuffer = input.next();
            guitarEffects = valueBuffer.split(",");
            input.useDelimiter("\n");
            result.add(new RockSong(author,name,duration,albumName,listeningStats,key, subGenre, hasExtremeVocals, guitarEffects));
        }

        return result;
    }*/
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
