package model.finance.financeModel;

import model.WMS;
import model.site.Sites;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Responsibility: Store all bookkeeping data of all the sites
 *
 * Uses: SiteFinanceModel
 * Used by: WMS
 *
 * @author Simon Porsgaard / doktorjevsky
 *
 * */
public class FinanceModel {

    private Map<Long, SiteFinanceModel> financeModels;

    public FinanceModel(Map<Long, SiteFinanceModel> financeModels){

        this.financeModels = financeModels;
    }

    public SiteFinanceModel getSiteFinanceModel(long id) throws Exception {
        if (!financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " doesn't exist");
        }
        return financeModels.get(id);
    }

    public void addNewSiteFinanceModel(long id) throws Exception {
        if (financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " is already exists");
        }
        financeModels.put(id, new SiteFinanceModel(id, new HashMap<>(), new ArrayList<>()));
    }

    /**
     * Precondition: -
     * Ensures: Returns a filtered by the predicate map of references to the SiteFinanceModels
     * */
    public Map<Long, SiteFinanceModel> getFilteredSiteFinanceModels(Predicate<SiteFinanceModel> predicate) {
        Map<Long, SiteFinanceModel> out = new HashMap<>();
        financeModels
                .values()
                .stream()
                .filter(predicate)
                .forEach(filtered -> out.put(filtered.getId(), filtered));
        return out;
    }

    public List<SiteFinanceModel> getAsList(){
        return financeModels.values().stream().toList();
    }


}
