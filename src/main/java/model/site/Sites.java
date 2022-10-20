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
public class Sites {

    private Map<Long, Site> sites;
    private Long nextId;


    /**
     * Constructor for sites, takes the map which contains all the sites, calculates the next available if and initializes
     * the list of observers
     * @param sites
     */
    public Sites(Map<Long, Site> sites){
        this.sites = sites;
        nextId = Collections.max(sites.keySet()) + 1;
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
    }

    public void removeSite(Site site){
        sites.remove(site.getSiteId());
    }

    public Long getNextId(){
        return nextId;
    }



}
