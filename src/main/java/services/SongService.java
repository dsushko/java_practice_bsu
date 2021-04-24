package services;

import company.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class SongService<T extends Song> {
    DAOInterface<T> Dao;
    Class<T> type;
    String source;
    static final int SOURCES_N = 3;
    static final int CLASS_TYPES_N = 3;
    static ReentrantLock[][] lockers = new ReentrantLock[CLASS_TYPES_N][SOURCES_N];

    public SongService(Class<T> type, String source) throws ServiceLayerException {
        try {
            for (int i = 0; i < CLASS_TYPES_N; i++) {
                for (int j = 0; j < SOURCES_N; j++) {
                    if (lockers[i][j] == null) {
                        lockers[i][j] = new ReentrantLock();
                    }
                }
            }
            this.type = type;
            this.source = source;
            if (source.equals("csv")) {
                Dao = (DAOInterface<T>) Proxy.newProxyInstance(CsvDAO.class.getClassLoader(),
                        new Class[]{
                                DAOInterface.class
                        }, new LoggingProxyHandler<>(new CsvDAO<>(type)));
            } else if (source.equals("json")) {
                Dao = (DAOInterface<T>) Proxy.newProxyInstance(JsonDAO.class.getClassLoader(),
                        new Class[]{
                                DAOInterface.class
                        }, new LoggingProxyHandler<>(new JsonDAO<>(type)));
            } else if (source.equals("xml")) {
                Dao = (DAOInterface<T>) Proxy.newProxyInstance(XmlDAO.class.getClassLoader(),
                        new Class[]{
                                DAOInterface.class
                        }, new LoggingProxyHandler<>(new XmlDAO<>(type)));
            } else throw new ServiceLayerException("Wrong file format");
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't start service: " + e.getMessage());
        }
    }

    public List<T> SortByListenersAgeGroup(String group) throws ServiceLayerException {
        try{
        List<T> songs = Dao.read("rock.json");
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
            return songs;
        } else throw new Exception("Wrong age group");
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't read song: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    void lockThis(){
        if(type == RockSong.class){
            lockBySource(0);
        } else if(type == RapSong.class){
            lockBySource(1);
        } else if(type == Opera.class){
            lockBySource(2);
        }
    }
    void lockBySource(int type){
        if(source.equals("json")){
            lockers[type][0].lock();
        } else if(source.equals("xml")){
            lockers[type][1].lock();
        } else if(source.equals("csv")){
            lockers[type][2].lock();
        }
    }

    void unlockThis(){
        if(type == RockSong.class){
            unlockBySource(0);
        } else if(type == RapSong.class){
            unlockBySource(1);
        } else if(type == Opera.class){
            unlockBySource(2);
        }
    }
    void unlockBySource(int type){
        if(source.equals("json")){
            lockers[type][0].unlock();
        } else if(source.equals("xml")){
            lockers[type][1].unlock();
        } else if(source.equals("csv")){
            lockers[type][2].unlock();
        }
    }
/*
    public double AverageListenersByAgeGroupAndGenre(String group, String genre) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        List<T> songs = Dao.read("rock.json");
        int count = 0;
        int totalSum = 0;
        if(genre.equals("Rock")){
            for (T s:
                    songs) {
                if(s instanceof RockSong){
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
            for (T s:
                    songs) {
                if(s instanceof RapSong){
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
            for (T s:
                    songs) {
                if(s instanceof Opera){
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
*/

    public void AddSong(T model) throws ServiceLayerException {
        try{
        List<T> all = null;
        lockThis();
        all = Dao.read(ReturnFilename());
        all.add(model);
        Dao.write(all, ReturnFilename());
        unlockThis();
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't add song: " + e.getMessage());
        }
    }

    public T GetSong(int id) throws ServiceLayerException {
        try{
        lockThis();
        List<T> songs = GetSongs();
        unlockThis();
        for (T s:
             songs) {
            if(s.getId() == id){
                return s;
            }
        }
        return (T) new Song();
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't get song: " + e.getMessage());
        }
    }

    public void EditSong(int id, T songModel) throws ServiceLayerException {
        try{
        List<T> all = null;
        all = Dao.read(ReturnFilename());
        ArrayList<T> newList = new ArrayList<>();
        for (T song:
             all) {
            if(song.getId() == id){
                newList.add(songModel);
            }
            else
                newList.add(song);
        }
        Dao.write(newList, ReturnFilename());
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't edit song:" + e.getMessage());
        }
    }

    public void DeleteSong(int id) throws ServiceLayerException {
        try {
            lockThis();
            List<T> songs = Dao.read(ReturnFilename());
            unlockThis();
            ArrayList<T> afterDelete = new ArrayList<>();
            for (T s :
                    songs) {
                if (s.getId() != id) {
                    afterDelete.add(s);
                }
            }
            lockThis();
            Dao.write(afterDelete, ReturnFilename());
            unlockThis();
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't delete song: " + e.getMessage());
        }
    }

    public String ReturnFilename(){
        if(type == RockSong.class) {
            return "C:/Users/ъуъ/lab3_/rock." + source;
        }
        else
        if(type == RapSong.class) {
            return "C:/Users/ъуъ/lab3_/rap." + source;
        }
        else
        //if(type == Opera.class)
            return "C:/Users/ъуъ/lab3_/opera." + source;

    }

    public List<T> SortByListenersAgeGroup(List<T> songs, String group){
        switch (group) {
            case "<10" -> songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getChildrenUnder10Count(), o2.getListeningStats().getChildrenUnder10Count());
                }
            });
            case "10-18" -> songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getTeenagers10To18Count(), o2.getListeningStats().getTeenagers10To18Count());
                }
            });
            case "18-35" -> songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getYouth18To35Count(), o2.getListeningStats().getYouth18To35Count());
                }
            });
            case "35-60" -> songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getPeople35To60Count(), o2.getListeningStats().getPeople35To60Count());
                }
            });
            case ">60" -> songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o1.getListeningStats().getOldsAbove60Count(), o2.getListeningStats().getOldsAbove60Count());
                }
            });
        }
        return songs;
    }

    public List<T> GetSongs() throws ServiceLayerException {
        try{
        lockThis();
        List<T> res = Dao.read(ReturnFilename());
        unlockThis();
        return res;
        } catch (Exception e){
            throw new ServiceLayerException("Couldn't get song : " + e.getMessage());
        }
    }
}
