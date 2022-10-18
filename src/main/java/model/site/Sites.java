package model.site;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Models a collection of sites
 */
public class Sites implements Observable {

    private Map<Long, Site> sites;
    private Long nextId;

    private List<Observer> observers;

    /**
     * Constructor for sites, takes the map which contains all the sites, calculates the next available if and initializes
     * the list of observers
     * @param sites
     */
    public Sites(Map<Long, Site> sites){
        this.sites = sites;
        nextId = Collections.max(sites.keySet()) + 1;
        observers = new ArrayList<>();
    }

    /**
     *
     * @return Returns all the sites in a map
     */
    public Map<Long, Site> getInMap(){
        return sites;
    }

    /**
     *
     * @return returns all the sites in a list
     */
    public List<Site> getInList() {
        return new ArrayList<>(sites.values());
    }

    public Site getById(Long id){
        return sites.get(id);
    }

    public void addSite(Site site){
        sites.put(site.getSiteId(), site);
        nextId++;
        notifyObservers();
    }

    public void removeSite(Site site){
        sites.remove(site.getSiteId());
        notifyObservers();
    }

    public Long getNextId(){
        return nextId;
    }

    /**
     * Used when a site was edited to inform observers
     */
    public void updateSite(){
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }
}
