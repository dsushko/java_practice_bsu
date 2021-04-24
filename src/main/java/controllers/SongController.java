package controllers;

import company.Song;
import services.ServiceLayerException;
import services.SongService;

import java.util.List;


public class SongController<T extends Song> {
    SongService<T> service;

    public SongController(Class<T> type, String source) throws ServiceLayerException {
        service = new SongService<T>(type, source);
    }
    public void AddSong(T model) throws ServiceLayerException {
        service.AddSong(model);
        //throw new ServiceLayerException("Something went wrong: couldn't add new song");
    }
    public T GetSong(int id) throws ServiceLayerException {
        return service.GetSong(id);
    }
    public void EditSong(int id, T songModel) throws ServiceLayerException {
        service.EditSong(id, songModel);
    }
    public List<T> GetSongs() throws ServiceLayerException {
        return service.GetSongs();
    }
    public void DeleteSong(int id) throws ServiceLayerException {
        service.DeleteSong(id);
    };
    public List<T> SortByListenersAgeGroup(List<T> list,String group){
        return service.SortByListenersAgeGroup(list, group);
    }
    //public double AverageListenersByAgeGroupAndGenre(String group, String genre) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
    //    return service.AverageListenersByAgeGroupAndGenre(group, genre);
    //}

}
