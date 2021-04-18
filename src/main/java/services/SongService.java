package services;

import company.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class SongService<T extends Song> {
    DAOInterface<T> Dao;
    Class<T> type;

    public SongService(Class<T> type, String source) throws Exception {
        this.type = type;
        if(source.equals("csv")){
            Dao = (DAOInterface<T>) Proxy.newProxyInstance(CsvDAO.class.getClassLoader(),
                    new Class[]{
                            DAOInterface.class
                    }, new LoggingProxyHandler<>(new CsvDAO<>(type)));
        } else if (source.equals("json")){
            Dao = (DAOInterface<T>) Proxy.newProxyInstance(JsonDAO.class.getClassLoader(),
                    new Class[]{
                            DAOInterface.class
                    }, new LoggingProxyHandler<>(new JsonDAO<>(type)));
        } else if (source.equals("xml")){
            Dao = (DAOInterface<T>) Proxy.newProxyInstance(XmlDAO.class.getClassLoader(),
                    new Class[]{
                            DAOInterface.class
                    }, new LoggingProxyHandler<>(new XmlDAO<>(type)));
        } else throw new Exception("wrong file format");
    }

    public List<T> SortByListenersAgeGroup(String group) throws Exception {
        List<T> songs = Dao.read("src.db");
        if(group == "<10"){
            songs.sort((Comparator<? super T>) new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getChildrenUnder10Count(), o2.getListeningStats().getChildrenUnder10Count());
                }
            });
        } else
        if(group == "10-18"){
            songs.sort((Comparator<? super T>) new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getTeenagers10To18Count(), o2.getListeningStats().getTeenagers10To18Count());
                }
            });
        } else
        if(group == "18-35"){
            songs.sort((Comparator<? super T>) new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getYouth18To35Count(), o2.getListeningStats().getYouth18To35Count());
                }
            });
        } else
        if(group == "35-60"){
            songs.sort((Comparator<? super T>) new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getPeople35To60Count(), o2.getListeningStats().getPeople35To60Count());
                }
            });
        } else
        if(group == ">60"){
            songs.sort((Comparator<? super T>) new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getOldsAbove60Count(), o2.getListeningStats().getOldsAbove60Count());
                }
            });
        } else throw new Exception("wrong age group");
        return songs;
    }

    public double AverageListenersByAgeGroupAndGenre(String group, String genre) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        List<T> songs = Dao.read("src.db");
        int count = 0;
        int totalSum = 0;
        if(genre.equals("Rock")){
            for (Song s:
                    songs) {
                RockSong rs = new RockSong();
                if(s.getClass() == rs.getClass()){
                    count++;
                    if(group == "<10"){
                        count+=s.getListeningStats().getChildrenUnder10Count();
                    } else
                    if(group == "10-18"){
                        count+=s.getListeningStats().getTeenagers10To18Count();
                    } else
                    if(group == "18-35"){
                        count+=s.getListeningStats().getYouth18To35Count();
                    } else
                    if(group == "35-60"){
                        count+=s.getListeningStats().getPeople35To60Count();
                    } else
                    if(group == ">60"){
                        count+=s.getListeningStats().getOldsAbove60Count();
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
                        count+=s.getListeningStats().getChildrenUnder10Count();
                    } else
                    if(group == "10-18"){
                        count+=s.getListeningStats().getTeenagers10To18Count();
                    } else
                    if(group == "18-35"){
                        count+=s.getListeningStats().getYouth18To35Count();
                    } else
                    if(group == "35-60"){
                        count+=s.getListeningStats().getPeople35To60Count();
                    } else
                    if(group == ">60"){
                        count+=s.getListeningStats().getOldsAbove60Count();
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
                        count+=s.getListeningStats().getChildrenUnder10Count();
                    } else
                    if(group == "10-18"){
                        count+=s.getListeningStats().getTeenagers10To18Count();
                    } else
                    if(group == "18-35"){
                        count+=s.getListeningStats().getYouth18To35Count();
                    } else
                    if(group == "35-60"){
                        count+=s.getListeningStats().getPeople35To60Count();
                    } else
                    if(group == ">60"){
                        count+=s.getListeningStats().getOldsAbove60Count();
                    }
                }
                if(count!=0){
                    return ((double)totalSum)/count;
                } else return 0;
            }
        }
        return 0;
    }

    public void AddSong(){

    }
//  public T GetSong(){
//
//  }
    public void EditSong(){

    }
    public void DeleteSong(){

    }
}
