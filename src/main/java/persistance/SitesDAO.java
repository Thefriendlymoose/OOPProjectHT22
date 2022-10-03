package persistance;

// @todo justera importer när klasserna flyttas till ett paket per
// funktionellt paket i applikationen
import model.site.Site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SitesDAO implements IPersistenceNew<Site> {

    private Map<Long, Site> sites = new HashMap<>();
    private long nextFreeId =0;

    // Skicka in json-filen som argument?
    // @todo Gör singleton, privat konstruktor
    private SitesDAO(){

    }

    @Override
    public void Save(Site site) {

    }

    //@todo borde gå att ha koden i interfacet?
    @Override
    public List<Site> getAll() {
        List<Site> sitesList = new ArrayList<>(this.sites.values());
        //@todo sortera före retur
        return sitesList;
    }

    @Override
    public Map<Long, Site> getAllMap() {
        return this.sites;
    }

    @Override
    public long getNextId() {
        return this.nextFreeId;
    }

    @Override
    public Site findById(long id) {
        return this.sites.get(id);
    }

}
