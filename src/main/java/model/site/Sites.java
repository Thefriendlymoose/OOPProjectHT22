package model.site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Models a collection of sites
 *
 * @author David al Amiri
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
        if (sites.isEmpty()){
            nextId = 1L;
        } else {
            nextId = Collections.max(sites.keySet()) + 1;
        }
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

    public Long getNextId(){
        return nextId;
    }



}
