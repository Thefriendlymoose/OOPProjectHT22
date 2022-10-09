package model.site;

import java.util.Map;

public class Sites {

    private Map<Long, Site> sites;

    public Sites(Map<Long, Site> sites){
        this.sites = sites;
    }

    public Map<Long, Site> getSites(){
        return sites;
    }


}
