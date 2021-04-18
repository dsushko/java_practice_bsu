package controllers;

import company.*;
import services.SongService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.List;


public class SongController<T extends Song> {
    SongService<T> service;

    SongController(Class<T> type, String source) throws Exception {
        service = new SongService<T>(type, source);
    }
    public void AddSong(){
        service.AddSong();
    }
    public void EditSong(){
        service.EditSong();
    }
    public void DeleteSong(){
        service.DeleteSong();
    };
    public List<T> SortByListenersAgeGroup(String group) throws Exception {
        return service.SortByListenersAgeGroup(group);
    }
    public double AverageListenersByAgeGroupAndGenre(String group, String genre) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
        return service.AverageListenersByAgeGroupAndGenre(group, genre);
    }

}
